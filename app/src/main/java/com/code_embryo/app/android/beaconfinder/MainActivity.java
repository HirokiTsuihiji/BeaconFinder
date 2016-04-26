/*
 * MIT License
 *
 * Copyright(c) 2016 Hiroki Tsuihiji
 *
 * Permission is hereby granted,free of charge,to any person obtaining a copy
 * of this software and associated documentation files(the"Software"),to deal
 * in the Software without restriction,including without limitation the rights
 * to use,copy,modify,merge,publish,distribute,sublicense,and/or sell
 * copies of the Software,and to permit persons to whom the Software is
 * furnished to do so,subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED"AS IS",WITHOUT WARRANTY OF ANY KIND,EXPRESS OR
 * IMPLIED,INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,DAMAGES OR OTHER
 * LIABILITY,WHETHER IN AN ACTION OF CONTRACT,TORT OR OTHERWISE,ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.code_embryo.app.android.beaconfinder;

import android.app.DialogFragment;
import android.bluetooth.le.ScanResult;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.VisibleForTesting;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

import com.code_embryo.android.ble.Ble;
import com.code_embryo.android.ble.beacon.Beacon;
import com.code_embryo.android.ble.beacon.sort.SortOrder;
import com.code_embryo.android.ble.scan.BleScan;
import com.code_embryo.app.android.beaconfinder.view.BeaconListView;
import com.code_embryo.app.android.beaconfinder.view.BeaconSwitchView;
import com.code_embryo.app.android.beaconfinder.view.ViewUpdateHandler;
import com.code_embryo.app.android.beaconfinder.view.ViewUpdater;

/**
 * アプリケーション本体クラス.
 */
public class MainActivity extends AppCompatActivity
        implements BleScan.Callback, BeaconSwitchView.Callback, ViewUpdateHandler.Callback, SortDialog.Callback {
  private static final String TAG = MainActivity.class.getSimpleName();

  private BeaconSwitchView beaconSwitchView;
  private BeaconListView beaconListView;
  private SortOrder sortOrder;

  private Ble ble;
  private ViewUpdater viewUpdater;

  private static final String STATE_SCAN = "scanProcess";
  private static final String STATE_ORDER = "sortOrder";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    Log.d(TAG, "onCreate()");
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    confirmDevice();

    if (ble == null) {
      createBleService();
      createViewFunctions(savedInstanceState);
    }
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.menu_main, menu);
    return true;
  }

  /**
   * ソートアイコンをタッチしたとき、ソートダイアログを表示する.
   *
   * @param item メニュー情報
   */
  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    if (item.getItemId() == R.id.sort) {
      Bundle bundle = new Bundle();
      bundle.putInt(SortDialog.SELECTED, sortOrder.order());

      DialogFragment fragment = new SortDialog();
      fragment.setArguments(bundle);
      fragment.show(getFragmentManager(), "sortDialog");

      return true;
    }
    return super.onOptionsItemSelected(item);
  }

  private void confirmDevice() {
    if (!Ble.isSupported(getPackageManager())) {
      Toast.makeText(this, "BLE is not supported.", Toast.LENGTH_LONG).show();
      Log.w(TAG, "BLE is not supported.");
      finish();
    }
  }

  /**
   * BLEのスキャンサービスを作成する.
   */
  private void createBleService() {
    try {
      ble = new Ble(getApplicationContext(), this);
      Log.d(TAG, "  BLE created. " + ble.toString());
    } catch (UnsupportedOperationException e) {
      Toast.makeText(this, "BLE is not enabled.", Toast.LENGTH_LONG).show();
      Log.w(TAG, "BLE is not enabled.");
      finish();
    }
  }

  /**
   * ビュー情報と表示内容の更新処理を作成する.
   */
  private void createViewFunctions(Bundle savedInstanceState) {
    beaconSwitchView = new BeaconSwitchView((Switch) findViewById(R.id.switchScan),
            this, getScanProcess(savedInstanceState));
    beaconListView = new BeaconListView((ListView) findViewById(R.id.listView));
    sortOrder = new SortOrder(loadSortOrder());
    viewUpdater = new ViewUpdater(this);
    Log.d(TAG, "  load scanProcess = " + String.valueOf(beaconSwitchView.state()));
  }

  private boolean getScanProcess(Bundle savedInstanceState) {
    return savedInstanceState != null && savedInstanceState.getBoolean(STATE_SCAN, false);
  }

  /**
   * スキャン動作が開始となっていた場合は、スキャン動作を開始する.
   */
  @Override
  public void onResume() {
    Log.d(TAG, "onResume()");
    super.onResume();

    if (beaconSwitchView.state()) {
      startScan();
    }
  }

  /**
   * ソート順を保存し、スキャン動作中の場合はスキャン動作を停止する.
   */
  @Override
  public void onPause() {
    Log.d(TAG, "onPause()");
    super.onPause();

    saveSortOrder();

    if (beaconSwitchView.state()) {
      stopScan();
    }
  }

  /**
   * スキャン動作状態をBundleに保存する.
   */
  @Override
  public void onSaveInstanceState(Bundle savedInstanceState) {
    Log.d(TAG, "onSaveInstanceState()");
    super.onSaveInstanceState(savedInstanceState);
    savedInstanceState.putBoolean(STATE_SCAN, beaconSwitchView.state());
    Log.d(TAG, "  save scanProcess = " + String.valueOf(beaconSwitchView.state()));
  }

  /**
   * ソート順序をSharedPreferencesに保存する.
   */
  private void saveSortOrder() {
    SharedPreferences preferences = getSharedPreferences(STATE_ORDER, MODE_PRIVATE);
    SharedPreferences.Editor editor = preferences.edit();
    editor.putInt(STATE_ORDER, sortOrder.order());
    editor.apply();
    Log.d(TAG, "  save sortOrder = " + String.valueOf(sortOrder.order()));
  }

  /**
   * ソート順序をSharedPreferencesから読み出す.
   */
  private int loadSortOrder() {
    SharedPreferences preferences = getSharedPreferences(STATE_ORDER, MODE_PRIVATE);
    if (preferences == null) { return 0; }
    int order = preferences.getInt(STATE_ORDER, 0);
    Log.d(TAG, "  load sortOrder = " + String.valueOf(order));
    return order;
  }

  /**
   * BLEのスキャンを開始し、表示内容の更新処理も開始する.
   */
  private void startScan() {
    ble.startScan();
    viewUpdater.start();
    Log.d(TAG, "  start BLE scan.");
  }

  /**
   * BLEのスキャンを停止し、表示内容は初期表示に戻す.
   */
  private void stopScan() {
    ble.stopScan();
    viewUpdater.stop();
    Log.d(TAG, "  stop BLE scan.");
  }

  /**
   * アプリケーションの表示内容の更新処理.
   */
  @Override
  public void update() {
    beaconListView.update(beaconSwitchView.state(), MainActivity.this, sortOrder.type());
  }

  /**
   * スイッチがONになったときの処理.
   */
  @Override
  public void turnedOnSwitch() {
    startScan();
  }

  /**
   * スイッチがOFFになったときの処理.
   */
  @Override
  public void turnedOffSwitch() {
    stopScan();
    beaconListView.initialize(this);
  }

  /**
   * BLEのスキャンが成功したときの処理.
   *
   * @param result スキャン結果
   */
  @Override
  public void onScanResult(ScanResult result) {
    Log.d(TAG, "onScanResult()");
    Beacon beacon = Beacon.generate(result);
    beaconListView.register(beacon);
  }

  /**
   * ソート順序を選択したときの処理.
   */
  @Override
  public void onOrderSelected(int order) {
    sortOrder = new SortOrder(order);
    saveSortOrder();
  }
}

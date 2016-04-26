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

package com.code_embryo.android.ble;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanSettings;
import android.content.Context;
import android.content.pm.PackageManager;

import com.code_embryo.android.ble.scan.BleScan;

/**
 * BLEスキャナの作成と動作の制御を行うクラス.
 */
public class Ble {
  private final BluetoothLeScanner scanner;
  private final ScanCallback callback;

  /**
   * 端末がBLEをサポートしているか確認する.
   *
   * @return true: サポートしている, false: サポートしていない
   */
  public static boolean isSupported(PackageManager packageManager) {
    final String ble = PackageManager.FEATURE_BLUETOOTH_LE;
    return packageManager.hasSystemFeature(ble);
  }

  /**
   * BLEスキャナの作成とコールバック処理の登録を行う.
   *
   * @param context BLEスキャナを保持しているコンテキスト
   * @param callbacks BLEスキャナのコールバック処理の呼び出し元
   * @throws UnsupportedOperationException BLEの機能が存在しないもしくは機能がOFFになっている場合
   */
  public Ble(Context context, BleScan.Callback callbacks) throws UnsupportedOperationException {
    BluetoothLeScanner leScanner = getLeScanner(context);
    if (leScanner == null) { throw new UnsupportedOperationException(); }

    this.scanner = leScanner;

    BleScan listener = new BleScan(callbacks);
    this.callback = listener.getCallback();
  }

  /**
   * BLEスキャナをコンテキスト情報から取り出す.
   *
   * @param context BLEスキャナを保持しているコンテキスト
   * @return 取得結果を返す（成功: BluetoothLeScanner, 失敗: null）
   */
  BluetoothLeScanner getLeScanner(Context context) {
    BluetoothAdapter adapter = getAdapter(context);
    if (adapter == null || !adapter.isEnabled()) { return null; }

    BluetoothLeScanner leScanner = adapter.getBluetoothLeScanner();
    if (leScanner == null) { return null; }
    return leScanner;
  }

  /**
   * Bluetoothアダプタをコンテキスト情報から取り出す.
   *
   * @param context Bluetoothアダプタを保持しているコンテキスト
   * @return 取得結果を返す（成功: BluetoothLeAdapter, 失敗: null）
   */
  private BluetoothAdapter getAdapter(Context context) {
    BluetoothManager manager = (BluetoothManager) context.getSystemService(Context.BLUETOOTH_SERVICE);
    if (manager == null) { return null; }

    BluetoothAdapter adapter = manager.getAdapter();
    if (adapter == null || !adapter.isEnabled()) { return null; }
    return adapter;
  }

  /**
   * BLEのスキャンを開始する.
   */
  public void startScan() {
    ScanSettings.Builder builder = new ScanSettings.Builder();
    builder.setScanMode(ScanSettings.SCAN_MODE_BALANCED);
    scanner.startScan(null, builder.build(), callback);
  }

  /**
   * BLEのスキャンを停止する.
   */
  public void stopScan() {
    scanner.stopScan(callback);
  }
}

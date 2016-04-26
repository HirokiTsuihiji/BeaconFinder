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

package com.code_embryo.app.android.beaconfinder.view;

import android.util.Log;
import android.widget.CompoundButton;
import android.widget.Switch;

/**
 * スキャン動作を制御するスイッチのビュークラス.
 */
public class BeaconSwitchView implements CompoundButton.OnCheckedChangeListener {
  private static final String TAG = BeaconSwitchView.class.getSimpleName();

  private Switch view;
  private Callback callback;
  private boolean scanProcess;

  /**
   * スイッチがON/OFFしたときに飛び出されるコールバック処理のインタフェース.
   */
  public interface Callback {
    void turnedOnSwitch();
    void turnedOffSwitch();
  }

  /**
   * ビーコンのスキャンを行うスイッチビュー
   *
   * @param view スイッチのビュー
   * @param callback コールバック処理の呼び出し元
   * @param scanProcess スキャン動作の開始/停止情報
   */
  public BeaconSwitchView(Switch view, Callback callback, boolean scanProcess) {
    this.view = view;
    view.setOnCheckedChangeListener(this);
    this.callback = callback;
    this.scanProcess = scanProcess;
  }

  /**
   * スキャン動作の開始/停止情報を返す.
   */
  public Boolean state() { return scanProcess; }

  /**
   * スイッチ動作が変化したときに呼び出される.
   *
   * @param buttonView スイッチのビュー情報
   * @param isChecked スッチのON/OFF情報
   */
  @Override
  public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
    Log.d(TAG, "onCheckChanged()");

    if (isChecked) {
      startBleScan();
      return;
    }
    stopBleScan();
  }

  private void startBleScan() {
    if (scanProcess) { return; }
    scanProcess = true;
    view.setChecked(true);

    callback.turnedOnSwitch();
    Log.d(TAG, "Beacon scan start.");
  }

  private void stopBleScan() {
    if (!scanProcess) { return; }
    scanProcess = false;
    view.setChecked(false);

    callback.turnedOffSwitch();
    Log.d(TAG, "Beacon scan stop.");
  }
}

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

package com.code_embryo.android.ble.scan;

import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanResult;
import android.util.Log;

import com.code_embryo.android.ble.scan.failed.ScanFailed;
import com.code_embryo.android.ble.scan.failed.ScanFailedMessage;

/**
 * BluetoothLeScannerのコールバック処理をラッピングして、成功したときの処理だけをあとから実装すれば動くようにしたクラス.
 */
public class BleScan {
  private final String TAG = BleScan.class.getSimpleName();

  private final Callback callback;
  private ScanFailed failedMessage;

  /**
   * BLEスキャンが成功したときのコールバック処理のインタフェース.
   * これを実装して、処理結果を受け取る。
   */
  public interface Callback {
    void onScanResult(ScanResult result);
  }

  /**
   * BLEデバイスのスキャンクラス.
   *
   * @param callback コールバック処理の呼び出し元
   */
  public BleScan(Callback callback) {
    this.callback = callback;
    this.failedMessage = new ScanFailed();
  }

  /**
   * BLEデバイスのスキャンが、成功したときと失敗したときに呼び出されるコールバック処理.
   * onBatchScanResults()は未使用のため、オーバーライドしていない。
   *
   * @return ScanCallback BLEデバイスのスキャン結果のコールバック処理
   */
  public ScanCallback getCallback() {
    return new ScanCallback() {
      @Override
      public void onScanResult(int callbackType, ScanResult result) {
        callback.onScanResult(result);
      }

      @Override
      public void onScanFailed(int errorCode) {
        ScanFailedMessage message = failedMessage.message(errorCode);
        Log.w(TAG, message.value());
      }
    };
  }
}

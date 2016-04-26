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

package com.code_embryo.android.ble.beacon.data.radio;

/**
 * 受信電波強度と送信電波強度を保持する電波強度クラス.
 */
public class Intensity {
  private final Rssi rssi;
  private final TxPower txPower;

  /**
   * 電波強度
   *
   * @param rssi 受信電波強度
   * @param txPower 送信電波強度
   */
  public Intensity(Rssi rssi, TxPower txPower) {
    if (rssi == null || txPower == null) {
      throw new NullPointerException("parameter is null.");
    }
    this.rssi = rssi;
    this.txPower = txPower;
  }

  /**
   * 受信電場強度をInteger型で返す.
   *
   * @return Integer 受信電波強度
   */
  Integer rssi() {
    return rssi.value();
  }

  /**
   * 送信電波強度をInteger型で返す.
   *
   * @return Integer 送信電波強度
   */
  Integer txPower() {
    return txPower.value();
  }
}

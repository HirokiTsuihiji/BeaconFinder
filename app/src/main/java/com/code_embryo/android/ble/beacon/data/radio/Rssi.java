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
 * 受信電波強度クラス.
 */
public class Rssi {
  private final int rssi;
  private static final int MAX_RSSI = 127;
  private static final int MIN_RSSI = -128;

  /**
   * 受信電波強度として8ビットの整数値を引数に設定する.
   *
   * @param rssi 受信電波強度
   * @throws IllegalArgumentException 引数値が8ビットの整数ではなかった場合
   */
  public Rssi(int rssi) {
    if (!validate(rssi)) {
      throw new IllegalArgumentException();
    }

    this.rssi = rssi;
  }

  private boolean validate(int value) {
    if (value > MAX_RSSI) {
      return false;
    }
    if (value < MIN_RSSI) {
      return false;
    }
    return true;
  }

  /**
   * 受信電波強度をInteger型で返す.
   *
   * @return Integer 受信電波強度
   */
  Integer value() {
    return rssi;
  }
}

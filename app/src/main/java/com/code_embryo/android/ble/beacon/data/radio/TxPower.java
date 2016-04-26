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
 * 送信電波強度クラス.
 */
public class TxPower {
  private final int txPower;
  private static final int MAX_TX_POWER = 127;
  private static final int MIN_TX_POWER = -128;

  /**
   * 送信電波強度として8ビットの整数値を引数に設定する.
   *
   * @param txPower 送信電波強度
   * @throws IllegalArgumentException 引数値が8ビットの整数ではなかった場合
   */
  public TxPower(int txPower) {
    if (!validate(txPower)) { throw new IllegalArgumentException(); }

    this.txPower = txPower;
  }

  private boolean validate(int power) {
    if (power > MAX_TX_POWER) { return false; }
    if (power < MIN_TX_POWER) { return false; }
    return true;
  }

  /**
   * 送信電波強度をInteger型で返す.
   * @return Integer 送信電波強度
   */
  Integer value() {
    return txPower;
  }
}

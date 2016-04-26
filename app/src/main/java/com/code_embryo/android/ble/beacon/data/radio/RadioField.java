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
 * 電波強度と距離などの近接情報を保持する電場クラス.
 */
public class RadioField {
  private final Intensity intensity;
  private final Proximity proximity;

  /**
   * 電場情報（電波強度と近接情報）
   *
   * @param intensity 電波強度
   */
  public RadioField(Intensity intensity) {
    if (intensity == null) { throw new NullPointerException(); }
    this.intensity = intensity;
    this.proximity = new Proximity(intensity);
  }

  /**
   * 受信電波強度をInteger型で返す.
   *
   * @return Integer 受信電波強度
   */
  public Integer rssi() {
    return intensity.rssi();
  }

  /**
   * 送信電波強度をInteger型で返す.
   *
   * @return Integer 送信電波強度
   */
  public Integer txPower() {
    return intensity.txPower();
  }

  /**
   * 距離をDouble型で返す.
   *
   * @return Double 距離[m]
   */
  public Double accuracy() {
    return proximity.accuracy();
  }

  /**
   * 近接情報をString型で返す.
   *
   * "Immediate": 0~0.2m, "Near": 0.2~2.0m, "Far": 2.0m~
   *
   * @return String 近接情報（"Immediate", "Near", "Far"）
   */
  public String proximity() {
    return proximity.value();
  }
}

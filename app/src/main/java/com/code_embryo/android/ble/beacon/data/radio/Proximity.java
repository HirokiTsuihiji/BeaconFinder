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
 * 電波強度を元に距離と近接情報を求める.
 */
class Proximity {
  private final double accuracy;
  private final String proximity;

  private static final double IMMEDIATE_AREA = 0.20d;
  private static final double NEAR_AERA = 2.00d;

  private static final String IMMEDIATE = "Immediate";
  private static final String NEAR = "Near";
  private static final String FAR = "Far";

  /**
   * 距離と近接情報
   *
   * @param intensity 電波強度.
   */
  Proximity(Intensity intensity) {
    Integer signalDifference = (intensity.txPower() - intensity.rssi());
    this.accuracy = Math.pow(10,  signalDifference.doubleValue() / 20.0d);
    this.proximity = decideProximity(this.accuracy);
  }

  private String decideProximity(double accuracy) {
    if (accuracy <= IMMEDIATE_AREA) {
      return IMMEDIATE;
    }
    if (accuracy <= NEAR_AERA) {
      return NEAR;
    }
    return FAR;
  }

  /**
   * 距離をDouble型で返す.
   *
   * @return Double 距離[m]
   */
  Double accuracy() {
    return accuracy;
  }

  /**
   * 近接情報をString型で返す.
   *
   * "Immediate": 0~0.2m, "Near": 0.2~2.0m, "Far": 2.0m~
   * @return String 近接情報（"Immediate", "Near", "Far"）
   */
  String value() {
    return proximity;
  }
}

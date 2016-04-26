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

package com.code_embryo.android.ble.beacon.data;

import com.code_embryo.android.ble.beacon.data.radio.Intensity;
import com.code_embryo.android.ble.beacon.data.radio.RadioField;
import com.code_embryo.android.ble.beacon.data.radio.Rssi;
import com.code_embryo.android.ble.beacon.data.radio.TxPower;
import com.code_embryo.android.ble.beacon.record.BeaconRecord;

/**
 * ビーコンのID情報と電場情報を保持するクラス.
 */
class BeaconInfo {
  private final BeaconId beaconId;
  private final RadioField radioField;

  /**
   * ビーコン情報
   *
   * @param record ビーコンから取得したレコード情報
   * @param rssi 受信電波強度
   */
  BeaconInfo(BeaconRecord record, Rssi rssi) {
    this.beaconId = new BeaconId(record);
    final Intensity intensity = new Intensity(rssi, new TxPower(record.txPower()));
    this.radioField = new RadioField(intensity);
  }

  /**
   * ビーコンのUUIDをString型で返す.
   *
   * @return String ビーコンのUUID
   */
  String uuid() {
    return beaconId.uuid();
  }

  /**
   * Major番号をInteger型で返す.
   *
   * @return Integer Major番号
   */
  Integer major() {
    return beaconId.major();
  }

  /**
   * Minor番号をInteger型で返す.
   *
   * @return Integer Minor番号
   */
  Integer minor() {
    return beaconId.minor();
  }

  /**
   * 受信電波強度をInteger型で返す.
   *
   * @return Integer 受信電波強度
   */
  Integer rssi() {
    return radioField.rssi();
  }

  /**
   * 送信電波強度をInteger型で返す.
   *
   * @return Integer 送信電波強度
   */
  Integer txPower() {
    return radioField.txPower();
  }

  /**
   * 距離をDouble型で返す.
   *
   * @return Double 距離[m]
   */
  Double accuracy() {
    return radioField.accuracy();
  }

  /**
   * 近接情報をString型で返す.
   *
   * "Immediate": 0~0.2m, "Near": 0.2~2.0m, "Far": 2.0m~
   *
   * @return String 近接情報（"Immediate", "Near", "Far"）
   */
  String proximity() {
    return radioField.proximity();
  }
}

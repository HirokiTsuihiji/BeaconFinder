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

import com.code_embryo.android.ble.beacon.data.radio.Rssi;
import com.code_embryo.android.ble.beacon.record.BeaconRecord;

/**
 * デバイスアドレスとビーコン情報を保持するビーコンデバイスクラス
 */
public class BeaconDevice {
  private final DeviceAddress address;
  private final BeaconInfo info;

  /**
   * ビーコンデバイス情報
   *
   * @param address デバイスアドレス文字列
   * @param record ビーコンから取得したレコード情報
   * @param rssi   受信電波強度
   */
  public BeaconDevice(DeviceAddress address, BeaconRecord record, Rssi rssi) {
    this.address = address;
    this.info = new BeaconInfo(record, rssi);
  }

  /**
   * デバイスアドレスをString型で返す.
   *
   * @return String デバイスアドレス
   */
  public String deviceAddress() {
    return address.value();
  }

  /**
   * ビーコンのUUIDをString型で返す.
   *
   * @return String ビーコンのUUID
   */
  public String uuid() {
    return info.uuid();
  }

  /**
   * Major番号をInteger型で返す.
   *
   * @return Integer Major番号
   */
  public Integer major() {
    return info.major();
  }

  /**
   * Minor番号をInteger型で返す.
   *
   * @return Integer Minor番号
   */
  public Integer minor() {
    return info.minor();
  }

  /**
   * 受信電波強度をInteger型で返す.
   *
   * @return Integer 受信電波強度
   */
  public Integer rssi() {
    return info.rssi();
  }

  /**
   * 送信電波強度をInteger型で返す.
   *
   * @return Integer 送信電波強度
   */
  public Integer txPower() {
    return info.txPower();
  }

  /**
   * 距離をDouble型で返す.
   *
   * @return Double 距離[m]
   */
  public Double accuracy() {
    return info.accuracy();
  }

  /**
   * 近接情報をString型で返す.
   *
   * "Immediate": 0~0.2m, "Near": 0.2~2.0m, "Far": 2.0m~
   *
   * @return String 近接情報（"Immediate", "Near", "Far"）
   */
  public String proximity() {
    return info.proximity();
  }
}

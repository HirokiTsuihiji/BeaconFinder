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

package com.code_embryo.android.ble.beacon;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.le.ScanResult;

import com.code_embryo.android.ble.beacon.data.BeaconDevice;
import com.code_embryo.android.ble.beacon.data.DeviceAddress;
import com.code_embryo.android.ble.beacon.data.FindDate;
import com.code_embryo.android.ble.beacon.data.radio.Rssi;
import com.code_embryo.android.ble.beacon.record.BeaconRecord;

/**
 * ビーコン情報を取得した日付とビーコンのデバイス情報を保持するクラス.
 */
public class Beacon {
  private final FindDate date;
  private final BeaconDevice device;

  Beacon(DeviceAddress address, BeaconRecord record, Rssi rssi) {
    this.date = generateFindDate();
    this.device = new BeaconDevice(address, record, rssi);
  }

  FindDate generateFindDate() {
    return new FindDate();
  }

  /**
   * ビーコンから取得したスキャン結果を元にビーコン情報を生成する.
   *
   * @param result スキャン結果
   * @return Beacon ビーコン情報
   */
  public static Beacon generate(ScanResult result) {
    BeaconRecord record = BeaconRecord.generate(result);
    if (record == null) { return null; }

    DeviceAddress address = new DeviceAddress(getBluetoothDeviceAddress(result));
    Rssi rssi = new Rssi(result.getRssi());

    return new Beacon(address, record, rssi);
  }

  /**
   * スキャン結果のBluetoothデバイス情報からデバイスアドレスを取り出す.
   *
   * @return String Bluetoothデバイスのデバイスアドレス
   */
  static String getBluetoothDeviceAddress(ScanResult result) {
    BluetoothDevice device = result.getDevice();
    return device.getAddress();
  }

  /**
   * ビーコン情報を取得した日付をLong型で返す.
   *
   * @return Long ビーコン情報を取得した日付
   */
  public Long date() {
    return date.value();
  }

  /**
   * デバイスアドレスをString型で返す.
   *
   * @return String デバイスアドレス
   */
  public String deviceAddress() {
    return device.deviceAddress();
  }

  /**
   * ビーコンのUUIDをString型で返す.
   *
   * @return String ビーコンのUUID
   */
  public String uuid() {
    return device.uuid();
  }

  /**
   * Major番号をInteger型で返す.
   *
   * @return Integer Major番号
   */
  public Integer major() {
    return device.major();
  }

  /**
   * Minor番号をInteger型で返す.
   *
   * @return Integer Minor番号
   */
  public Integer minor() {
    return device.minor();
  }

  /**
   * 受信電波強度をInteger型で返す.
   *
   * @return Integer 受信電波強度
   */
  public Integer rssi() {
    return device.rssi();
  }

  /**
   * 送信電波強度をInteger型で返す.
   *
   * @return Integer 送信電波強度
   */
  public Integer txPower() {
    return device.txPower();
  }

  /**
   * 距離をDouble型で返す.
   *
   * @return Double 距離[m]
   */
  public Double accuracy() {
    return device.accuracy();
  }

  /**
   * 近接情報をString型で返す.
   * "Immediate": 0~0.2m, "Near": 0.2~2.0m, "Far": 2.0m~
   *
   * @return String 近接情報（"Immediate", "Near", "Far"）
   */
  public String proximity() {
    return device.proximity();
  }
}

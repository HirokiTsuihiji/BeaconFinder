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

package com.code_embryo.android.ble.beacon.record;

import android.bluetooth.le.ScanRecord;
import android.bluetooth.le.ScanResult;

import com.code_embryo.android.ble.beacon.record.header.Header;

import java.util.Arrays;

/**
 * ビーコンから取得したレコード情報を保持するクラス.
 */
public class BeaconRecord {
  private final Header header;
  private final BeaconBody beaconBody;

  /**
   * ビーコンから取得したスキャン結果を元にレコード情報を取得する.
   * 取得したスキャン結果がビーコンの情報ではない場合、nullを返す.
   *
   * @param result ビーコンのスキャン結果情報
   * @return BeaconRecord ビーコンのレコード情報（正しくない場合はnullを返す）
   */
  public static BeaconRecord generate(ScanResult result) {
    if (result == null) { return null; }
    BeaconRecord record = new BeaconRecord(getRawRecord(result));
    if (RecordChecker.validate(record)) { return record; }
    return null;
  }

  private static byte[] getRawRecord(ScanResult result) {
    ScanRecord record = result.getScanRecord();
    assert record != null;
    return record.getBytes();
  }

  /**
   * ビーコンのレコード情報
   *
   * @param rawRecord 取得したスキャン結果のレコード情報
   */
  BeaconRecord(byte[] rawRecord) {
    this.header = createPrefix(rawRecord);
    this.beaconBody = createBeaconBody(rawRecord);
  }

  private Header createPrefix(byte[] bytes) {
    byte adType = bytes[Constants.AD_TYPE];
    byte[] id = Arrays.copyOfRange(bytes, Constants.COMPANY_ID, Constants.COMPANY_ID_END);
    byte[] ad = Arrays.copyOfRange(bytes, Constants.BEACON_AD, Constants.BEACON_AD_END);

    return new Header(adType, id, ad);
  }

  private BeaconBody createBeaconBody(byte[] bytes) {
    byte[] uuid = Arrays.copyOfRange(bytes, Constants.UUID, Constants.UUID_END);
    byte[] major = Arrays.copyOfRange(bytes, Constants.MAJOR, Constants.MAJOR_END);
    byte[] minor = Arrays.copyOfRange(bytes, Constants.MINOR, Constants.MINOR_END);
    byte txPower = bytes[Constants.TXPOWER];

    return new BeaconBody(uuid, major, minor, txPower);
  }

  /**
   * AD種別をプリミティブなbyte型で返す.
   *
   * @return byte AD種別
   */
  public byte adType() {
    return header.adType();
  }

  /**
   * 企業IDをプリミティブなbyte型配列で返す.
   *
   * @return byte[] 企業ID
   */
  public byte[] companyId() {
    return header.companyId();
  }

  /**
   * フォーマット情報をプリミティブなbyte型配列で返す.
   *
   * @return byte[] フォーマット情報
   */
  public byte[] beaconFormatInfo() {
    return header.beaconFormatInfo();
  }

  /**
   * UUIDをプリミティブなbyte型配列で返す.
   *
   * @return byte[] UUID
   */
  public byte[] uuid() {
    return beaconBody.uuid();
  }

  /**
   * Major番号をプリミティブなbyte型配列で返す.
   *
   * @return byte[] Major番号
   */
  public byte[] major() {
    return beaconBody.major();
  }

  /**
   * Minor番号をプリミティブなbyte型配列で返す.
   *
   * @return byte[] Minor番号
   */
  public byte[] minor() {
    return beaconBody.minor();
  }

  /**
   * 送信電波強度をプリミティブなbyte型で返す.
   *
   * @return byte 送信電波強度
   */
  public byte txPower() {
    return beaconBody.txPower();
  }
}

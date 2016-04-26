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

/**
 * ビーコンから取得したレコード情報が正しいか確認するクラス.
 */
class RecordChecker {
  /**
   * レコード情報が正しいか確認する.
   *
   * @param record ビーコンから取得したレコード情報
   * @return Boolean 確認結果 true = OK, false = NG
   */
  static Boolean validate(BeaconRecord record) {
    if (record.adType() != Constants.MANUFACTURER_AD) {
      return false;
    }
    if (!isAppleId(record.companyId())) {
      return false;
    }
    if (!isBeaconFormatInfo(record.beaconFormatInfo())) {
      return false;
    }

    return true;
  }

  private static boolean isAppleId(byte[] bytes) {
    if (bytes[0] != Constants.APPLE_ID0) {
      return false;
    }
    if (bytes[1] != Constants.APPLE_ID1) {
      return false;
    }

    return true;
  }

  private static boolean isBeaconFormatInfo(byte[] bytes) {
    if (bytes[0] != Constants.BEACON_AD0) {
      return false;
    }
    if (bytes[1] != Constants.BEACON_AD1) {
      return false;
    }

    return true;
  }

}

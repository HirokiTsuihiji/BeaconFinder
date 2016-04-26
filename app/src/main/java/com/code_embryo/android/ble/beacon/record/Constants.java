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

class Constants {
  // ビーコンから取得したレコードのオフセット値
  static final int HEADER_LENGTH = 0;
  static final int HEADER_TYPE = HEADER_LENGTH + 1;
  static final int HEADER_FLAG = HEADER_TYPE + 1;
  static final int LENGTH = HEADER_FLAG + 1;
  static final int AD_TYPE = LENGTH + 1;
  static final int COMPANY_ID = AD_TYPE + 1;
  static final int COMPANY_ID_SIZE = 2;
  static final int BEACON_AD = COMPANY_ID + COMPANY_ID_SIZE;
  static final int BEACON_AD_SIZE = 2;
  static final int UUID = BEACON_AD + BEACON_AD_SIZE;
  static final int UUID_SIZE = 16;
  static final int MAJOR = UUID + UUID_SIZE;
  static final int MAJOR_SIZE = 2;
  static final int MINOR = MAJOR + MAJOR_SIZE;
  static final int MINOR_SIZE = 2;
  static final int TXPOWER = MINOR + MINOR_SIZE;

  // copyOfRange()で使う終端値
  static final int COMPANY_ID_END = COMPANY_ID + COMPANY_ID_SIZE;
  static final int BEACON_AD_END = BEACON_AD + BEACON_AD_SIZE;
  static final int UUID_END = UUID + UUID_SIZE;
  static final int MAJOR_END = MAJOR + MAJOR_SIZE;
  static final int MINOR_END = MINOR + MINOR_SIZE;

  // ビーコンのレコードから取得した値
  // RecordCheckerでiBeaconのパケットか確認するために使用する
  static final byte MANUFACTURER_AD = (byte) 0xFF;

  static final byte APPLE_ID0 = (byte) 0x4C;
  static final byte APPLE_ID1 = (byte) 0x00;

  static final int BEACON_AD0 = (byte) 0x02;
  static final int BEACON_AD1 = (byte) 0x15;
}

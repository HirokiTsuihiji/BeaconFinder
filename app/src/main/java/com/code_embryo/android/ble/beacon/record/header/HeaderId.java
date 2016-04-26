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

package com.code_embryo.android.ble.beacon.record.header;

/**
 * ビーコンから取得した企業IDとフォーマット情報を保持するクラス.
 */
class HeaderId {
  private final CompanyId companyId;
  private final FormatInfo formatInfo;

  /**
   * ヘッダID情報
   *
   * @param companyId 企業ID
   * @param beaconFormatInfo フォーマット情報
   */
  HeaderId(byte[] companyId, byte[] beaconFormatInfo) {
    this.companyId = new CompanyId(companyId);
    this.formatInfo = new FormatInfo(beaconFormatInfo);
  }

  /**
   * 企業IDをプリミティブなbyte型配列で返す.
   *
   * @return byte[] 企業ID
   */
  byte[] companyId() {
    return companyId.value();
  }

  /**
   * フォーマット情報をプリミティブなbyte型配列で返す.
   *
   * @return byte[] フォーマット情報
   */
  byte[] beaconFormatInfo() {
    return formatInfo.value();
  }
}

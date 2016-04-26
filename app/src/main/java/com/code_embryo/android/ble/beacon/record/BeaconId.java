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
 * ビーコンから取得したビーコンID情報を保持するクラス.
 */
class BeaconId {
  private final Uuid uuid;
  private final AttributeId attributeId;

  /**
   * ビーコンID情報
   *
   * @param uuid UUID
   * @param major Major番号
   * @param minor Minor番号
   */
  BeaconId(byte[] uuid, byte[] major, byte[] minor) {
    this.uuid = new Uuid(uuid);
    this.attributeId = new AttributeId(major, minor);
  }

  /**
   * UUIDをプリミティブなbyte型配列で返す.
   *
   * @return byte[] UUID
   */
  byte[] uuid() {
    return uuid.value();
  }

  /**
   * Major番号をプリミティブなbyte型配列で返す.
   *
   * @return byte[] Major番号
   */
  byte[] major() {
    return attributeId.major();
  }

  /**
   * Minor番号をプリミティブなbyte型配列で返す.
   *
   * @return byte[] Minor番号
   */
  byte[] minor() {
    return attributeId.minor();
  }
}

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

import com.code_embryo.android.ble.beacon.record.BeaconRecord;

/**
 * UUIDと属性IDを保持するビーコンIDクラス.
 */
class BeaconId {
  private final Uuid uuid;
  private final AttributeId attributeId;


  /**
   * ビーコンID情報
   *
   * @param record ビーコンから取得したレコード情報
   */
  BeaconId(BeaconRecord record) {
    String id = makeUuid(record.uuid());
    this.uuid = new Uuid(id);
    this.attributeId = new AttributeId(record);
  }

  private String makeUuid(byte[] bytes) {
    StringBuilder sb = new StringBuilder();
    for (byte b : bytes) {
      sb.append(String.format("%02X", b));
    }

    return sb.toString();
  }

  /**
   * ビーコンのUUIDをString型で返す.
   *
   * @return String ビーコンのUUID
   */
  String uuid() {
    return uuid.value();
  }

  /**
   * Major番号をInteger型で返す.
   *
   * @return Integer Major番号
   */
  Integer major() {
    return attributeId.major();
  }

  /**
   * Minor番号をInteger型で返す.
   *
   * @return Integer Minor番号
   */
  Integer minor() {
    return attributeId.minor();
  }
}

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

import java.nio.ByteBuffer;
import java.util.Arrays;

/**
 * ビーコンの属性ID（Major番号, Minor番号）クラス.
 */
class AttributeId {
  private final int major;
  private final int minor;

  /**
   * ビーコンの属性ID
   *
   * @param record ビーコンから取得したレコード情報
   */
  AttributeId(BeaconRecord record) {
    int major = makeAttributeId(record.major());
    int minor = makeAttributeId(record.minor());

    this.major = major;
    this.minor = minor;
  }

  private int makeAttributeId(byte[] byteId) {
    byte[] intSizeId = new byte[4];
    intSizeId[2] = byteId[0];
    intSizeId[3] = byteId[1];
    ByteBuffer buf = ByteBuffer.wrap(intSizeId);

    return buf.getInt();
  }

  /**
   * Major番号をInteger型で返す.
   *
   * @return Integer Major番号
   */
  Integer major() { return major; }

  /**
   * Minor番号をInteger型で返す.
   *
   * @return Integer Minor番号
   */
  Integer minor() { return minor; }
}

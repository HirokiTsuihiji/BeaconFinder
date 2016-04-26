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

package com.code_embryo.android.ble.beacon.sort;

import com.code_embryo.android.ble.beacon.Beacon;

import java.util.Comparator;

/**
 * ソート順序を列挙したクラス.
 */
public enum OrderType {
  /**
   * デバイスアドレスを昇順にソートする.
   */
  DEVICE_ADDRESS(new DeviceAddressOrder()),
  /**
   *デバイスアドレスを降順にソートする.
   */
  DEVICE_ADDRESS_REVERSE(new DeviceAddressReverseOrder()),
  /**
   * UUID, Major番号, Minor番号を連結した値を昇順にソートする.
   */
  ID(new IdOrder()),
  /**
   * UUID, Major番号, Minor番号を連結した値を降順にソートする.
   */
  ID_REVERSE(new IdReverseOrder());

  private Comparator<Beacon> comparator;

  /**
   * ソート順序種別
   *
   * @param comparator ソートに使用するコンパレータ
   */
  OrderType(Comparator<Beacon> comparator) {
    this.comparator = comparator;
  }

  /**
   * ソートに使用するコンパレータを返す.
   *
   * @return Comparator<Beacon> ソートに使用するコンパレータ
   */
  public Comparator<Beacon> comparator() {
    return comparator;
  }
}

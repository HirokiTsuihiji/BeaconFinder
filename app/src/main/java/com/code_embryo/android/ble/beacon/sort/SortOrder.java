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

/**
 * 番号で指定されたソート順序に対応したコンパレータを保持するクラス.
 */
public class SortOrder {
  private final OrderType order;

  /**
   * ソート順序
   *
   * @param sortOrder ソート順序の指定番号
   */
  public SortOrder(int sortOrder) {
    int order = sortOrder;
    if (sortOrder > OrderType.values().length) {
      order = 0;
    }
    this.order = getOrderType(order);
  }

  OrderType getOrderType(int sortOrder) {
    return OrderType.values()[sortOrder];
  }

  /**
   * ソート順序の指定番号をプリミティブなint型で返す.
   *
   * @return int ソート順序の指定番号
   */
  public int order() { return order.ordinal(); }

  /**
   * ソート順序に対応したコンパレータを返す.
   */
  public OrderType type() { return order; }
}

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

import java.util.Calendar;
import java.util.Date;

/**
 * ビーコン情報を取得した日付を保持するクラス.
 */
public class FindDate {
  private final Date date;

  /**
   * インスタンスを作成したときの時刻をDate型で保持する.
   */
  public FindDate() {
    this.date = getNowDate();
  }

  /**
   * ユニットテストがやりやすいようにパッケージプライベートのメソッドにしておく.
   */
  Date getNowDate() {
    Calendar c = Calendar.getInstance();
    return c.getTime();
  }

  /**
   * ビーコン情報を取得した日付をLong型で返す.
   */
  public Long value() {
    return date.getTime();
  }
}

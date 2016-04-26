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

package com.code_embryo.app.android.beaconfinder.view.cell;

import android.view.View;
import android.widget.TextView;

import com.code_embryo.android.ble.beacon.Beacon;
import com.code_embryo.app.android.beaconfinder.R;

import java.util.Locale;

/**
 * 表示する電波情報をビューに設定する.
 */
class RadioField {
  private final View convertView;
  private final Beacon beacon;

  /**
   * セルアダプタに表示する電波情報
   *
   * @param convertView 表示するビュー情報
   * @param beacon      表示するビーコン情報
   */
  RadioField(View convertView, Beacon beacon) {
    this.convertView = convertView;
    this.beacon = beacon;
  }

  /**
   * 受信電波強度をビューに設定する.
   */
  void rssi() {
    TextView rssi = (TextView) convertView.findViewById(R.id.rssi);
    String textRssi = String.valueOf(beacon.rssi()) + " dBm";
    rssi.setText(textRssi);
  }

  /**
   * 送信電波強度をビューに設定する.
   */
  void txPower() {
    TextView txPower = (TextView) convertView.findViewById(R.id.txPower);
    String textTxPower = String.valueOf(beacon.txPower()) + " dBm";
    txPower.setText(textTxPower);
  }

  /**
   * ビーコンまでの距離をビューに設定する.
   */
  void accuracy() {
    TextView accuracy = (TextView) convertView.findViewById(R.id.accuracy);
    String textAccuracy = String.format(Locale.getDefault(), "%.2f", beacon.accuracy()) + " m";
    accuracy.setText(textAccuracy);
  }

  /**
   * 近接情報をビューに設定する.
   */
  void proximity() {
    TextView proximity = (TextView) convertView.findViewById(R.id.proximity);
    proximity.setText(beacon.proximity());
  }

}

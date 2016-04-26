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

/**
 * 表示するセルにビーコンID情報を設定する.
 */
class BeaconId {
  private final View convertView;
  private final Beacon beacon;

  /**
   * セルアダプタに表示するビーコンID
   *
   * @param convertView 表示するビュー情報
   * @param beacon 表示するビーコン情報
   */
  BeaconId(View convertView, Beacon beacon) {
    this.convertView = convertView;
    this.beacon = beacon;
  }

  /**
   * UUIDの値をビューに設定する.
   */
  void uuid() {
    TextView uuid = (TextView) convertView.findViewById(R.id.uuid);
    uuid.setText(beacon.uuid());
  }

  /**
   * Major番号をビューに設定する.
   */
  void major() {
    TextView major = (TextView) convertView.findViewById(R.id.majorId);
    major.setText(String.valueOf(beacon.major()));
  }

  /**
   * Minor番号をビューに設定する.
   */
  void minor() {
    TextView minor = (TextView) convertView.findViewById(R.id.minorId);
    minor.setText(String.valueOf(beacon.minor()));
  }

}

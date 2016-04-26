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

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.code_embryo.android.ble.beacon.Beacon;
import com.code_embryo.app.android.beaconfinder.R;

import java.util.List;

/**
 * ビーコン情報を表示するセルクラス.
 */
public class BeaconCellAdapter extends ArrayAdapter<Beacon> {
  private LayoutInflater inflater;

  /**
   * ビーコンの情報を表示するセルアダプタ
   *
   * @param context アプリケーションのコンテキスト情報
   * @param resource レイアウトリソース
   * @param objects 表示するビーコン情報のリスト
   */
  public BeaconCellAdapter(Context context, int resource, List<Beacon> objects) {
    super(context, resource, objects);
    inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
  }

  /**
   * 表示するセルの情報を設定する.
   *
   * @param position 表示するセルの番号
   * @param convertView 表示するセルのビュー情報
   * @param parent 表示するセルのビューグループ情報
   * @return 表示するセルのビュー情報
   */
  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    Beacon item = getItem(position);

    if (convertView == null) {
      convertView = inflater.inflate(R.layout.beacon_cell, null);
    }

    TextView address = (TextView) convertView.findViewById(R.id.deviceAddress);
    address.setText(item.deviceAddress());

    BeaconId beaconId = new BeaconId(convertView, item);
    beaconId.uuid();
    beaconId.major();
    beaconId.minor();

    RadioField radioField = new RadioField(convertView, item);
    radioField.rssi();
    radioField.txPower();
    radioField.accuracy();
    radioField.proximity();

    return convertView;
  }
}

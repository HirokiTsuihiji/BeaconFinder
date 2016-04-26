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

package com.code_embryo.app.android.beaconfinder.view;

import android.content.Context;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.code_embryo.android.ble.beacon.Beacon;
import com.code_embryo.android.ble.beacon.BeaconKey;
import com.code_embryo.android.ble.beacon.BeaconList;
import com.code_embryo.android.ble.beacon.sort.OrderType;
import com.code_embryo.app.android.beaconfinder.view.cell.BeaconCellAdapter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;

/**
 * ビーコン情報を表示するリストビュークラス.
 */
public class BeaconListView {
  private static final String TAG = BeaconListView.class.getSimpleName();
  private static final int TIME_OUT = 20 * 1000;  // 20s

  private BeaconList beaconList;
  private ListView listView;

  /**
   * ビーコン情報を表示するリストビュー
   *
   * @param listView ビーコン情報を表示するリストビュー
   */
  public BeaconListView(ListView listView) {
    this.beaconList = new BeaconList();
    this.listView = listView;
  }

  /**
   * ビーコン情報が未表示の状態をビューに設定する.
   *
   * @param context アプリケーションのコンテキスト情報
   */
  public void initialize(Context context) {
    ArrayList<String> list = new ArrayList<>();
    ListAdapter adapter = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, list);
    listView.setAdapter(adapter);
  }

  /**
   * ビーコン情報をビューに設定する.
   *
   * @param beacon ビーコン情報
   */
  public void register(Beacon beacon) {
    if (beacon == null) {
      return;
    }
    BeaconKey key = new BeaconKey(beacon);
    beaconList.register(key, beacon);
    Log.d(TAG, "Beacon registered = " + key.value());
  }

  /**
   * リストビューの表示内容を更新する.
   *
   * @param isScan スキャン動作の開始/停止
   * @param context アプリケーションのコンテキスト情報
   * @param order ソート順序
   */
  public void update(Boolean isScan, Context context, OrderType order) {
    if (isScan) {
      current(context, order);
      return;
    }
    initialize(context);
  }

  private void current(Context context, OrderType order) {
    Calendar cal = Calendar.getInstance();
    detectMissing(cal.getTime());
    ArrayList<Beacon> array = sort(order);
    BeaconCellAdapter adapter = new BeaconCellAdapter(context, 0, array);
    listView.setAdapter(adapter);
  }

  private void detectMissing(Date date) {
    for (Beacon beacon : beaconList.array()) {
      if (date.getTime() - beacon.date() > TIME_OUT) {
        BeaconKey key = new BeaconKey(beacon);
        beaconList.remove(key);
        Log.d(TAG, "Beacon removed = " + key.value());
      }
    }
  }

  private ArrayList<Beacon> sort(OrderType order) {
    ArrayList<Beacon> array = beaconList.array();
    Collections.sort(array, order.comparator());
    return array;
  }

}

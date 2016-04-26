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

package com.code_embryo.android.ble.beacon;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * ビーコン情報キーとビーコン情報をペアで保持するクラス.
 */
public class BeaconList {
  private Map<String, Beacon> map;

  public BeaconList() {
    this.map = new HashMap<>();
  }

  /**
   * ビーコン情報を登録する.
   *
   * @param key ビーコン情報キー
   * @param beacon ビーコン情報
   */
  public synchronized void register(BeaconKey key, Beacon beacon) {
    map.put(key.value(), beacon);
  }

  /**
   * ビーコン情報を削除する.
   *
   * @param key    ビーコン情報キー
   */
  public synchronized void remove(BeaconKey key) {
    map.remove(key.value());
  }

  /**
   * ビーコン情報キーを使ってビーコン情報を取得する.
   *
   * @param key ビーコン情報キー
   * @return Beacon ビーコン情報
   */
  public Beacon beacon(BeaconKey key) {
    return map.get(key.value());
  }

  /**
   * 登録してあるビーコン情報をArrayListで返す.
   *
   * @return ArrayList<Beacon> 登録してあるビーコン情報の配列リスト
   */
  public ArrayList<Beacon> array() {
    return new ArrayList<>(map.values());
  }
}

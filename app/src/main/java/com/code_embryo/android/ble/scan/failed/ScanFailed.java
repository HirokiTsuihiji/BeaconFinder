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

package com.code_embryo.android.ble.scan.failed;

import android.bluetooth.le.ScanCallback;

import java.util.HashMap;
import java.util.Map;

/**
 * BLEのスキャンが失敗したときのメッセージをまとめるクラス.
 */
public class ScanFailed {
  private Map<Integer, ScanFailedMessage> errorCodeMap;

  /**
   * インスタインスを作成したときに、エラーコードと発行するメッセージのマップを作成する.
   */
  public ScanFailed() {
    this.errorCodeMap = new HashMap<>();
    register(errorCodeMap);
  }

  private void register(Map<Integer, ScanFailedMessage> map) {
    map.put(ScanCallback.SCAN_FAILED_ALREADY_STARTED, new MessageAlreadyStarted());
    map.put(ScanCallback.SCAN_FAILED_APPLICATION_REGISTRATION_FAILED, new MessageRegistrationFailed());
    map.put(ScanCallback.SCAN_FAILED_FEATURE_UNSUPPORTED, new MessageFeatureUnsupported());
    map.put(ScanCallback.SCAN_FAILED_INTERNAL_ERROR, new MessageInternalError());
  }

  /**
   * エラーコードを渡して発行するメッセージを取り出す.
   */
  public ScanFailedMessage message(int errorCode) {
    return errorCodeMap.get(errorCode);
  }
}

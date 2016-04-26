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

import android.util.Log;

import java.util.Timer;
import java.util.TimerTask;

/**
 * アプリケーションの表示内容を更新する.
 * 一定周期で表示内容の更新を行う実行処理を呼び出している。
 */
public class ViewUpdater {
  private static final String TAG = ViewUpdater.class.getSimpleName();
  private static final int DELAY = 0; // ms
  private static final int PERIOD = 1500; // ms

  private Timer timer;
  private ViewUpdateHandler handler;

  /**
   * アプリケーションの表示内容を更新する.
   *
   * @param callback 表示内容のコールバック処理の呼び出し元
   */
  public ViewUpdater(ViewUpdateHandler.Callback callback) {
    this.timer = null;
    this.handler = new ViewUpdateHandler(callback);
  }

  /**
   * 表示内容の更新を開始する.
   */
  public void start() {
    Log.d(TAG, "start()");
    timer = new Timer(true);
    Log.d(TAG, "new Timer generated. " + timer.toString());
    timer.schedule(new TimerTask() {
      @Override
      public void run() {
        handler.execute();
      }
    }, DELAY, PERIOD);
  }

  /**
   * 表示内容の更新を停止する.
   */
  public void stop() {
    Log.d(TAG, "stop()");
    timer.cancel();
    timer = null;
    Log.d(TAG, "  timer = null");
  }
}

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

import android.os.Handler;
import android.util.Log;

/**
 * アプリケーションの表示内容の更新を実行する.
 */
public class ViewUpdateHandler {
  private static final String TAG = ViewUpdateHandler.class.getSimpleName();

  private Handler handler;
  private final Callback callback;

  /**
   * 表示更新のコールバック処理のインタフェース.
   * これを実装して表示内容を更新する。
   */
  public interface Callback {
    void update();
  }

  /**
   * アプリケーションの表示内容を更新するハンドラ
   *
   * @param callback コールバック処理の呼び出し元
   */
  ViewUpdateHandler(Callback callback) {
    if (this.handler == null) {
      this.handler = new Handler();
    }

    this.callback = callback;
  }

  /**
   * 表示内容の更新を実行する.
   */
  void execute() {
    handler.post(new Runnable() {
      @Override
      public void run() {
        Log.d(TAG, "handler.run()");
        Log.d(TAG, "  ListView updated.");
        Log.d(TAG, "  handler = " + handler.toString());
        callback.update();
      }
    });
  }
}

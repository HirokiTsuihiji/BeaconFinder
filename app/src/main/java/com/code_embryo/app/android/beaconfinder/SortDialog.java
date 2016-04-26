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

package com.code_embryo.app.android.beaconfinder;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * ビーコン表示リストのソート順序を選択するダイアログ.
 */
public class SortDialog extends DialogFragment {
  private static final String TAG = SortDialog.class.getSimpleName();

  public static final String SELECTED = "selected";

  private Callback callback;

  public interface Callback {
    void onOrderSelected(int order);
  }

  /**
   * コールバック先を取得する.
   */
  @Override
  public void onAttach(Activity activity) {
    Log.d(TAG, "onAttach()");
    super.onAttach(activity);
    try {
      callback = (Callback) activity;
    } catch(ClassCastException e) {
      Log.e(TAG, "ClassCastException = " + e.getMessage());
    }
    Log.d(TAG, "callback = " + activity.getClass().getSimpleName());
  }

  /**
   * ダイアログの作成とタッチしたときの処理を実装する.
   */
  @Override
  public Dialog onCreateDialog(final Bundle savedInstanceState) {
    Log.d(TAG, "onCreateDialog()");
    int sortOrder = getArguments().getInt(SELECTED);
    final List<Integer> argsList = new ArrayList<>();
    argsList.add(sortOrder);

    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.AlertDialog);
    builder.setTitle(R.string.sort_title)
            .setSingleChoiceItems(R.array.sort_dialog_items, sortOrder, new DialogInterface.OnClickListener() {
              @Override
              public void onClick(DialogInterface dialog, int which) {
                Log.d(TAG, "setSingleChoiceItems: onClick()");
                argsList.clear();
                argsList.add(which);
                Log.d(TAG, "  select sortOrder = " + String.valueOf(which));
              }
            })
            .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
              @Override
              public void onClick(DialogInterface dialog, int which) {
                Log.d(TAG, "setPositiveButton: onClick()");
                callback.onOrderSelected(argsList.get(0));
                Log.d(TAG, "  save sortOrder = " + String.valueOf(argsList.get(0)));
              }
            })
            .setNegativeButton(R.string.cancel, null);
    Log.d(TAG, "  load sortOrder = " + String.valueOf(sortOrder));
    return builder.create();
  }
}

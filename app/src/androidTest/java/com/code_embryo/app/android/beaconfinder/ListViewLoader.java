package com.code_embryo.app.android.beaconfinder;

import android.support.test.espresso.IdlingResource;
import android.widget.ListAdapter;
import android.widget.ListView;

class ListViewLoader implements IdlingResource {
  ResourceCallback callback;
  ListView listView;

  ListViewLoader(ListView listView) {
    this.listView = listView;
  }
  @Override
  public String getName() {
    return ListViewLoader.class.getSimpleName();
  }

  @Override
  public boolean isIdleNow() {
    if (callback == null) { return false; }
    if (isLoaded()) {
      callback.onTransitionToIdle();
      return true;
    }
    return false;
  }

  @Override
  public void registerIdleTransitionCallback(ResourceCallback callback) {
    this.callback = callback;
  }

  private boolean isLoaded() {
    ListAdapter adapter = listView.getAdapter();
    return (adapter != null && adapter.getCount() > 0);
  }
}

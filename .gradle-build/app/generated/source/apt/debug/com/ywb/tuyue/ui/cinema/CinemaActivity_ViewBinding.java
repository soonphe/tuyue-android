// Generated code from Butter Knife. Do not modify!
package com.ywb.tuyue.ui.cinema;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.ywb.tuyue.R;
import com.ywb.tuyue.widget.head.HeaderView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class CinemaActivity_ViewBinding implements Unbinder {
  private CinemaActivity target;

  @UiThread
  public CinemaActivity_ViewBinding(CinemaActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public CinemaActivity_ViewBinding(CinemaActivity target, View source) {
    this.target = target;

    target.mHeader = Utils.findRequiredViewAsType(source, R.id.header, "field 'mHeader'", HeaderView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    CinemaActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mHeader = null;
  }
}

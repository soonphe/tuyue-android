// Generated code from Butter Knife. Do not modify!
package com.ywb.tuyue.ui.web;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.FrameLayout;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.ywb.tuyue.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class WebviewActivity_ViewBinding implements Unbinder {
  private WebviewActivity target;

  @UiThread
  public WebviewActivity_ViewBinding(WebviewActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public WebviewActivity_ViewBinding(WebviewActivity target, View source) {
    this.target = target;

    target.flWeb = Utils.findRequiredViewAsType(source, R.id.fl_web, "field 'flWeb'", FrameLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    WebviewActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.flWeb = null;
  }
}

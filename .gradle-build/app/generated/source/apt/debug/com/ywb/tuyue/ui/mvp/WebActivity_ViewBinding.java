// Generated code from Butter Knife. Do not modify!
package com.ywb.tuyue.ui.mvp;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.FrameLayout;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.ywb.tuyue.R;
import com.ywb.tuyue.widget.AppTitle;
import java.lang.IllegalStateException;
import java.lang.Override;

public class WebActivity_ViewBinding implements Unbinder {
  private WebActivity target;

  @UiThread
  public WebActivity_ViewBinding(WebActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public WebActivity_ViewBinding(WebActivity target, View source) {
    this.target = target;

    target.flWeb = Utils.findRequiredViewAsType(source, R.id.fl_web, "field 'flWeb'", FrameLayout.class);
    target.appTitle = Utils.findRequiredViewAsType(source, R.id.app_title_id, "field 'appTitle'", AppTitle.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    WebActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.flWeb = null;
    target.appTitle = null;
  }
}

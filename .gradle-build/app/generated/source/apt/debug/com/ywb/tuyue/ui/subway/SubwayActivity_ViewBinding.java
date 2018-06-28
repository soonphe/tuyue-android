// Generated code from Butter Knife. Do not modify!
package com.ywb.tuyue.ui.subway;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.ywb.tuyue.R;
import com.ywb.tuyue.widget.head.HeaderView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class SubwayActivity_ViewBinding implements Unbinder {
  private SubwayActivity target;

  @UiThread
  public SubwayActivity_ViewBinding(SubwayActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public SubwayActivity_ViewBinding(SubwayActivity target, View source) {
    this.target = target;

    target.mHeader = Utils.findRequiredViewAsType(source, R.id.header, "field 'mHeader'", HeaderView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    SubwayActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mHeader = null;
  }
}

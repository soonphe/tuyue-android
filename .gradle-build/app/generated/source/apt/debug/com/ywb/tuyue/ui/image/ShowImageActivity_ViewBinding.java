// Generated code from Butter Knife. Do not modify!
package com.ywb.tuyue.ui.image;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ProgressBar;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.github.chrisbanes.photoview.PhotoView;
import com.ywb.tuyue.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ShowImageActivity_ViewBinding implements Unbinder {
  private ShowImageActivity target;

  @UiThread
  public ShowImageActivity_ViewBinding(ShowImageActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public ShowImageActivity_ViewBinding(ShowImageActivity target, View source) {
    this.target = target;

    target.ivPhoto = Utils.findRequiredViewAsType(source, R.id.iv_photo, "field 'ivPhoto'", PhotoView.class);
    target.photoviewProgressbar = Utils.findRequiredViewAsType(source, R.id.photoview_progressbar, "field 'photoviewProgressbar'", ProgressBar.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    ShowImageActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.ivPhoto = null;
    target.photoviewProgressbar = null;
  }
}

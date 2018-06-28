// Generated code from Butter Knife. Do not modify!
package com.ywb.tuyue.ui.game;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.ywb.tuyue.R;
import com.ywb.tuyue.widget.head.HeaderView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class GameActivity_ViewBinding implements Unbinder {
  private GameActivity target;

  @UiThread
  public GameActivity_ViewBinding(GameActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public GameActivity_ViewBinding(GameActivity target, View source) {
    this.target = target;

    target.mHeader = Utils.findRequiredViewAsType(source, R.id.header, "field 'mHeader'", HeaderView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    GameActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mHeader = null;
  }
}

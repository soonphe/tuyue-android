// Generated code from Butter Knife. Do not modify!
package com.ywb.tuyue.ui.main;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.ywb.tuyue.R;
import com.ywb.tuyue.widget.card.Card1;
import com.ywb.tuyue.widget.card.Card2;
import com.ywb.tuyue.widget.head.HeaderView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MainActivity_ViewBinding implements Unbinder {
  private MainActivity target;

  private View view2131296330;

  private View view2131296331;

  private View view2131296577;

  private View view2131296447;

  private View view2131296579;

  private View view2131296444;

  private View view2131296358;

  private View view2131296389;

  private View view2131296685;

  @UiThread
  public MainActivity_ViewBinding(MainActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public MainActivity_ViewBinding(final MainActivity target, View source) {
    this.target = target;

    View view;
    target.mHeader = Utils.findRequiredViewAsType(source, R.id.header, "field 'mHeader'", HeaderView.class);
    target.mBack = Utils.findRequiredViewAsType(source, R.id.head_back, "field 'mBack'", ImageButton.class);
    target.mSettings = Utils.findRequiredViewAsType(source, R.id.head_setting, "field 'mSettings'", ImageButton.class);
    view = Utils.findRequiredView(source, R.id.advertise1, "field 'mAdvertise1' and method 'onViewClicked'");
    target.mAdvertise1 = Utils.castView(view, R.id.advertise1, "field 'mAdvertise1'", ImageView.class);
    view2131296330 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.advertise2, "field 'mAdvertise2' and method 'onViewClicked'");
    target.mAdvertise2 = Utils.castView(view, R.id.advertise2, "field 'mAdvertise2'", ImageView.class);
    view2131296331 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.movie, "field 'mMovie' and method 'onViewClicked'");
    target.mMovie = Utils.castView(view, R.id.movie, "field 'mMovie'", Card1.class);
    view2131296577 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.game, "field 'mGame' and method 'onViewClicked'");
    target.mGame = Utils.castView(view, R.id.game, "field 'mGame'", Card1.class);
    view2131296447 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.music, "field 'mMusic' and method 'onViewClicked'");
    target.mMusic = Utils.castView(view, R.id.music, "field 'mMusic'", Card2.class);
    view2131296579 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.food, "field 'mFood' and method 'onViewClicked'");
    target.mFood = Utils.castView(view, R.id.food, "field 'mFood'", Card2.class);
    view2131296444 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.book, "field 'mBook' and method 'onViewClicked'");
    target.mBook = Utils.castView(view, R.id.book, "field 'mBook'", Card2.class);
    view2131296358 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.city, "field 'mCity' and method 'onViewClicked'");
    target.mCity = Utils.castView(view, R.id.city, "field 'mCity'", Card2.class);
    view2131296389 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.subway, "field 'mSubway' and method 'onViewClicked'");
    target.mSubway = Utils.castView(view, R.id.subway, "field 'mSubway'", Card2.class);
    view2131296685 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    MainActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mHeader = null;
    target.mBack = null;
    target.mSettings = null;
    target.mAdvertise1 = null;
    target.mAdvertise2 = null;
    target.mMovie = null;
    target.mGame = null;
    target.mMusic = null;
    target.mFood = null;
    target.mBook = null;
    target.mCity = null;
    target.mSubway = null;

    view2131296330.setOnClickListener(null);
    view2131296330 = null;
    view2131296331.setOnClickListener(null);
    view2131296331 = null;
    view2131296577.setOnClickListener(null);
    view2131296577 = null;
    view2131296447.setOnClickListener(null);
    view2131296447 = null;
    view2131296579.setOnClickListener(null);
    view2131296579 = null;
    view2131296444.setOnClickListener(null);
    view2131296444 = null;
    view2131296358.setOnClickListener(null);
    view2131296358 = null;
    view2131296389.setOnClickListener(null);
    view2131296389 = null;
    view2131296685.setOnClickListener(null);
    view2131296685 = null;
  }
}

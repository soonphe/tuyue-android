package com.ywb.tuyue.widget;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.ywb.tuyue.R;

/**
 * Created by penghao on 2017/12/27.
 * description：
 */

public class LoadingDialog extends Dialog {

    public LoadingDialog(@NonNull Context context) {
        super(context);
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_loading, null);
        ImageView imageView = view.findViewById(R.id.loading_image);
        AnimationDrawable animationDrawable = (AnimationDrawable) imageView.getDrawable();
        animationDrawable.start();
        this.setContentView(view);
    }

    public void showDialog() {
        if (!isShowing()) {
            show();
        }
    }

    public void dismissDialog() {
        if (isShowing()) {
            dismiss();
        }
    }
}

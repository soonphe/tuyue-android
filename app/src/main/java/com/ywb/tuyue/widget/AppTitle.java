package com.ywb.tuyue.widget;


import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.StringRes;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.ConvertUtils;
import com.blankj.utilcode.util.StringUtils;
import com.ywb.tuyue.R;
import com.ywb.tuyue.utils.ResourceUtils;


/**自定义Title
 * Created by wushange on 2016/12/19.
 */

public class AppTitle extends RelativeLayout implements View.OnClickListener, View.OnLongClickListener{

    private ImageButton  appBarBack;
    private TextView     appBarTitle;
    private ImageButton  appBarBtn1;
    private ImageButton  appBarBtn2;
    private TextView     appBarRightTv;
    private LinearLayout titleRoot;

    private boolean canFinish = true;

    public AppTitle(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
        initAttrs(context, attrs);

    }


    private void initView(Context context) {
        LayoutInflater.from(context).inflate(R.layout.app_bar_view, this);
        titleRoot = (LinearLayout) findViewById(R.id.title_root);
        appBarBack = (ImageButton) findViewById(R.id.app_bar_back);
        appBarTitle = (TextView) findViewById(R.id.app_bar_title);
        appBarBtn1 = (ImageButton) findViewById(R.id.app_bar_btn1);
        appBarBtn2 = (ImageButton) findViewById(R.id.app_bar_btn2);
        appBarRightTv = (TextView) findViewById(R.id.app_bar_right_tv);

        appBarBack.setOnClickListener(this);
        appBarTitle.setOnClickListener(this);
        appBarBtn1.setOnClickListener(this);
        appBarBtn2.setOnClickListener(this);
        appBarRightTv.setOnClickListener(this);


        appBarBack.setOnLongClickListener(this);
        appBarTitle.setOnLongClickListener(this);
        appBarBtn1.setOnLongClickListener(this);
        appBarBtn2.setOnLongClickListener(this);
        appBarRightTv.setOnLongClickListener(this);


    }

    public void initAttrs(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.AppTitle);
        appBarBack.setVisibility(ResourceUtils.getBoolean(typedArray, R.styleable.AppTitle_showBack, R.bool.default_showBack)
                ? VISIBLE : INVISIBLE);
        canFinish = ResourceUtils.getBoolean(typedArray, R.styleable.AppTitle_canFinish, R.bool.default_canFinish);


        String titleText = ResourceUtils.getString(typedArray, R.styleable.AppTitle_centreText);
        appBarTitle.setVisibility(StringUtils.isEmpty(titleText) ? GONE : VISIBLE);
        appBarTitle.setText(titleText);
        appBarTitle.setTextColor(ResourceUtils.getColor(typedArray, R.styleable.AppTitle_centreTextColor, R.color.default_textColor));
        appBarTitle.setTextSize(TypedValue.COMPLEX_UNIT_PX, ResourceUtils.getDimenSize(typedArray, R.styleable.AppTitle_centreTextSize, R.dimen.default_textSize));

        int button1Res = ResourceUtils.getResourceId(typedArray, R.styleable.AppTitle_button1ImgSrc, 0);
        appBarBtn1.setVisibility(button1Res == 0 ? GONE : VISIBLE);
        appBarBtn1.setImageResource(button1Res);

        int button2Res = ResourceUtils.getResourceId(typedArray, R.styleable.AppTitle_button2ImgSrc, 0);
        appBarBtn2.setVisibility(button2Res == 0 ? GONE : VISIBLE);
        appBarBtn2.setImageResource(button2Res);

        String rtitleText = ResourceUtils.getString(typedArray, R.styleable.AppTitle_centreText);
        appBarRightTv.setVisibility(StringUtils.isEmpty(rtitleText) ? GONE : VISIBLE);
        appBarRightTv.setText(ResourceUtils.getString(typedArray, R.styleable.AppTitle_rightText));
        appBarRightTv.setTextColor(ResourceUtils.getColor(typedArray, R.styleable.AppTitle_rightTextColor, R.color.default_textColor));
        appBarRightTv.setTextSize(TypedValue.COMPLEX_UNIT_PX, ResourceUtils.getDimenSize(typedArray, R.styleable.AppTitle_rightTextSize, R.dimen.default_textSize));
        typedArray.recycle();
        if (appBarBtn1.isShown() || appBarBtn2.isShown()) {
            appBarTitle.setMaxWidth(ConvertUtils.dp2px(120));
        } else if (appBarBtn1.isShown() && appBarBtn2.isShown()) {
            appBarTitle.setMaxWidth(ConvertUtils.dp2px(150));
        } else {
            appBarTitle.setMaxWidth(ConvertUtils.dp2px(200));
        }

    }


    public AppTitle setCanFinish(boolean canFinish) {
        this.canFinish = canFinish;
        return this;
    }

    public AppTitle setTitle(String title) {
        if (!appBarTitle.isShown()) {
            appBarTitle.setVisibility(VISIBLE);
        }
        appBarTitle.setText(title);
        return this;
    }

    public AppTitle setTitle(@StringRes int titleResId) {
        if (!appBarTitle.isShown()) {
            appBarTitle.setVisibility(VISIBLE);
        }
        appBarTitle.setText(getResources().getString(titleResId));
        return this;
    }


    public AppTitle setRightText(String rightText) {
        if (!appBarRightTv.isShown()) {
            appBarRightTv.setVisibility(VISIBLE);
        }
        appBarRightTv.setText(rightText);
        return this;
    }


    public AppTitle setRightText(@StringRes int rightText) {
        if (!appBarRightTv.isShown()) {
            appBarRightTv.setVisibility(VISIBLE);
        }
        appBarRightTv.setText(getResources().getString(rightText));
        return this;
    }

    public AppTitle setButton1Src(int resId) {
        if (!appBarBtn1.isShown()) {
            appBarBtn1.setVisibility(VISIBLE);
        }
        appBarBtn1.setImageResource(resId);
        return this;
    }


    public AppTitle setButton2Src(int resId) {
        if (!appBarBtn2.isShown()) {
            appBarBtn2.setVisibility(VISIBLE);
        }
        appBarBtn2.setImageResource(resId);
        return this;
    }

    /**
     * 统一左上角返回
     * @param view
     */
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            //返回
            case R.id.app_bar_back:
                if (canFinish) {
                    try {
                        ((Activity) getContext()).finish();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                break;
//            //重写：刷新/视图
//            case R.id.app_bar_right_tv:
//                try {
//                    ((Activity) getContext()).getWindow().getDecorView().invalidate();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//                break;
        }
    }

    @Override
    public boolean onLongClick(View v) {
        return false;
    }

    public ImageButton getAppBarBack() {
        return appBarBack;
    }

    public TextView getAppBarTitle() {
        return appBarTitle;
    }

    public ImageButton getAppBarBtn1() {
        return appBarBtn1;
    }

    public ImageButton getAppBarBtn2() {
        return appBarBtn2;
    }

    public TextView getAppBarRightTv() {
        return appBarRightTv;
    }

    public LinearLayout getTitleRoot() {
        return titleRoot;
    }

    public boolean isCanFinish() {
        return canFinish;
    }

}

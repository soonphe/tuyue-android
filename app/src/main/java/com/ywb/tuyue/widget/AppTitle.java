package com.ywb.tuyue.widget;


import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.StringRes;

import com.blankj.utilcode.util.BarUtils;
import com.blankj.utilcode.util.ConvertUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.StringUtils;
import com.ywb.tuyue.R;
import com.ywb.tuyue.utils.ResourceUtils;
import com.ywb.tuyue.widget.head.CustomStatusBar;


/**
 * @Author soonphe
 * @Date 2018-08-30 10:41
 * @Description 自定义title——左部返回，中间文件/图片，右部文字/图片
 */
public class AppTitle extends RelativeLayout implements View.OnClickListener, View.OnLongClickListener {

    private Context mContext;
    private ImageButton appBarBack;
    private TextView appBarTitle;
    private ImageButton appBarBtn1;
    private ImageButton appBarBtn2;
    private TextView appBarRightTv;
    private LinearLayout titleRoot;

    private CustomStatusBar statusLine;

    private boolean canFinish = true;

    public AppTitle(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initView(context);
        initAttrs(context, attrs);

    }


    private void initView(Context context) {
        LayoutInflater.from(context).inflate(R.layout.app_bar_view, this);
        statusLine = findViewById(R.id.status_bar);
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

        //获取屏幕分辨率（Math.ceil向上取整）
        //高度heightPixel，宽度widthPixel，密度density
        //dpi       ：dots per inch ， 直接来说就是一英寸多少个像素点。常见取值 120，160，240。也称作像素密度，简称密度
        //density ： 直接翻译的话貌似叫 密度。常见取值 1.5 ， 1.0 。和标准dpi的比例（160px/inc）
//        double statusBarHeight = Math.ceil(25 * mContext.getResources().getDisplayMetrics().density);
//        LogUtils.e("屏幕大小"
//                + mContext.getResources().getDisplayMetrics()
//                + "屏幕密度"
//                + mContext.getResources().getDisplayMetrics().density);

        /**
         * 二代平板存放路径：7.0
         /storage/emulated/0/download/
         分辨率：1280x800 160dpi

         原始平板存放路径：系统5.1
         /storage/sdcard0/Download/ctcy.mp4
         分辨率：1280x800 160dpi

         4G平板：
         状态栏高度：48
         分辨率：1920x1200 320dpi
         */

        /**
         * 获取状态栏高度
         */
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            //dp转px
//            result = context.getResources().getDimensionPixelSize(resourceId);
            LogUtils.e("屏幕高度大于0");
            statusLine.setVisibility(View.VISIBLE);
        } else {
            LogUtils.e("未获取到的屏幕高度");
            statusLine.setVisibility(View.GONE);
            //设置状态栏透明
            BarUtils.setStatusBarAlpha((Activity) mContext);
        }

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
     *
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

    public CustomStatusBar getStatusLine() {
        return statusLine;
    }
}

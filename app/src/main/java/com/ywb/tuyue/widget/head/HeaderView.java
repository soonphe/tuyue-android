package com.ywb.tuyue.widget.head;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.ywb.tuyue.R;
import com.ywb.tuyue.utils.StatusBarUtil;

/**
 * Description:自定义头部标题
 * Created by wcystart on 2018/6/21.
 */

public class HeaderView  extends FrameLayout{
    private Context mContext;
    ImageButton mBack;
    TextView mHeadTitle;
    ImageView ivTitle;
    ImageButton mHeadSetting;
    CustomStatusBar statusLine;

    public HeaderView(Context context) {
        this(context, null);
    }

    public HeaderView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HeaderView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        init();
    }

    private void init() {
        LayoutInflater.from(mContext).inflate(R.layout.header, this, true);
        mBack = findViewById(R.id.head_back);
        mHeadTitle = findViewById(R.id.head_tv_title);
        ivTitle = findViewById(R.id.iv_title);
        mHeadSetting = findViewById(R.id.head_setting);
        statusLine = findViewById(R.id.status_bar);

        double statusBarHeight = Math.ceil(25 * mContext.getResources().getDisplayMetrics().density);
        Log.i("状态栏高度", statusBarHeight + "");
        if (statusBarHeight > 25) {
            statusLine.setVisibility(View.GONE);
            StatusBarUtil.immersive((Activity) mContext);
            StatusBarUtil.buildStatuSpace(mContext, statusLine);
        } else {
            statusLine.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 设置左侧按钮图片
     *
     * @param imgRes
     */
    public void setLeftBtnSrc(int imgRes) {
        mBack.setImageResource(imgRes);
    }

    /**
     * 设置左侧按钮点击事件
     *
     * @param onClickListener
     */
    public void setLeftBtnClickListsner(OnClickListener onClickListener) {
        mBack.setOnClickListener(onClickListener);
    }


    /**
     * 设置左侧按钮的图标和点击事件
     *
     * @param imgRes
     * @param onClickListener
     */
    public void setLeftBtnProperty(int imgRes, OnClickListener onClickListener) {
        setLeftBtnSrc(imgRes);
        setLeftBtnClickListsner(onClickListener);
    }

    /**
     * 设置左侧按钮可见性
     *
     * @param visiable
     */
    public void setLeftBtnVisiable(int visiable) {
        mBack.setVisibility(visiable);
    }

    /**
     * 设置右侧按钮图片
     *
     * @param imgRes
     */
    public void setRightBtnSrc(int imgRes) {
        mHeadSetting.setImageResource(imgRes);
    }

    /**
     * 设置右侧按钮点击事件
     *
     * @param onClickListener
     */
    public void setRightBtnClickListsner(OnClickListener onClickListener) {
        mHeadSetting.setOnClickListener(onClickListener);
    }

    /**
     * 设置右侧按钮可见性
     *
     * @param visiable
     */
    public void setRightBtnVisiable(int visiable) {
        mHeadSetting.setVisibility(visiable);
    }

    /**
     * 设置右侧按钮的图标和点击事件
     *
     * @param imgRes
     * @param onClickListener
     */
    public void setRightBtnProperty(int imgRes, OnClickListener onClickListener) {
        setRightBtnSrc(imgRes);
        setRightBtnClickListsner(onClickListener);
    }

    /**
     * 设置标题
     *
     * @param textTitleRes
     */
    public void setTitle(int textTitleRes) {
        mHeadTitle.setText(textTitleRes);
    }

    /**
     * 设置标题
     *
     * @param textTitle
     */
    public void setTitle(String textTitle) {
        mHeadTitle.setText(textTitle);
    }

    /**
     * 设置图片标题
     *
     * @param imgTitleRes
     */
    public void setImgTitle(int imgTitleRes) {
        ivTitle.setImageResource(imgTitleRes);
    }

    /**
     * 设置标题隐藏其它类别标题
     *
     * @param textTitleRes
     */
    public void setTitleOnly(int textTitleRes) {
        setTitle(textTitleRes);
        mHeadTitle.setVisibility(View.VISIBLE);
        ivTitle.setVisibility(View.GONE);
    }

    /**
     * 设置图片标题隐藏其它类别标题
     *
     * @param imgTitleRes
     */
    public void setImgTitleOnly(int imgTitleRes) {
        setImgTitle(imgTitleRes);
        ivTitle.setVisibility(View.VISIBLE);
        mHeadTitle.setVisibility(View.GONE);
    }

    public void unRegister() {
        statusLine.unregisterBroadcast();
    }
}

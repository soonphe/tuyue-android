package com.ywb.tuyue.widget.card;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.FrameLayout;

import com.ywb.tuyue.entity.CardBean;

/**
 * Description: 自定义卡片基类
 * Created by wcystart on 2018/6/21.
 */

public  abstract class CustomerCard extends FrameLayout {
    public CustomerCard(@NonNull Context context) {
        super(context);
    }

    public CustomerCard(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomerCard(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * 设置背景图片
     *
     * @param url
     */
    abstract void setCardBg(String url);

    /**
     * 设置背景图片
     *
     * @param resId
     */
    abstract void setCardBg(int resId);


    /**
     * 设置图标
     *
     * @param iconId
     */
    abstract void setCardIcon(int iconId);

    /**
     * 设置英字标题
     *
     * @param ENText
     */
    abstract void setENText(String ENText);

    /**
     * 设置汉字标题
     *
     * @param ZNText
     */
    abstract void setZNText(String ZNText);

    public void setCardBean(CardBean cardBean) {
        if (cardBean.getUrl() != null) {
            //Log.d("setCardBg", "___" + DataBase.getString(cardBean.getUrl()));
            setCardBg(cardBean.getUrl());
        } else {
            setCardBg(cardBean.getResId());
        }
        setCardIcon(cardBean.getIconId());
        setENText(cardBean.getENText() + "");
        setZNText(cardBean.getZNText() + "");
    }
}

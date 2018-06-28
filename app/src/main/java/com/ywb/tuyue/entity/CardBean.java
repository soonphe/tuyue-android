package com.ywb.tuyue.entity;

/**
 * Description:主界面中模块划分使用的卡片实体类
 * Created by wcystart on 2018/6/21.
 */

public class CardBean {
    /**
     * 设置背景图片
     *
     * @param resId
     */
    private int resId;
    /**
     * 设置背景图片
     *
     * @param url
     */
    private String url;

    /**
     * 设置图标
     *
     * @param iconId
     */
    private int iconId;

    /**
     * 设置英字标题
     *
     * @param ENText
     */
    private String ENText;

    /**
     * 设置汉字标题
     *
     * @param ZNText
     */
    private String ZNText;



    public CardBean() {
    }

    public CardBean(int resId, String ENText, String ZNText) {
        this.resId = resId;
        this.ENText = ENText;
        this.ZNText = ZNText;
    }


    public CardBean(String url, String ENText, String ZNText) {
        this.url = url;
        this.ENText = ENText;
        this.ZNText = ZNText;
    }

    public CardBean(int resId, int iconId, String ENText, String ZNText) {
        this.resId = resId;
        this.iconId = iconId;
        this.ENText = ENText;
        this.ZNText = ZNText;
    }

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getIconId() {
        return iconId;
    }

    public void setIconId(int iconId) {
        this.iconId = iconId;
    }

    public String getENText() {
        return ENText;
    }

    public void setENText(String ENText) {
        this.ENText = ENText;
    }

    public String getZNText() {
        return ZNText;
    }

    public void setZNText(String ZNText) {
        this.ZNText = ZNText;
    }
}

package com.ywb.tuyue.entity;

import org.litepal.annotation.Column;
import org.litepal.crud.LitePalSupport;

/**
 * @Author soonphe
 * @Date 2018-08-21 09:47
 * @Description 广告
 */
public class TAdvert extends LitePalSupport {


    /**
     * id : 73
     * typeid : 1
     * title : 锁屏广告
     * picurl : /img/20180727/1532655515.jpg
     * sort : 1
     * click : 37
     * createtime : 2018-07-06 15:06:29
     * updatetime : 2018-08-06 17:33:43
     * delflag : false
     * content : <p><img src="http://192.168.1.6/upload/img/20180802/1533192019.jpg"></p>
     * typename : 锁屏广告
     */
    @Column(unique = true, defaultValue = "unknown")
    private int id;
    private int typeid;
    private String title;
    private String picurl;
    private int sort;
    private int click;
    private String createtime;
    private String updatetime;
    private boolean delflag;
    private String content;
    private String typename;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTypeid() {
        return typeid;
    }

    public void setTypeid(int typeid) {
        this.typeid = typeid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPicurl() {
        return picurl;
    }

    public void setPicurl(String picurl) {
        this.picurl = picurl;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public int getClick() {
        return click;
    }

    public void setClick(int click) {
        this.click = click;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(String updatetime) {
        this.updatetime = updatetime;
    }

    public boolean isDelflag() {
        return delflag;
    }

    public void setDelflag(boolean delflag) {
        this.delflag = delflag;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTypename() {
        return typename;
    }

    public void setTypename(String typename) {
        this.typename = typename;
    }
}

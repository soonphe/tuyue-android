package com.ywb.tuyue.entity;

/**
 * Description:解锁屏页面的实体类
 * Created by wcystart on 2018/6/20.
 */

public class TAdvertData {

    /**
     * id : 5
     * type : 1
     * title : 途悦2
     * picurl : /pictures/20180511/1526026781.jpg
     * sort : null
     * createtime : 2018-05-11 16:20:09
     * updatetime : 2018-05-14 21:47:01
     * delflag : false
     * content : <p><img src="//47.98.121.127/pictures/20180511/1526026792.jpg"></p>
     */

    private int id;
    private int type;
    private String title;
    private String picurl;
    private Object sort;
    private String createtime;
    private String updatetime;
    private boolean delflag;
    private String content;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
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

    public Object getSort() {
        return sort;
    }

    public void setSort(Object sort) {
        this.sort = sort;
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

}

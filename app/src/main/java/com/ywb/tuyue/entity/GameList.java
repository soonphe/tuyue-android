package com.ywb.tuyue.entity;

/**
 * Created by JuZhongJoy on 2018/6/29.
 * 游戏列表
 */

public class GameList {


    /**
     * id : 1
     * typeid : 1
     * name : 捕鱼达人
     * picurl : /pictures/20180511/1526029665.png
     * filepath : /movie/20180511/1526029718.zip
     * createtime : 2018-05-11 17:10:28
     * updatetime : 2018-06-28 18:09:40
     * delflag : null
     */

    private int id;
    private int typeid;
    private String name;
    private String picurl;
    private String filepath;
    private String createtime;
    private String updatetime;
    private Object delflag;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPicurl() {
        return picurl;
    }

    public void setPicurl(String picurl) {
        this.picurl = picurl;
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
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

    public Object getDelflag() {
        return delflag;
    }

    public void setDelflag(Object delflag) {
        this.delflag = delflag;
    }
}

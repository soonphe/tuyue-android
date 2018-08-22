package com.ywb.tuyue.entity;


import org.litepal.crud.LitePalSupport;

/**
 * @Author soonphe
 * @Date 2018-08-21 09:57
 * @Description 小说
 */
public class TBook  extends LitePalSupport {

    /**
     * id : 44
     * typeid : 1
     * name : 慕川向晚
     * author : 姒锦
     * picurl : /img/20180807/1533610461.jpg
     * filepath : null
     * click : 0
     * createtime : 2018-08-07 10:54:28
     * updatetime : 2018-08-07 10:54:28
     * delflag : false
     * typename : 网络小说
     */

    private int id;
    private int typeid;
    private String name;
    private String author;
    private String picurl;
    private String filepath;
    private int click;
    private String createtime;
    private String updatetime;
    private boolean delflag;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
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

    public String getTypename() {
        return typename;
    }

    public void setTypename(String typename) {
        this.typename = typename;
    }
}

package com.ywb.tuyue.entity;

import org.litepal.crud.LitePalSupport;

/**
 * @Author soonphe
 * @Date 2018-08-21 09:54
 * @Descprition 城铁文章
 */
public class TArticle  extends LitePalSupport {

    /**
     * id : 12
     * typeid : 17
     * title : 文明乘车提醒
     * picurl : /img/20180727/1532659862.png
     * classify : 0
     * click : 0
     * createtime : 2018-05-11 19:01:02
     * updatetime : 2018-08-21 09:55:23
     * delflag : false
     * pathfile :
     * content : <p><img src="http://192.168.1.6/upload/img/20180731/1533006204.png"></p>
     * typename : 旅客须知
     */

    private int id;
    private int typeid;
    private String title;
    private String picurl;
    private int classify;
    private int click;
    private String createtime;
    private String updatetime;
    private boolean delflag;
    private String pathfile;
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

    public int getClassify() {
        return classify;
    }

    public void setClassify(int classify) {
        this.classify = classify;
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

    public String getPathfile() {
        return pathfile;
    }

    public void setPathfile(String pathfile) {
        this.pathfile = pathfile;
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

package com.ywb.tuyue.entity;


import org.litepal.crud.LitePalSupport;

/**
 * @Author soonphe
 * @Date 2018-08-21 09:57
 * @Description 城市
 */
public class TCity extends LitePalSupport {

    /**
     * id : 9
     * name : 西安
     * picurl : /img/20180727/1532657627.png
     * click : 0
     * createtime : 2018-05-11 18:35:23
     * updatetime : 2018-08-22 14:36:46
     * delflag : false
     * content : <p class="ql-align-justify" style="text-align: center;"><img src="http://192.168.1.6/upload/img/20180731/1533024831.png" style="max-width:100%;"><br></p>
     */

    private int id;
    private String name;
    private String picurl;
    private int click;
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
}

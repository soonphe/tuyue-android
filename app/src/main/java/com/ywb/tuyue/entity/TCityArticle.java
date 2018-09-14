package com.ywb.tuyue.entity;


import com.google.gson.annotations.SerializedName;

import org.litepal.crud.LitePalSupport;

import java.io.Serializable;

/**
 * @Author soonphe
 * @Date 2018-08-21 09:57
 * @Description 城市文章
 */
public class TCityArticle  extends LitePalSupport implements Serializable {

    /**
     * id : 169
     * cityid : 9
     * typeid : 0
     * title : 长安的历史名人都住哪？
     * intro : 说不定跟你还是邻居呢！
     * picurl : /advert/20180731/1533009270.jpg
     * click : 0
     * createtime : 2018-07-31 11:54:32
     * updatetime : 2018-08-03 09:44:38
     * content : <p style="text-align: center;"><img src="http://192.168.1.6/upload/img/20180731/1533009257.png" style="max-width:100%;"><br></p>
     */
    @SerializedName("id")   //真实ID
    private int tid;
    @SerializedName("myid")
    private int id;
    private int cityid;
    private int typeid;
    private String title;
    private String intro;
    private String picurl;
    private int click;
    private String createtime;
    private String updatetime;
    private String content;

    private String downloadPic; //图片地址
    private String downloadContent;    //富文本地址

    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

    public String getDownloadPic() {
        return downloadPic;
    }

    public void setDownloadPic(String downloadPic) {
        this.downloadPic = downloadPic;
    }

    public String getDownloadContent() {
        return downloadContent;
    }

    public void setDownloadContent(String downloadContent) {
        this.downloadContent = downloadContent;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCityid() {
        return cityid;
    }

    public void setCityid(int cityid) {
        this.cityid = cityid;
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

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

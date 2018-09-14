package com.ywb.tuyue.entity;

import com.google.gson.annotations.SerializedName;

import org.litepal.crud.LitePalSupport;

import java.io.Serializable;

/**
 * @Author soonphe
 * @Date 2018-08-21 09:53
 * @Description 游戏
 */
public class TGame  extends LitePalSupport implements Serializable {


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
    @SerializedName("id")   //真实ID
    private int tid;
    @SerializedName("myid")
    private int id;
    private int typeid;
    private String name;
    private String picurl;
    private String filepath;
    private String createtime;
    private String updatetime;
    private Boolean delflag;

    private String downloadPic; //图片地址
    private String downloadFile;    //压缩包地址

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

    public String getDownloadFile() {
        return downloadFile;
    }

    public void setDownloadFile(String downloadFile) {
        this.downloadFile = downloadFile;
    }

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

    public Boolean getDelflag() {
        return delflag;
    }

    public void setDelflag(Boolean delflag) {
        this.delflag = delflag;
    }
}

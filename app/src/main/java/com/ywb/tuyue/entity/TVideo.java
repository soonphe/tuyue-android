package com.ywb.tuyue.entity;


import com.google.gson.annotations.SerializedName;

import org.litepal.crud.LitePalSupport;

/**
 * @Author soonphe
 * @Date 2018-08-21 09:57
 * @Description 视频
 */
public class TVideo  extends LitePalSupport {


    /**
     * id : 14
     * typeid : 46
     * name : 腿软作死系列
     * posterurl : /img/20180727/1532658949.jpg
     * profile : 据说只有2%的人能坚持看完
     * director : Instreet
     * actor : olegcricket
     * detail : olegcricket个人集锦：49楼高空跳跃、多伦多顶层飞跃、香港自拍杆行动、香港BMX玩耍、迪拜与刺激、板上危机。
     * filepath : /video/tuiruan.mp4
     * click : 0
     * createtime : 2018-05-14 15:44:44
     * updatetime : 2018-08-22 14:40:41
     * delflag : false
     * typename : 小视频
     */
    @SerializedName("id")   //真实ID
    private int tid;
    @SerializedName("myid")
    private int id;
    private int typeid;
    private String name;
    private String posterurl;
    private String profile;
    private String director;
    private String actor;
    private String detail;
    private String filepath;
    private int click;
    private String createtime;
    private String updatetime;
    private boolean delflag;
    private String typename;

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

    public String getPosterurl() {
        return posterurl;
    }

    public void setPosterurl(String posterurl) {
        this.posterurl = posterurl;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getActor() {
        return actor;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
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

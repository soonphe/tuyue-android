package com.ywb.tuyue.entity;

import com.google.gson.annotations.SerializedName;

import org.litepal.crud.LitePalSupport;

import java.io.Serializable;

/**
 * @Author soonphe
 * @Date 2018-08-29 10:24
 * @Descprition 1905电影
 */

public class TMovie extends LitePalSupport implements Serializable {


    /**
     * id : 4701
     * file_name : 我最好朋友的婚礼
     * save_name : http://192.168.1.6:8088/data/micro_ticket/dst/gao_tie/480p_2/c3dfde5f6c70e1b23f90eeeca5a72bbd.mp4
     * size : 412428034
     * time : 5247
     * initial : WZHPYDHL
     * direct : 陈飞宏
     * starring : 舒淇,冯绍峰,宋茜,凤小岳
     * country :
     * film_type : 剧情,喜剧,爱情
     * play_type : 1
     * language : 中文
     * release_date : 2016-08-05
     * drama_cn : 影片改编自好莱坞同名经典爱情喜剧。顾佳是国内某时尚杂志的新任主编，奉命去米兰参加时装周。一通意外的电话让她刚刚落地就放弃了工作安排飞往伦敦。因为她曾经逃避但其实内心深爱的男人林然 马上就要和一位年轻富家女萱萱结婚，她想在婚礼之前把新郎抢回来。在飞往伦敦的飞机上，顾佳邂逅了型男Nick，她一直出糗，令Nick非常尴尬。见面后，林然的准新娘萱萱却待她亲如姐妹。尽管内心矛盾，顾佳还是有意无意的制造着麻烦，并因为一个误会使婚礼无法在英国如期举行，但林然和萱萱情比金坚，顾佳决定安排林然和萱萱去意大利办婚礼。在米兰，顾佳鼓起勇气和林然表白，却意外被萱萱撞见，三人在街头展开了追逐……
     * score : 7.1
     * face_pic : http://192.168.1.6:8087/Uploads/pic/gao_tie/480p_2/thumb_m_1526545378.jpg
     * sort : 255
     * ctime : 1525944346
     * uptime : 1525944346
     * play_times : 0
     * down_status : 2
     * down_time : 1526663102
     * pic_down_status : 1
     * file_path : /data/micro_ticket/dst/gao_tie/480p_2/c3dfde5f6c70e1b23f90eeeca5a72bbd.mp4
     */
    @SerializedName("id")   //真实ID
    private String tid;
    @SerializedName("myid")
    private int id;
    private String file_name;
    private String save_name;
    private String size;
    private String time;
    private String initial;
    private String direct;
    private String starring;
    private String country;
    private String film_type;
    private String play_type;
    private String language;
    private String release_date;
    private String drama_cn;
    private String score;
    private String face_pic;
    private String sort;
    private String ctime;
    private String uptime;
    private String play_times;
    private String down_status;
    private String down_time;
    private String pic_down_status;
    private String file_path;

    private String downloadPic; //图片地址
    private String downloadFile;    //压缩包地址

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getFile_name() {
        return file_name;
    }

    public void setFile_name(String file_name) {
        this.file_name = file_name;
    }

    public String getSave_name() {
        return save_name;
    }

    public void setSave_name(String save_name) {
        this.save_name = save_name;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getInitial() {
        return initial;
    }

    public void setInitial(String initial) {
        this.initial = initial;
    }

    public String getDirect() {
        return direct;
    }

    public void setDirect(String direct) {
        this.direct = direct;
    }

    public String getStarring() {
        return starring;
    }

    public void setStarring(String starring) {
        this.starring = starring;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getFilm_type() {
        return film_type;
    }

    public void setFilm_type(String film_type) {
        this.film_type = film_type;
    }

    public String getPlay_type() {
        return play_type;
    }

    public void setPlay_type(String play_type) {
        this.play_type = play_type;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public String getDrama_cn() {
        return drama_cn;
    }

    public void setDrama_cn(String drama_cn) {
        this.drama_cn = drama_cn;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getFace_pic() {
        return face_pic;
    }

    public void setFace_pic(String face_pic) {
        this.face_pic = face_pic;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getCtime() {
        return ctime;
    }

    public void setCtime(String ctime) {
        this.ctime = ctime;
    }

    public String getUptime() {
        return uptime;
    }

    public void setUptime(String uptime) {
        this.uptime = uptime;
    }

    public String getPlay_times() {
        return play_times;
    }

    public void setPlay_times(String play_times) {
        this.play_times = play_times;
    }

    public String getDown_status() {
        return down_status;
    }

    public void setDown_status(String down_status) {
        this.down_status = down_status;
    }

    public String getDown_time() {
        return down_time;
    }

    public void setDown_time(String down_time) {
        this.down_time = down_time;
    }

    public String getPic_down_status() {
        return pic_down_status;
    }

    public void setPic_down_status(String pic_down_status) {
        this.pic_down_status = pic_down_status;
    }

    public String getFile_path() {
        return file_path;
    }

    public void setFile_path(String file_path) {
        this.file_path = file_path;
    }
}

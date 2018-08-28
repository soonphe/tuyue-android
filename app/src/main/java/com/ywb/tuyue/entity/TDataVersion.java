package com.ywb.tuyue.entity;

import com.google.gson.annotations.SerializedName;

import org.litepal.crud.LitePalSupport;

/**
 * @Author soonphe
 * @Date 2018-08-22 11:46
 * @Descprition 数据版本
 */

public class TDataVersion  extends LitePalSupport {


    /**
     * id : 2
     * advertversion : 72
     * dataversion : 4
     * updatetime : 2018-08-16 17:32:38
     */
    @SerializedName("id")   //真实ID
    private int tid;
    @SerializedName("myid")
    private int id;
    private int advertversion;
    private int dataversion;
    private String updatetime;

    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAdvertversion() {
        return advertversion;
    }

    public void setAdvertversion(int advertversion) {
        this.advertversion = advertversion;
    }

    public int getDataversion() {
        return dataversion;
    }

    public void setDataversion(int dataversion) {
        this.dataversion = dataversion;
    }

    public String getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(String updatetime) {
        this.updatetime = updatetime;
    }
}

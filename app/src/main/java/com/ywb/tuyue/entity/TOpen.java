package com.ywb.tuyue.entity;

import org.litepal.crud.LitePalSupport;

/**
 * @Author soonphe
 * @Date 2018-08-30 15:22
 * @Descprition 解锁统计
 */

public class TOpen  extends LitePalSupport {


    /**
     * createdate : 2018-08-30T06:17:30.551Z
     * id : 0
     * imcode : string
     * ismobile : true
     * openlock : 0
     */

    private String createdate;
    private int id;
    private String imcode;
    private boolean ismobile;
    private int openlock;

    public String getCreatedate() {
        return createdate;
    }

    public void setCreatedate(String createdate) {
        this.createdate = createdate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImcode() {
        return imcode;
    }

    public void setImcode(String imcode) {
        this.imcode = imcode;
    }

    public boolean isIsmobile() {
        return ismobile;
    }

    public void setIsmobile(boolean ismobile) {
        this.ismobile = ismobile;
    }

    public int getOpenlock() {
        return openlock;
    }

    public void setOpenlock(int openlock) {
        this.openlock = openlock;
    }
}

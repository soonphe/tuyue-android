package com.ywb.tuyue.dto;

/**
 * @Author soonphe
 * @Date 2018-08-31 19:12
 * @Descprition
 */

public class TpenDto {

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

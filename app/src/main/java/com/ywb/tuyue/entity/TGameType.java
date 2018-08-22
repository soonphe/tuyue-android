package com.ywb.tuyue.entity;

import org.litepal.crud.LitePalSupport;

/**
 * @Author soonphe
 * @Date 2018-08-21 09:48
 * @Description 游戏类型
 */
public class TGameType  extends LitePalSupport {
    /**
     * id : 1
     * name : 锁屏广告
     * createtime : 2018-06-22 14:22:24
     * updatetime : 2018-06-22 14:26:59
     * delflag : false
     */

    private int id;
    private String name;
    private String createtime;
    private String updatetime;
    private boolean delflag;

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
}

package com.ywb.tuyue.entity;

/**
 * @Author soonphe
 * @Date 2018-08-21 09:48
 * @Description 途悦类型（广告，电影，小说，游戏，点餐，城市，城铁文章）
 */
public class TTuyueType {
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

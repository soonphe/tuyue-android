package com.ywb.tuyue.entity;

import java.util.List;

/**
 * Created by JuZhongJoy on 2018/6/28.
 * 游戏类别
 */

public class GameType {

    /**
     * id : 1
     * name : 动作
     * createtime : 2018-06-28 14:29:25
     * updatetime : 2018-06-28 14:29:25
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

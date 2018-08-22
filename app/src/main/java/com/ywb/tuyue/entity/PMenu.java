package com.ywb.tuyue.entity;

import java.io.Serializable;

/**
 * @Author soonphe
 * @Date 2017-12-27 20:01
 * @Descprition pad左部菜单实体
 */

public class PMenu implements Serializable {

    private int menuId;
    private String name;


    public PMenu(int menuId, String name) {
        this.menuId = menuId;
        this.name = name;
    }


    public int getMenuId() {
        return menuId;
    }

    public void setMenuId(int menuId) {
        this.menuId = menuId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}

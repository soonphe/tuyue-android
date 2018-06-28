package com.ywb.tuyue.vo;

/**
 * @Author soonphe
 * @Date 2018-04-23 18:48
 * @Descprition 帮助查询VO
 */

public class PClickSearchVO extends PCommonSearchVO{

    private int userId;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

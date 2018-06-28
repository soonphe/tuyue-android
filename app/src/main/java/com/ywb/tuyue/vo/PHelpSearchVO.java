package com.ywb.tuyue.vo;

/**
 * @Author soonphe
 * @Date 2018-04-23 18:48
 * @Descprition 帮助查询VO
 */

public class PHelpSearchVO extends PCommonSearchVO{

    private String name;

    public PHelpSearchVO(int start) {
        super(start);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

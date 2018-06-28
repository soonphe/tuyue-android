package com.ywb.tuyue.vo;

/**
 * @Author soonphe
 * @Date 2018-04-23 18:48
 * @Descprition 商品收藏查询VO
 */

public class PCollectSearchVO extends PCommonSearchVO{

    private int userId;
    private String name;

    public PCollectSearchVO(int start, int userId) {
        super(start);
        this.userId = userId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

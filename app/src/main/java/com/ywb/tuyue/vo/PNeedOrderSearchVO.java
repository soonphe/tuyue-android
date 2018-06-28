package com.ywb.tuyue.vo;

/**
 * @Author soonphe
 * @Date 2018-04-03 21:36
 * @Descprition 需求订单查询VO
 */

public class PNeedOrderSearchVO extends PCommonSearchVO {

    private int state;
    private String name;
    private int userId;

    public PNeedOrderSearchVO(int start, int state) {
        super(start);
        this.state = state;
    }

    public PNeedOrderSearchVO(int start, int userId, int state) {
        super(start);
        this.userId = userId;
        this.state = state;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}

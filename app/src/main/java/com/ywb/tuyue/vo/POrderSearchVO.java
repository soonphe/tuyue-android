package com.ywb.tuyue.vo;

/**
 * @Author soonphe
 * @Date 2018-04-03 21:36
 * @Descprition 订单查询VO
 */

public class POrderSearchVO extends PCommonSearchVO {

    private int state;
    private String name;
    private int userId;
    private int storeId;

    public POrderSearchVO(int start, int state) {
        super(start);
        this.state = state;
    }

    public POrderSearchVO(int start, int userId, int state) {
        super(start);
        this.userId = userId;
        this.state = state;
    }

    public POrderSearchVO(int start,int length, int storeId, int state) {
        super(start,length);
        this.storeId = storeId;
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

    public int getStoreId() {
        return storeId;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }
}

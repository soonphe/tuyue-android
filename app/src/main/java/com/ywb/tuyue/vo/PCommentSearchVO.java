package com.ywb.tuyue.vo;

/**
 * @Author soonphe
 * @Date 2018-04-23 18:48
 * @Descprition 商品评论查询VO
 */

public class PCommentSearchVO extends PCommonSearchVO{

    private int goodsId;
    private String name;

    public PCommentSearchVO(int start, int goodsId) {
        super(start);
        this.goodsId = goodsId;
    }

    public int getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(int goodsId) {
        this.goodsId = goodsId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

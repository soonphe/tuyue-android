package com.ywb.tuyue.vo;

/**
 * @Author soonphe
 * @Date 2018-04-03 21:36
 * @Descprition 商品查询VO
 */

public class PGoodsSearchVO extends PCommonSearchVO{

    /**
     * categoryId : 0
     * name : string
     * storeId : 0
     */

    private int categoryId;
    private String name;
    private int storeId;

    public PGoodsSearchVO(int start, int storeId) {
        super(start);
        this.storeId = storeId;
    }

    public PGoodsSearchVO(int start,int length, int categoryId,String name) {
        super(start,length);
        this.categoryId = categoryId;
        this.name=name;
    }
    public PGoodsSearchVO(int start,String name) {
        super(start);
        this.name=name;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStoreId() {
        return storeId;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }
}

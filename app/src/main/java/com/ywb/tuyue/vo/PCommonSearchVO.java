package com.ywb.tuyue.vo;

import com.ywb.tuyue.constants.Constants;

/**
 * @Author soonphe
 * @Date 2018-03-29 21:05
 * @Descprition 公共分页查询条件——携带页码与页长
 */

public class PCommonSearchVO {

    private int pageNum = 0;//页码
    private int pageSize = Constants.PAGE_SIZE;//页长

    public PCommonSearchVO(int pageNum, int pageSize) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
    }

    public PCommonSearchVO(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}

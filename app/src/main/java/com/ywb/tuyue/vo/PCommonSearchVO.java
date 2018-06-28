package com.ywb.tuyue.vo;

/**
 * @Author soonphe
 * @Date 2018-03-29 21:05
 * @Descprition 公共分页查询条件
 */

public class PCommonSearchVO {

    private int page = 0;//页码
    private int start = 0;//起始位置
    private int length = 15;//每页条目数
    private int total = 0;//总条目数

    public PCommonSearchVO(){

    }
    public PCommonSearchVO(int start){
        this.start=start;
    }

    public PCommonSearchVO(int start,int length){
        this.start=start;
        this.length=length;
    }

    public static PCommonSearchVO getInstance(int start) {
        return new PCommonSearchVO(start);
    }

    public static PCommonSearchVO getInstance(int start,int length) {
        return new PCommonSearchVO(start,length);
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}

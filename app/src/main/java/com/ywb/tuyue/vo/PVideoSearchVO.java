package com.ywb.tuyue.vo;

/**
 * @Author soonphe
 * @Date 2018-04-03 21:36
 * @Descprition 视频查询VO
 */

public class PVideoSearchVO extends PCommonSearchVO{


    private int type;
    private String name;

    public PVideoSearchVO(int pageNum) {
        super(pageNum);
    }

    public PVideoSearchVO(int pageNum, int type) {
        super(pageNum);
        this.type = type;
    }


}

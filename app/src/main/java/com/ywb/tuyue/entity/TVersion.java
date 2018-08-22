package com.ywb.tuyue.entity;

import org.litepal.crud.LitePalSupport;

/**
 * @Author soonphe
 * @Date 2018-08-22 11:46
 * @Descprition 版本信息
 */

public class TVersion  extends LitePalSupport {


    /**
     * id : 2
     * versioncode : 58
     * name : v59.0.1
     * content : 1.wifi识别调整 2.头部标识修正 3.统计添加基数
     * filepath : /apk/tuyue5801.apk
     * createtime : 2018-07-13 09:43:15
     */

    private int id;
    private String versioncode;
    private String name;
    private String content;
    private String filepath;
    private String createtime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVersioncode() {
        return versioncode;
    }

    public void setVersioncode(String versioncode) {
        this.versioncode = versioncode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }
}

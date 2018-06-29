package com.ywb.tuyue.entity;

import java.util.List;

/**
 * Description:书籍类型
 * Created by wcystart on 2018/6/29.
 */

public class TBookType {

    /**
     * resultCode : 200
     * message : Success
     * data : []
     * ext :
     */

    private String resultCode;
    private String message;
    private String ext;
    private List<?> data;

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getExt() {
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }

    public List<?> getData() {
        return data;
    }

    public void setData(List<?> data) {
        this.data = data;
    }
}

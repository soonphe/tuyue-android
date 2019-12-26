package com.ywb.tuyue.constants.enums;

public enum DownloadEventEnum {
    /**
     * 下载状态
     */
    START_DOWNLOAD,
    FINISH_DOWNLOAD,
    DOWNLOAD_ALL_FINISH;

    private Object object;

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    @Override
    public String toString() {
        return "CommonEvent{" +
                "object=" + object +
                '}';
    }
}
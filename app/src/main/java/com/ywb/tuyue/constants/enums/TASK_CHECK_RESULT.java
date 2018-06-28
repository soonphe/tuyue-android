package com.ywb.tuyue.constants.enums;

public enum TASK_CHECK_RESULT {
    NORMAL(0), ABNORMAL(1);
    final int value;

    TASK_CHECK_RESULT(int i) {
        this.value = i;
    }

    public int value() {
        return value;
    }
}
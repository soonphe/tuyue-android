package com.ywb.tuyue.constants.enums;

public enum TaskStatusEnum {

    NOCHECK(0),
    CHECKING(1),
    CHECKDONE(2);

    final int value;

    TaskStatusEnum(int value) {
        this.value = value;
    }

    public int value() {
        return value;
    }
}








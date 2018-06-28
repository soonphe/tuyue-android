package com.ywb.tuyue.constants.enums;

public enum TASK_STATUS {
        NOCHECK(0), CHECKING(1),
        CHECKDONE(2);
        final int value;

        TASK_STATUS(int value) {
            this.value = value;
        }

        public int value() {
            return value;
        }
    }








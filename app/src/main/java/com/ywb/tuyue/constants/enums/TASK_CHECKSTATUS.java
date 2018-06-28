package com.ywb.tuyue.constants.enums;

public enum TASK_CHECKSTATUS {
        NOCHECK(0), CHECKDONE(2);
        final int value;

        TASK_CHECKSTATUS(int i) {
            this.value = i;
        }

        public int value() {
            return value;
        }
    }
package com.ywb.tuyue.constants.enums;

public enum TASK_SYNCUPLOAD {
        NOUPLOAD(0), UPLOADDONE(2);
        final int value;

        TASK_SYNCUPLOAD(int i) {
            this.value = i;
        }

        public int value() {
            return value;
        }
    }
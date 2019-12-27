package com.ywb.tuyue.entity;

public class TWorkBench {


    /**
     * status : 0
     * message : success
     * data : {"waitToStoreClue":1,"newClue":1,"waitToFollowClue":1,"overdueClue":1}
     */

    private int status;
    private String message;
    private DataBean data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * waitToStoreClue : 1
         * newClue : 1
         * waitToFollowClue : 1
         * overdueClue : 1
         */

        private int waitToStoreClue;
        private int newClue;
        private int waitToFollowClue;
        private int overdueClue;

        public int getWaitToStoreClue() {
            return waitToStoreClue;
        }

        public void setWaitToStoreClue(int waitToStoreClue) {
            this.waitToStoreClue = waitToStoreClue;
        }

        public int getNewClue() {
            return newClue;
        }

        public void setNewClue(int newClue) {
            this.newClue = newClue;
        }

        public int getWaitToFollowClue() {
            return waitToFollowClue;
        }

        public void setWaitToFollowClue(int waitToFollowClue) {
            this.waitToFollowClue = waitToFollowClue;
        }

        public int getOverdueClue() {
            return overdueClue;
        }

        public void setOverdueClue(int overdueClue) {
            this.overdueClue = overdueClue;
        }
    }
}

package com.ywb.tuyue.ui.mvp;

/**
 * Created by wushange on 2016/08/16.
 */
public interface BaseEvents {
    void setObj(Object obj);

    Object getObj();

    enum CommonEvent implements BaseEvents {
        START_DOWNLOAD, FINISH_DOWNLOAD, DOWNLOAD_ALL_FINISH;

        private Object object;

        @Override
        public void setObj(Object obj) {

            this.object = obj;
        }

        @Override
        public Object getObj() {
            return object;
        }

        @Override
        public String toString() {
            return "CommonEvent{" +
                    "object=" + object +
                    '}';
        }
    }

}

package com.ywb.tuyue.ui.mvp;

/**
 * Created by wushange on 2016/08/16.
 */
public interface BaseEvents {
    void setObj(Object obj);

    Object getObj();

    enum CommonEvent implements BaseEvents {
        UPDATE_LIST,UPDATE_PCALSS;

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

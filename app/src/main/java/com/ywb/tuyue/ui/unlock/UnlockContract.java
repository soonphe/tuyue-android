package com.ywb.tuyue.ui.unlock;

import com.ywb.tuyue.entity.TOpen;
import com.ywb.tuyue.ui.mvp.BasePView;

import java.util.List;

/**
 * @Author anna
 * @Date 2017-12-06 14:02
 * @Description 解锁页
 */
public interface UnlockContract {

    interface UnlockView extends BasePView {
        void uploadDataSuccess();
    }

    interface UnlockPresenter {
        void uploadData(List<TOpen> list);
    }
}

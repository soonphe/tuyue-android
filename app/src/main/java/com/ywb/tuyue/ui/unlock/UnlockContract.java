package com.ywb.tuyue.ui.unlock;
import com.ywb.tuyue.entity.TUnlockAdvert;
import com.ywb.tuyue.ui.mvp.BasePView;
import java.util.List;

/**
 * @Author anna
 * @Date 2017-12-06 14:02
 * @Description 
 */
public interface UnlockContract {

    interface UnlockView extends BasePView {
        void getUnlockAdvertSuccess(TUnlockAdvert unlockAdvert);
    }

    interface UnlockPresenter {
        void getAdvert();
    }
}

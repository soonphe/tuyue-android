package com.ywb.tuyue.ui.unlock;
import android.content.Context;
import com.ywb.tuyue.api.AppApi;
import com.ywb.tuyue.di.PerActivity;
import com.ywb.tuyue.ui.mvp.BasePresenter;
import javax.inject.Inject;
/**
 * @Author anna
 * @Date 2017-12-06 13:54
 * @Description
 */
@PerActivity
public class UnlockPresenter extends BasePresenter<UnlockContract.UnlockView> implements UnlockContract.UnlockPresenter{
    Context context;
    private AppApi api;
    @Inject
    public UnlockPresenter(Context context, AppApi accountApi) {
        this.context = context;
        this.api = accountApi;
    }
    @Override
    public void getAdvert() {
        mView.startLoading();
        mDisposable.add(api.getUnlockAdvert(1).subscribe(adverts -> {
                    mView.endLoading();
                    mView.getUnlockAdvertSuccess(adverts);
                },
                throwable -> {
                    mView.onError(throwable.getMessage());
                    mView.endLoading();
            }
        ));
    }
}

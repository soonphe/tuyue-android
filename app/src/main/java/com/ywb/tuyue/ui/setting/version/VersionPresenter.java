package com.ywb.tuyue.ui.setting.version;

import com.blankj.utilcode.util.SPUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.FileCallback;
import com.lzy.okgo.model.Response;
import com.ywb.tuyue.api.AppApi;
import com.ywb.tuyue.di.PerActivity;
import com.ywb.tuyue.entity.TVersion;
import com.ywb.tuyue.ui.mvp.BasePresenter;

import org.litepal.LitePal;

import java.io.File;

import javax.inject.Inject;

import static com.ywb.tuyue.constants.Constants.NETWORK_AVAILABLE;

@PerActivity
public class VersionPresenter extends BasePresenter<VersionContract.View> implements VersionContract.Presenter {

    private AppApi api;

    @Inject
    public VersionPresenter(AppApi api) {
        this.api = api;
    }

    @Override
    public void getVersion() {
        //判断当前网络可用——可用则上传
        if (SPUtils.getInstance().getBoolean(NETWORK_AVAILABLE)) {
            mDisposable.add(api.getVersion().subscribe(list -> {
                        if (LitePal.findFirst(TVersion.class) != null) {
                            LitePal.deleteAll(TVersion.class);
                        }
                        list.save();
                        mView.getVersionSuccess(list);

                    },
                    throwable -> mView.onError(throwable.getMessage()))
            );
        }
    }

    @Override
    public void downloadApk(String downpath) {
        OkGo.<File>get(downpath)
                .tag(this)
                .execute(new FileCallback() {
                    @Override
                    public void onError(Response<File> response) {
                        super.onError(response);
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                    }

                    @Override
                    public void onSuccess(Response<File> response) {

                    }
                });
    }

}

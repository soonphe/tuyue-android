package com.ywb.tuyue.ui.advert;

import com.blankj.utilcode.util.LogUtils;
import com.ywb.tuyue.api.AppApi;
import com.ywb.tuyue.base.mvp.BasePresenter;
import com.ywb.tuyue.components.okhttp.OkHttpHelper;
import com.ywb.tuyue.di.PerActivity;

import javax.inject.Inject;

@PerActivity
public class AdvertPresenter extends BasePresenter<AdvertContract.View> implements AdvertContract.Presenter {

    private AppApi api;
    private OkHttpHelper okHttpHelper;

    @Inject
    public AdvertPresenter(AppApi accountApi,OkHttpHelper okHttpHelper) {
        this.api = accountApi;
        this.okHttpHelper = okHttpHelper;
    }


    @Override
    public void getAdvertListByType(int typeid) {
        mDisposable.add(
                api.getAdvertList(1000)
                        .subscribe(list -> {
                            LogUtils.e("list大小" + list.size());
                            mView.getAdvertListSuccess(list);
                        }, throwable -> mView.onError(throwable.getMessage()))
        );
         //测试自定义路进请求API（可用）
//        mDisposable.add(
//                api.getWorkBenchData().subscribe(obj -> {
//                    LogUtils.e("工作台Message:" + obj.getMessage());
//                }, throwable -> mView.onError(throwable.getMessage()))
//        );
         //测试Okhttp（可用）
//        Request request = new Request.Builder()
//                .url("https://jz-amp.daojia-inc.com/mock/459/workBench/getBenchData")
//                .get()
//                .build();
//        okHttpHelper.enqueue(request, new Callback(){
//
//            @Override
//            public void onFailure(Call call, IOException e) {
//                LogUtils.e("请求失败");
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                LogUtils.e("请求成功"+response.body().string());
//            }
//        });

//        List<TAdvert> list = LitePal.where("typeid=?", typeid+"").order("sort asc").find(TAdvert.class);
//        if (list.size() > 0) {
//            mView.getAdvertListSuccess(list);
//        } else {
//            mView.getAdvertListSuccess(new ArrayList<>());
//        }
    }
}

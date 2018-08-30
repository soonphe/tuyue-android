//package com.ywb.tuyue.services;
//
//import android.app.Service;
//import android.content.Intent;
//import android.os.IBinder;
//import android.support.annotation.Nullable;
//import android.text.TextUtils;
//import android.util.Log;
//
//import com.google.gson.Gson;
//import com.lzy.okgo.OkGo;
//import com.lzy.okgo.callback.StringCallback;
//import com.ywb.tuyue.constants.Constants;
//import com.ywb.tuyue.constants.IpConfig;
//import com.ywb.tuyue.db.dao.CountDao;
//import com.ywb.tuyue.entity.BaseBean;
//import com.ywb.tuyue.entity.Count;
//import com.ywb.tuyue.ui.main.MainActivity;
//import com.ywb.tuyue.utils.DateUtils;
//import com.ywb.tuyue.utils.DeviceUtils;
//import com.ywb.tuyue.utils.PreferencesUtils;
//
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.concurrent.TimeUnit;
//
//import io.reactivex.Observable;
//import io.reactivex.disposables.CompositeDisposable;
//import io.reactivex.observers.DisposableObserver;
//import io.reactivex.schedulers.Schedulers;
//
///**
// * @Author soonphe
// * @Date 2018-08-30 15:18
// * @Description 数据统计 开启服务上传统计数据
// */
//public class CountService extends Service {
//
//    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();//
//    @Nullable
//    @Override
//    public IBinder onBind(Intent intent) {
//        return null;
//    }
//
//    @Override
//    public void onCreate() {
//        super.onCreate();
//    }
//
//    @Override
//    public int onStartCommand(Intent intent, int flags, int startId) {
//
//        //数据上传之前判断是不是昨天，如果是的话清空数据库
//        String time = (String) PreferencesUtils.getString(getApplicationContext(), Constants.COUNT_TIME);
//        if (!TextUtils.isEmpty(time)) {
//            if (DateUtils.isYesterday(time)) {
//                Log.i("统计数据：", "是昨天");
//                CountDao.getInstance().deleteCount();
//                Count count = new Count();
//                count.setUnLock(0);
//                CountDao.getInstance().updateCount(count);
//                PreferencesUtils.putString(getApplicationContext(), Constants.COUNT_TIME, ""); //存储时间
//            } else {
//                send(); //当天数据上传
//            }
//        } else {
//            send();
//        }
//        //离线注册有网后上传
//        String regist = (String) PreferencesUtils.getString(getApplicationContext(), Constants.REGIST_SUCCESS);
//        if (!TextUtils.isEmpty(regist) && regist.equals(Constants.REGIST_FAIL)) {
//            MainActivity mainActivity = new MainActivity();
//            mainActivity.synReister();
//        }
//        return super.onStartCommand(intent, flags, startId);
//    }
//
//
//    private void send() {
//        mCompositeDisposable.add(Observable.interval(1, 1, TimeUnit.SECONDS)
//                .subscribeOn(Schedulers.io())
//                .observeOn(Schedulers.io())
//                .subscribeWith(getObserver()));
//    }
//
//    private DisposableObserver getObserver() {
//        DisposableObserver disposableObserver = new DisposableObserver<Object>() {
//
//            @Override
//            public void onNext(Object o) {
//                Log.d(TAG_1, "#####开始#####");
//                //for count
//
//                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//                Count lastCount = CountDao.getInstance().getLastCount();
//
//                if (lastCount != null) {
//                    //时间
//                    String format = sdf.format(new Date());
//                    lastCount.setCreateTime(format);
//                    //设备IMEI
//                    lastCount.setPadImei(DeviceUtils.getUniqueId(getApplicationContext()));
//                    //开机次数
//                  //  lastCount.setOpenPad(1); //开机统计
//
//                    //获取当前设备是否为4G设备（判断是否装有SIM卡）、
//                    boolean ishasSimCard = DeviceUtils.ishasSimCard(CountService.this);
//                    if(ishasSimCard==true){
//                        //有SIM卡   是4G
//                        device4GOrFei4G=0;
//                    }else{
//                        //无SIM卡  非4G
//                        device4GOrFei4G=1;
//                    }
//
//                    CountDao.getInstance().updateCount(lastCount);
//                } else {
//                    lastCount = new Count();
//                    //时间
//                    String format = sdf.format(new Date());
//                    lastCount.setCreateTime(format);
//                    //设备IMEI
//                    lastCount.setPadImei(DeviceUtils.getUniqueId(getApplicationContext()));
//                    //开机次数
//                   // lastCount.setOpenPad(0);
//                    CountDao.getInstance().updateCount(lastCount);
//                }
//
//                String countData = new Gson().toJson(CountDao.getInstance().getLastCount() == null ? "" : CountDao.getInstance().getLastCount());
//                Log.d(TAG_1, "统计数据: " + countData);
//                if (!TextUtils.isEmpty(countData)) {
//                    sendDataToServer(countData);
//                }
//            }
//
//            @Override
//            public void onComplete() {
//                Log.d(TAG_1, "onComplete");
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                Log.e(TAG_1, e.toString(), e);
//                PreferencesUtils.putString(getApplicationContext(), Constants.COUNT_TIME, "");
//            }
//        };
//
//        return disposableObserver;
//    }
//
//    /**
//     * 上传统计数据
//     * @param countData
//     */
//    private void sendDataToServer(String countData) {
//        String url = IpConfig.BASE_API_URL + IpConfig.SEND_COUNT;
//        try {
//
//            OkGo.<String>post(url).tag(this).upJson(countData).execute(new StringCallback() {
//                @Override
//                public void onSuccess(com.lzy.okgo.model.Response<String> response) {
//                    BaseBean baseBean = new Gson().fromJson(response.body(), BaseBean.class);
//                    if (baseBean.equals("200")) {
//                        Log.d(TAG_1, "#####发送成功#####");
//                        PreferencesUtils.putString(getApplicationContext(), Constants.COUNT_TIME, DateUtils.getDate().toString());
//                    }
//                }
//
//                @Override
//                public void onError(com.lzy.okgo.model.Response<String> response) {
//                    Log.d(TAG_1, "#####发送失败#####");
//                    PreferencesUtils.putString(getApplicationContext(), Constants.COUNT_TIME, "");
//                }
//            });
//        } catch (Exception e) {
//            e.printStackTrace();
//            PreferencesUtils.putString(getApplicationContext(), Constants.COUNT_TIME, "");
//        }
//    }
//}

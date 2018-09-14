package com.ywb.tuyue.ui.setting.network;


import android.Manifest;
import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.NetworkInfo;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.NetworkUtils;
import com.blankj.utilcode.util.SPUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.facebook.stetho.common.LogUtil;
import com.suke.widget.SwitchButton;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.ywb.tuyue.R;
import com.ywb.tuyue.constants.Constants;
import com.ywb.tuyue.entity.WifiBean;
import com.ywb.tuyue.ui.adapter.WifiListAdapter;
import com.ywb.tuyue.ui.mvp.BaseFragmentV4;
import com.ywb.tuyue.utils.CollectionUtils;
import com.ywb.tuyue.utils.WifiSupport;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @Author soonphe
 * @Date 2018-08-22 11:01
 * @Description 设置-网络设置
 */
public class NetworkFragment extends BaseFragmentV4 implements NetworkContract.View {

    @BindView(R.id.switch_button)
    SwitchButton mSwitchButton;
    @BindView(R.id.recy_list_wifi)
    RecyclerView mRecyWifiList;
//    private SwitchButton mSwitchButton;
//    RecyclerView mRecyWifiList;

    private WifiListAdapter mAdapter;

    private static final String TAG = "MainActivity";
    List<WifiBean> mWifiList = new ArrayList<>();

    private WifiBroadcastReceiver mWifiReceiver;
    private int mConnectType = 0;//1：连接成功？ 2 正在连接（如果wifi热点列表发生变需要该字段）
    private WifiManager mWifiManager;


    @Override
    public int bindLayout() {
        return R.layout.fragment_wifisetting;
    }

    @SuppressLint("CheckResult")
    @Override
    public void initView(View view) {

        mAdapter = new WifiListAdapter(R.layout.item_wifi_list); //传一个item的布局
        mRecyWifiList.setHasFixedSize(true);//当我们确定Item的改变不会影响RecyclerView的宽高的时候可以设置setHasFixedSize(true)，并通过Adapter的增删改插方法去刷新RecyclerView
        mRecyWifiList.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyWifiList.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        mRecyWifiList.setAdapter(mAdapter);
        mRecyWifiList.setNestedScrollingEnabled(false);
        mAdapter.setOnItemClickListener((adapter, view2, position) -> {
            WifiBean wifiBean = mWifiList.get(position);
            //如果wifi是  未连接  或者是   已连接状态
            if (wifiBean.getState().equals(Constants.WIFI_STATE_UNCONNECT)
                    || wifiBean.getState().equals(Constants.WIFI_STATE_CONNECT)) {

                String capabilities = mWifiList.get(position).getCapabilities(); //获取加密方式
                //如果 wifi 热点支持加密的方式为  不需要密码
                if (WifiSupport.getWifiCipher(capabilities) == WifiSupport.WifiCipherType.WIFICIPHER_NOPASS) {//无需密码
                    WifiConfiguration tempConfig = WifiSupport.isExsits(wifiBean.getWifiName(), getActivity()); //判断之前是否连接配置过某一个网络
                    if (tempConfig == null) { //如果没有配置过，就新创建配置一个
                        WifiConfiguration exsits = WifiSupport.createWifiConfig(wifiBean.getWifiName(), null, WifiSupport.WifiCipherType.WIFICIPHER_NOPASS);
                        WifiSupport.addNetWork(exsits, getActivity());
                    } else {
                        WifiSupport.addNetWork(tempConfig, getActivity());
                    }
                    //没有密码的 wifi 点击提示已连接
                    if (wifiBean.getState().equals(Constants.WIFI_STATE_CONNECT)) { //如果wifi已经处于连接状态
                        Toast.makeText(getActivity(), "当前wifi已连接", Toast.LENGTH_SHORT).show();
                        System.out.println("当前wifi已经连接" + "///////////////////////////");
                        return;
                    }
                } else {
                    //在这里做个判断，如果列表中的某一项wifi显示已经连接上了，则不用弹出密码输入框  ，
                    // 第一次连接一个新的wifi时，需弹框，还要就是之前连接成功过的，当密码被修改时在弹出框输入密码
                    if (wifiBean.getState().equals(Constants.WIFI_STATE_CONNECT)) { //如果wifi已经处于连接状态
                        Toast.makeText(getActivity(), "当前wifi已经连接", Toast.LENGTH_SHORT).show();
                        return;
                    } else {
                        //需要密码，弹出输入密码dialog
                        //如果之前输入过密码  就记录密码，下次再点击的时候，就不需要在弹框输入密码了，而是直接连接
                        WifiConfiguration tempConfig = WifiSupport.isExsits(wifiBean.getWifiName(), getActivity()); //判断之前是否连接配置过某一个网络
                        if (null != tempConfig) {
                            //直接连接
                        } else {
                            //才会弹框输入密码进行配置
                            noConfigurationWifi(position);
                        }
                    }
                }
            }
        });
        mSwitchButton.setOnCheckedChangeListener((view1, isChecked) -> {
                    if (isChecked) {
                        //动态申请权限
                        if (Build.VERSION.SDK_INT >= 23) {
                            new RxPermissions(getActivity()).request(
                                    Manifest.permission.ACCESS_FINE_LOCATION,
                                    Manifest.permission.ACCESS_COARSE_LOCATION
                            ).subscribe(granded -> {
                                if (granded) {
                                    //开启wifi
                                    NetworkUtils.setWifiEnabled(true);
                                } else {
                                    new MaterialDialog.Builder(getActivity())
                                            .content("是否立即去设置权限?")
                                            .positiveText(android.R.string.ok)
                                            .negativeText(android.R.string.cancel)
                                            .onPositive((dialog1, which) -> {
                                                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);   //跳转位置服务界面
                                                startActivity(intent);
                                            })
                                            .show();
                                }
                            });
                        }
                    }else{
                        //关闭wifi
                        NetworkUtils.setWifiEnabled(false);
                    }
                }
        );
        //Context通过getSystemService获取wifimanager
        mWifiManager = (WifiManager) getActivity().getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        //注册广播
        mWifiReceiver = new WifiBroadcastReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction(WifiManager.WIFI_STATE_CHANGED_ACTION);//监听wifi是开关变化的状态
        filter.addAction(WifiManager.NETWORK_STATE_CHANGED_ACTION);//监听wifi连接状态广播,是否连接了一个有效路由
        filter.addAction(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION);//监听wifi列表变化（开启一个热点或者关闭一个热点）
        getActivity().registerReceiver(mWifiReceiver, filter);
        //判断系统wifi是否开启
        if (!NetworkUtils.getWifiEnabled()) {
            mSwitchButton.setChecked(false);
        } else {
            mSwitchButton.setChecked(true);
            //初始化wifi列表
            sortScaResult();
        }
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void initInjector() {
        getComponent().inject(this);
    }

    @Override
    public void startLoading() {

    }

    @Override
    public void endLoading() {

    }

    @Override
    public void onError(String error) {

    }

    /**
     * 获取wifi列表然后将bean转成自己定义的WifiBean
     */
    public void sortScaResult() {
        mWifiManager.startScan();
        List<ScanResult> scanResults = WifiSupport.noSameName(WifiSupport.getWifiScanResult(getActivity()));
        mWifiList.clear();
        if (!CollectionUtils.isNullOrEmpty(scanResults)) {
            for (int i = 0; i < scanResults.size(); i++) {
                WifiBean wifiBean = new WifiBean();
                wifiBean.setWifiName(scanResults.get(i).SSID);
                wifiBean.setState(Constants.WIFI_STATE_UNCONNECT);   //只要获取都假设设置成未连接，真正的状态都通过广播来确定
                wifiBean.setCapabilities(scanResults.get(i).capabilities);
                wifiBean.setLevel(WifiSupport.getLevel(scanResults.get(i).level) + "");
                mWifiList.add(wifiBean);
            }
        }
        //排序
        Collections.sort(mWifiList);
        mAdapter.setNewData(mWifiList);
        //更新recycleView
//        mAdapter.notifyDataSetChanged();

    }

    /**
     * WifiBroadcastReceiver
     */
    public class WifiBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (WifiManager.WIFI_STATE_CHANGED_ACTION.equals(intent.getAction())) {
                int state = intent.getIntExtra(WifiManager.EXTRA_WIFI_STATE, 0);
                switch (state) {
                    /**
                     * WIFI_STATE_DISABLED    WLAN已经关闭
                     * WIFI_STATE_DISABLING   WLAN正在关闭
                     * WIFI_STATE_ENABLED     WLAN已经打开
                     * WIFI_STATE_ENABLING    WLAN正在打开
                     * WIFI_STATE_UNKNOWN     未知
                     */
                    case WifiManager.WIFI_STATE_DISABLED: {
                        LogUtils.e("已经关闭");
//                        sortScaResult();
                        mWifiList.clear();
                        mAdapter.setNewData(mWifiList);
                        mSwitchButton.setChecked(false);
                        break;
                    }
                    case WifiManager.WIFI_STATE_ENABLED: {
                        LogUtils.e("已经打开");
                        sortScaResult();
                        mSwitchButton.setChecked(true);
                        break;
                    }
                }
            } else if (WifiManager.NETWORK_STATE_CHANGED_ACTION.equals(intent.getAction())) {
                NetworkInfo info = intent.getParcelableExtra(WifiManager.EXTRA_NETWORK_INFO);
                Log.d(TAG, "--NetworkInfo--" + info.toString());
                if (NetworkInfo.State.DISCONNECTED == info.getState()) {//wifi没连接上
                    LogUtil.e(TAG, "wifi没连接上");
                    for (int i = 0; i < mWifiList.size(); i++) {//没连接上将 所有的连接状态都置为“未连接”
                        mWifiList.get(i).setState(Constants.WIFI_STATE_UNCONNECT);
                    }
                    if (mAdapter != null) {
                        mAdapter.notifyDataSetChanged();
                    }
                } else if (NetworkInfo.State.CONNECTED == info.getState()) {//wifi连接上了
                    LogUtil.e(TAG, "wifi连接上了");
                    WifiInfo connectedWifiInfo = WifiSupport.getConnectedWifiInfo(getActivity());
                    mConnectType = 1;
                    wifiListSet(connectedWifiInfo.getSSID(), mConnectType);
                } else if (NetworkInfo.State.CONNECTING == info.getState()) {//正在连接
                    LogUtil.e(TAG, "wifi正在连接");
                    WifiInfo connectedWifiInfo = WifiSupport.getConnectedWifiInfo(getActivity());
                    mConnectType = 2;
                    wifiListSet(connectedWifiInfo.getSSID(), mConnectType);
                }
            } else if (WifiManager.SCAN_RESULTS_AVAILABLE_ACTION.equals(intent.getAction())) {
                Log.d(TAG, "网络列表变化了");
                wifiListChange();
            }
        }
    }

    /**
     * 网络状态发生改变 调用此方法！
     */
    public void wifiListChange() {
        sortScaResult();
        WifiInfo connectedWifiInfo = WifiSupport.getConnectedWifiInfo(getActivity());
        if (connectedWifiInfo != null) {
            wifiListSet(connectedWifiInfo.getSSID(), mConnectType);
        }
    }

    /**
     * 将"已连接"或者"正在连接"的wifi热点放置在第一个位置
     *
     * @param wifiName
     * @param type
     */
    public void wifiListSet(String wifiName, int type) {
        int index = -1;
        WifiBean wifiInfo = new WifiBean();
        if (CollectionUtils.isNullOrEmpty(mWifiList)) {
            return;
        }
        for (int i = 0; i < mWifiList.size(); i++) {
            mWifiList.get(i).setState(Constants.WIFI_STATE_UNCONNECT);
        }
        Collections.sort(mWifiList);//根据信号强度排序
        for (int i = 0; i < mWifiList.size(); i++) {
            WifiBean wifiBean = mWifiList.get(i);
            if (index == -1 && ("\"" + wifiBean.getWifiName() + "\"").equals(wifiName)) {
                index = i;
                wifiInfo.setLevel(wifiBean.getLevel());
                wifiInfo.setWifiName(wifiBean.getWifiName());
                wifiInfo.setCapabilities(wifiBean.getCapabilities());
                if (type == 1) {
                    wifiInfo.setState(Constants.WIFI_STATE_CONNECT);
                } else {
                    wifiInfo.setState(Constants.WIFI_STATE_ON_CONNECTING);
                }
            }
        }
        if (index != -1) {
            mWifiList.remove(index);
            mWifiList.add(0, wifiInfo);
            if (mAdapter != null) {
                mAdapter.notifyDataSetChanged();
            }
        }
    }

    /**
     * 之前没配置过该网络， 弹出输入密码界面
     * @param position
     */
    private void noConfigurationWifi(int position) {
        WifiLinkDialog linkDialog = new WifiLinkDialog(getActivity(), R.style.dialog_download, mWifiList.get(position).getWifiName(), mWifiList.get(position).getCapabilities());
        if (!linkDialog.isShowing()) {
            linkDialog.show();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        SPUtils.getInstance().put(Constants.SWITCH_BTN_STATE, mSwitchButton.isChecked());
    }

    @Override
    public void onDestroy() {
        getActivity().unregisterReceiver(mWifiReceiver);
        super.onDestroy();
    }

    @Override
    public void initParams(Bundle params) {
    }

    @Override
    public void doBusiness(Context mContext) {

    }

    @Override
    public void lazyInitBusiness(Context mContext) {

    }
}

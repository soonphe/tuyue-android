package com.ywb.tuyue.ui.setting.network;


import android.Manifest;
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
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.blankj.utilcode.util.SPUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.suke.widget.SwitchButton;
import com.ywb.tuyue.R;
import com.ywb.tuyue.constants.Constants;
import com.ywb.tuyue.entity.WifiBean;
import com.ywb.tuyue.ui.adapter.WifiListAdapter;
import com.ywb.tuyue.ui.mvp.BaseActivity;
import com.ywb.tuyue.ui.mvp.BaseFragmentV4;
import com.ywb.tuyue.utils.CollectionUtils;
import com.ywb.tuyue.utils.WifiSupport;
import com.ywb.tuyue.utils.WifiUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Author soonphe
 * @Date 2018-08-22 11:01
 * @Description 设置-网络设置
 */
public class NetworkFragment extends BaseFragmentV4 implements NetworkContract.View {

    private SwitchButton mSwitchButton;
    RecyclerView mRecyWifiList;


    private static final String TAG = "MainActivity";
    //权限请求码
    private static final int PERMISSION_REQUEST_CODE = 0;
    //两个危险权限需要动态申请
    private static final String[] NEEDED_PERMISSIONS = new String[]{
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION
    };

    private boolean mHasPermission;
    List<WifiBean> mWifiList = new ArrayList<>();
    private WifiListAdapter mAdapter;
    private WifiBroadcastReceiver mWifiReceiver;
    private int mConnectType = 0;//1：连接成功？ 2 正在连接（如果wifi热点列表发生变需要该字段）
    private WifiManager mWifiManager;


    @Override
    public int bindLayout() {
        return R.layout.fragment_wifisetting;
    }

    @Override
    public void initView(View view) {
        mSwitchButton = (SwitchButton) findViewById(R.id.switch_button);
        mRecyWifiList = (RecyclerView) findViewById(R.id.recy_list_wifi);
        initRecycler();
        Log.i("wifi设置：", "系统wifi：" + WifiUtils.isWifiAvailable(getActivity()));
        if (!WifiUtils.isWifiAvailable(getActivity())) {
            Log.i("wifi设置：", "系统wifi关闭");
            WifiUtils.onOpenSystemWifi(getActivity());
        }
        initWifi();
    }

    private void initWifi() {
        mHasPermission = checkPermission();
        if (!mHasPermission && WifiSupport.isOpenWifi(getActivity())) {  //未获取权限，申请权限
            requestPermission();
        } else if (mHasPermission && WifiSupport.isOpenWifi(getActivity())) {  //已经获取权限
            mSwitchButton.setChecked(true);
        } else {
            Toast.makeText(getActivity(), "WIFI处于关闭状态", Toast.LENGTH_SHORT).show();
//            mSwitchButton.setChecked(false);
        }

        mSwitchButton.setChecked(SPUtils.getInstance().getBoolean(Constants.SWITCH_BTN_STATE, true));

        mSwitchButton.setOnCheckedChangeListener((view, isChecked) -> setWifiEnable(isChecked));
        //首先，用Context通过getSystemService获取wifimanager
        mWifiManager = (WifiManager) getActivity().getApplicationContext().getSystemService(Context.WIFI_SERVICE);
    }


    @Override
    public void onResume() {
        super.onResume();

        //注册广播
        mWifiReceiver = new WifiBroadcastReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction(WifiManager.WIFI_STATE_CHANGED_ACTION);//监听wifi是开关变化的状态
        filter.addAction(WifiManager.NETWORK_STATE_CHANGED_ACTION);//监听wifi连接状态广播,是否连接了一个有效路由
        filter.addAction(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION);//监听wifi列表变化（开启一个热点或者关闭一个热点）
        getActivity().registerReceiver(mWifiReceiver, filter);
    }

    /**
     * 关闭或者打开wifi
     *
     * @param state
     */
    public void setWifiEnable(boolean state) {

        //调用WifiManager的setWifiEnabled方法设置wifi的打开或者关闭，只需把下面的state改为布尔值即可（true:打开 false:关闭）
        mWifiManager.setWifiEnabled(state);
    }

    private void initRecycler() {
        mRecyWifiList.setHasFixedSize(true);//当我们确定Item的改变不会影响RecyclerView的宽高的时候可以设置setHasFixedSize(true)，并通过Adapter的增删改插方法去刷新RecyclerView
        mRecyWifiList.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyWifiList.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
//        //设置适配器
        mAdapter = new WifiListAdapter(R.layout.item_wifi_list); //传一个item的布局
//        //设置开始加载的位置
        mAdapter.setStartUpFetchPosition(2);
//        //设置adapter的启动动画（ALPHAIN 渐显、SCALEIN 缩放、SLIDEIN_BOTTOM 从下到上，SLIDEIN_LEFT从左到右、SLIDEIN_RIGHT 从右到左）
        //  mAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
        mAdapter.setNewData(mWifiList);
        mRecyWifiList.setAdapter(mAdapter);
//        //解决NestedScrollView和RecycleView滑动冲突、
        mRecyWifiList.setNestedScrollingEnabled(false);

        if (WifiSupport.isOpenWifi(getActivity()) && mHasPermission) { //如果 wifi是 打开状态并且申请有权限
            sortScaResult(); //获取wifi展示连接列表
        } else {
//            Toast.makeText(getActivity(), "WIFI处于关闭状态或权限获取失败", Toast.LENGTH_SHORT).show();
        }

        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                WifiBean wifiBean = mWifiList.get(position);
                if (wifiBean.getState().equals(Constants.WIFI_STATE_UNCONNECT) || wifiBean.getState().equals(Constants.WIFI_STATE_CONNECT)) {
                    //如果wifi是  未连接  或者是   已连接状态
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
                        //在这里做个判断，如果列表中的某一项wifi显示已经连接上了，则不用弹出密码输入框  ，  第一次连接一个新的wifi时，需弹框，还要就是之前连接成功过的，当密码被修改时在弹出框输入密码
                        if (wifiBean.getState().equals(Constants.WIFI_STATE_CONNECT)) { //如果wifi已经处于连接状态
                            Toast.makeText(getActivity(), "当前wifi已经连接", Toast.LENGTH_SHORT).show();
                            System.out.println("当前wifi已经连接" + "///////////////////////////");
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
            }

        });
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
                        Log.d(TAG, "已经关闭");
                        Toast.makeText(getActivity(), "WIFI处于关闭状态", Toast.LENGTH_SHORT).show();
                        sortScaResult();
                        mSwitchButton.setChecked(false);
                        break;
                    }
                    case WifiManager.WIFI_STATE_DISABLING: {
                        Log.d(TAG, "正在关闭");
                        break;
                    }
                    case WifiManager.WIFI_STATE_ENABLED: {
                        Log.d(TAG, "已经打开");
                        sortScaResult();
                        mSwitchButton.setChecked(true);
                        break;
                    }
                    case WifiManager.WIFI_STATE_ENABLING: {
                        Log.d(TAG, "正在打开");
                        break;
                    }
                    case WifiManager.WIFI_STATE_UNKNOWN: {
                        Log.d(TAG, "未知状态");
                        break;
                    }
                }
            } else if (WifiManager.NETWORK_STATE_CHANGED_ACTION.equals(intent.getAction())) {
                NetworkInfo info = intent.getParcelableExtra(WifiManager.EXTRA_NETWORK_INFO);
                Log.d(TAG, "--NetworkInfo--" + info.toString());
                if (NetworkInfo.State.DISCONNECTED == info.getState()) {//wifi没连接上
                    Log.d(TAG, "wifi没连接上");
                    for (int i = 0; i < mWifiList.size(); i++) {//没连接上将 所有的连接状态都置为“未连接”
                        mWifiList.get(i).setState(Constants.WIFI_STATE_UNCONNECT);
                    }
                    if (mAdapter != null) {
                        mAdapter.notifyDataSetChanged();
                    }
                } else if (NetworkInfo.State.CONNECTED == info.getState()) {//wifi连接上了
                    Log.d(TAG, "wifi连接上了");
                    WifiInfo connectedWifiInfo = WifiSupport.getConnectedWifiInfo(getActivity());

                    //连接成功 跳转界面 传递ip地址
//                    Toast.makeText(getActivity(), "wifi连接上了", Toast.LENGTH_SHORT).show();
                    mConnectType = 1;
                    wifiListSet(connectedWifiInfo.getSSID(), mConnectType);
                } else if (NetworkInfo.State.CONNECTING == info.getState()) {//正在连接
                    Log.d(TAG, "wifi正在连接");
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
     * //网络状态发生改变 调用此方法！
     */
    public void wifiListChange() {
        sortScaResult();
        WifiInfo connectedWifiInfo = WifiSupport.getConnectedWifiInfo(getActivity());
        if (connectedWifiInfo != null) {
            wifiListSet(connectedWifiInfo.getSSID(), mConnectType);
        }
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
        if (mAdapter != null) {
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        boolean hasAllPermission = true;
        if (requestCode == PERMISSION_REQUEST_CODE) {
            for (int i : grantResults) {
                if (i != PackageManager.PERMISSION_GRANTED) {
                    hasAllPermission = false;   //判断用户是否同意获取权限
                    break;
                }
            }

            //如果同意权限
            if (hasAllPermission) {
                mHasPermission = true;
                if (WifiSupport.isOpenWifi(getActivity()) && mHasPermission) {  //如果wifi开关是开 并且 已经获取权限
//                    initRecycler();
                    mSwitchButton.setChecked(true);
                } else {
                    Toast.makeText(getActivity(), "WIFI处于关闭状态或权限获取失败", Toast.LENGTH_SHORT).show();
                }

            } else {  //用户不同意权限
                mHasPermission = false;
                Toast.makeText(getActivity(), "获取权限失败", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void noConfigurationWifi(int position) {//之前没配置过该网络， 弹出输入密码界面
        WifiLinkDialog linkDialog = new WifiLinkDialog(getActivity(), R.style.dialog_download, mWifiList.get(position).getWifiName(), mWifiList.get(position).getCapabilities());
        if (!linkDialog.isShowing()) {
            linkDialog.show();
        }
    }

    /**
     * 申请权限
     */
    private void requestPermission() {
        ActivityCompat.requestPermissions(getActivity(),
                NEEDED_PERMISSIONS, PERMISSION_REQUEST_CODE);
    }

    /**
     * 检查是否已经授予权限
     *
     * @return
     */
    private boolean checkPermission() {
        for (String permission : NEEDED_PERMISSIONS) {
            if (ActivityCompat.checkSelfPermission(getActivity(), permission)
                    != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void onPause() {
        super.onPause();
        getActivity().unregisterReceiver(mWifiReceiver);
        Log.i("wifi设置", mSwitchButton.isChecked() + "");
        SPUtils.getInstance().put( Constants.SWITCH_BTN_STATE, mSwitchButton.isChecked());
    }

    @Override
    public void onDestroy() {
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

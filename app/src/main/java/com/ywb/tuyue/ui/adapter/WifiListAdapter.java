package com.ywb.tuyue.ui.adapter;

import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.blankj.utilcode.util.NetworkUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ywb.tuyue.R;
import com.ywb.tuyue.constants.Constants;
import com.ywb.tuyue.entity.WifiBean;
import com.ywb.tuyue.utils.WifiSupport;


/**
 * Created by JuZhongJoy on 2018/7/12.
 * wifi适配器
 */

public class WifiListAdapter extends BaseQuickAdapter<WifiBean, BaseViewHolder> {

    public WifiListAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, WifiBean bean) {

        String capabilities = bean.getCapabilities();//加密方式
        helper.setText(R.id.tv_item_wifi_name, bean.getWifiName());
        helper.setText(R.id.tv_item_wifi_status, bean.getState());
        int level = Integer.parseInt(bean.getLevel());
        Log.d("onBindViewHolder", "加密方式" + capabilities);
        String capabi = "";
        if (capabilities.contains("WPA-")) {
            capabi = "WPA";
        }
        if (capabilities.contains("WPA2-")) {
            if (!TextUtils.isEmpty(capabi)) {
                capabi = capabi + "/";
            }
            capabi = capabi + "WPA2";
        }
        helper.setText(R.id.tv_item_wifi_safe, capabi);
        //无需密码
        if (WifiSupport.getWifiCipher(bean.getCapabilities()) == WifiSupport.WifiCipherType.WIFICIPHER_NOPASS) {
            helper.setImageResource(R.id.wifi_level, getWifiIcon(level, 0));
        } else {
            helper.setImageResource(R.id.wifi_level, getWifiIcon(level, 1));
        }
        //可以传递给adapter的数据都是经过处理的，已连接或者正在连接状态的wifi都是处于集合中的首位，所以可以写出如下判断
        if ((Constants.WIFI_STATE_ON_CONNECTING.equals(bean.getState()) || Constants.WIFI_STATE_CONNECT.equals(bean.getState()))) {
            helper.setVisible(R.id.ll_show_collection, true);
            if (Constants.WIFI_STATE_CONNECT.equals(bean.getState())) {
                helper.setVisible(R.id.iv_choose, true);
            } else {
                helper.setGone(R.id.iv_choose, false);
            }
        } else {
            helper.setGone(R.id.ll_show_collection, false);
            helper.setGone(R.id.iv_choose, false);
        }

    }

    private int getWifiIcon(int level, int type) {
        int resId = 0;
        switch (level) {//信号等级
            case 1://强
                resId = type == 0 ? R.drawable.icon_wifi1 : R.mipmap.icon_swifi1;
                break;
            case 2://较强
                resId = type == 0 ? R.mipmap.icon_wifi3 : R.mipmap.icon_swifi2;
                break;
            case 3://较弱
            case 4://弱
                resId = type == 0 ? R.mipmap.icon_wifi2 : R.mipmap.icon_swifi3;
                break;
        }
        return resId;
    }

}

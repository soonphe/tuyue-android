package com.ywb.tuyue.ui.city.city;


import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.blankj.utilcode.constant.TimeConstants;
import com.blankj.utilcode.util.BarUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.TimeUtils;
import com.jude.easyrecyclerview.decoration.SpaceDecoration;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.ywb.tuyue.R;
import com.ywb.tuyue.constants.Constants;
import com.ywb.tuyue.entity.TCity;
import com.ywb.tuyue.entity.TStats;
import com.ywb.tuyue.ui.adapter.CityAdapter;
import com.ywb.tuyue.ui.city.citydetail.CitydetailActivity;
import com.ywb.tuyue.ui.data.DataContract;
import com.ywb.tuyue.ui.data.DataPresenter;
import com.ywb.tuyue.ui.mvp.BaseActivity;

import org.litepal.LitePal;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;


/**
 * @Author soonphe
 * @Date 2018-08-28 15:23
 * @Description 城市activity
 */
public class CityActivity extends BaseActivity implements CityContract.View, DataContract.View {

    @Inject
    CityPresenter presenter;
    @Inject
    DataPresenter dataPresenter;

    @BindView(R.id.rv_list)
    RecyclerView rvList;
    @BindView(R.id.sm_refresh)
    SmartRefreshLayout smRefresh;

    private CityAdapter cityAdapter;
    private long stayTime;//模块停留时长

    @Override
    public void startLoading() {

    }

    @Override
    public void endLoading() {

    }

    @Override
    public void onError(String error) {

    }


    @Override
    public int bindLayout() {
        return R.layout.activity_city;
    }

    @Override
    public void initParms(Bundle parms) {

    }

    @Override
    public void initView(View view) {
        BarUtils.setStatusBarAlpha(this, 0);
        presenter.attachView(this);
        dataPresenter.attachView(this);
        stayTime = System.currentTimeMillis();

        cityAdapter = new CityAdapter(R.layout.item_city);
        rvList.setLayoutManager(new GridLayoutManager(getContext(), 3));
        rvList.addItemDecoration(new SpaceDecoration(10));
        rvList.setAdapter(cityAdapter);
        rvList.setNestedScrollingEnabled(false);

        cityAdapter.setOnItemClickListener((adapter, view1, position) -> {
            mOperation.addParameter("city", ((TCity) adapter.getItem(position)).getTid());
            mOperation.forward(CitydetailActivity.class);
        });
    }

    @Override
    public void doBusiness(Context mContext) {
        presenter.getCityList();
    }


    @Override
    public void getCityListSuccess(List<TCity> list) {
        if (list.size() > 0) {
            cityAdapter.setNewData(list);
        }
    }

    @Override
    public void initInjector() {
        getComponent().inject(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        //停留时长统计:单位S
        long stayDataTime = TimeUtils.getTimeSpan(stayTime, System.currentTimeMillis(), TimeConstants.SEC);
        String phone = SPUtils.getInstance().getString(Constants.REGIST_PHONE, "");
        //判断这里是否存在用户，如果存在则要记录数据
        if (!StringUtils.isEmpty(phone)) {
            //判断今天是否创建过统计数据——有数据则更新数据+1
            TStats tOpen = LitePal.where("phone = ?", phone + "").order("id desc").findFirst(TStats.class);
            if (tOpen != null) {
                tOpen.setFoodtime((int) (tOpen.getFoodtime() + stayDataTime));
                boolean result = tOpen.save();
                //判断当前网络可用且用户数据保存成功
                if (result) {
                    //上传所有数据
                    dataPresenter.uploadData(tOpen);
                }
            }
        }
    }

    @Override
    public void uploadDataSuccess() {
        LogUtils.e("城市停留上传成功");
    }
}

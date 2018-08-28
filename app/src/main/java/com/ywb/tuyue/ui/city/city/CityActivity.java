package com.ywb.tuyue.ui.city.city;


import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.blankj.utilcode.util.BarUtils;
import com.jude.easyrecyclerview.decoration.SpaceDecoration;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.ywb.tuyue.R;
import com.ywb.tuyue.entity.TCity;
import com.ywb.tuyue.ui.adapter.CityAdapter;
import com.ywb.tuyue.ui.city.citydetail.CitydetailActivity;
import com.ywb.tuyue.ui.mvp.BaseActivity;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;


/**
 * @Author soonphe
 * @Date 2018-08-28 15:23
 * @Description 城市activity
 */
public class CityActivity extends BaseActivity implements CityContract.View {

    @Inject
    CityPresenter presenter;

    @BindView(R.id.rv_list)
    RecyclerView rvList;
    @BindView(R.id.sm_refresh)
    SmartRefreshLayout smRefresh;

    private CityAdapter cityAdapter;

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

}

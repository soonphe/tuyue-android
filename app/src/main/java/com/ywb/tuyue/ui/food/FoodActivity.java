package com.ywb.tuyue.ui.food;


import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.blankj.utilcode.util.BarUtils;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.jude.easyrecyclerview.decoration.SpaceDecoration;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.ywb.tuyue.R;
import com.ywb.tuyue.entity.TFood;
import com.ywb.tuyue.entity.TFoodType;
import com.ywb.tuyue.entity.TabEntity;
import com.ywb.tuyue.ui.adapter.FoodAdapter;
import com.ywb.tuyue.ui.mvp.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;


/**
 * @Author soonphe
 * @Date 2018-08-28 13:54
 * @Description 餐饮
 */
public class FoodActivity extends BaseActivity implements FoodContract.View {

    @Inject
    FoodPresenter presenter;

    @BindView(R.id.tl_2)
    CommonTabLayout tl2;
    @BindView(R.id.rv_list)
    RecyclerView rvList;
    @BindView(R.id.sm_refresh)
    SmartRefreshLayout smRefresh;

    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    private FoodAdapter foodAdapter;

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
        return R.layout.activity_food;
    }

    @Override
    public void initParms(Bundle parms) {

    }

    @Override
    public void initView(View view) {
        BarUtils.setStatusBarAlpha(this, 0);
        presenter.attachView(this);

        foodAdapter = new FoodAdapter(R.layout.item_food);
        rvList.setLayoutManager(new GridLayoutManager(getContext(), 4));
        rvList.addItemDecoration(new SpaceDecoration(10));
        rvList.setAdapter(foodAdapter);
        rvList.setNestedScrollingEnabled(false);
        foodAdapter.setOnItemClickListener((adapter, view1, position) -> {
//            mOperation.addParameter("book", ((TBook) adapter.getItem(position)).getId());
//            mOperation.forward(BookreadActivity.class);
        });
    }

    @Override
    public void doBusiness(Context mContext) {
        presenter.getTypeList();
    }

    @Override
    public void getTypeListSuccess(List<TFoodType> list) {

        for (int i = 0; i < list.size(); i++) {
            mTabEntities.add(new TabEntity(list.get(i).getName()));
//            mTabEntities.add(new TabEntity(mTitles[i]));
        }
        tl2.setTabData(mTabEntities);

        tl2.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                presenter.getFoodList(list.get(position).getTid());
            }

            @Override
            public void onTabReselect(int position) {

            }
        });
        presenter.getFoodList(list.get(0).getTid());
    }

    @Override
    public void getFoodListSuccess(List<TFood> list) {
        if (list.size() > 0) {
            foodAdapter.setNewData(list);
        }
    }

    @Override
    public void initInjector() {
        getComponent().inject(this);
    }


}

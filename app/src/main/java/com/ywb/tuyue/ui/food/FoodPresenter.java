package com.ywb.tuyue.ui.food;

import com.ywb.tuyue.api.AppApi;
import com.ywb.tuyue.di.PerActivity;
import com.ywb.tuyue.entity.TFood;
import com.ywb.tuyue.entity.TFoodType;
import com.ywb.tuyue.base.mvp.BasePresenter;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

@PerActivity
public class FoodPresenter extends BasePresenter<FoodContract.View> implements FoodContract.Presenter {


    private AppApi api;

    @Inject
    public FoodPresenter(AppApi accountApi) {
        this.api = accountApi;
    }

    @Override
    public void getTypeList() {
        //获取类型列表
        List<TFoodType> list = LitePal.findAll(TFoodType.class);
        if (list.size() > 0) {
            mView.getTypeListSuccess(list);
        } else {
            mView.getTypeListSuccess(new ArrayList<>());
        }
    }

    @Override
    public void getFoodList(int typeid) {
        //获取列表
        List<TFood> list = LitePal.where("typeid = ?", typeid + "").find(TFood.class);
        if (list.size() > 0) {
            mView.getFoodListSuccess(list);
        } else {
            mView.getFoodListSuccess(new ArrayList<>());
        }
    }
}

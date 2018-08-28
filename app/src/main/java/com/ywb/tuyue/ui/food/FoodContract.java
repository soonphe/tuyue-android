package com.ywb.tuyue.ui.food;

import com.ywb.tuyue.entity.TFood;
import com.ywb.tuyue.entity.TFoodType;
import com.ywb.tuyue.ui.mvp.BasePView;

import java.util.List;

public class FoodContract {
    interface View extends BasePView {
        void getTypeListSuccess(List<TFoodType> list );
        void getFoodListSuccess( List<TFood> list);
    }

    interface  Presenter  {
        void getTypeList( );
        void getFoodList(int typeid );
    }
}

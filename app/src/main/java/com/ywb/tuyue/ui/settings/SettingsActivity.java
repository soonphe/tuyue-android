package com.ywb.tuyue.ui.settings;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.ywb.tuyue.R;
import com.ywb.tuyue.ui.main.MainActivity;
import com.ywb.tuyue.ui.mvp.BaseActivity;
import com.ywb.tuyue.widget.head.HeaderView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SettingsActivity extends BaseActivity {


    @BindView(R.id.header)
    HeaderView mHeader;

    @Override
    public int bindLayout() {
        return R.layout.activity_settings;
    }

    @Override
    public void initParms(Bundle parms) {

    }

    @Override
    public void initView(View view) {

      mHeader.setTitle(R.string.settings);
      mHeader.setRightBtnVisiable(View.GONE);
      mHeader.setLeftBtnClickListsner(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              mOperation.forwardAndFinish(MainActivity.class,TOP_BOTTOM);
          }
      });
    }

    @Override
    public void doBusiness(Context mContext) {

    }

    @Override
    public void initInjector() {

    }


}

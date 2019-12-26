package com.ywb.tuyue.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.ywb.tuyue.MyApplication;
import com.ywb.tuyue.di.component.ApplicationComponent;
import com.ywb.tuyue.di.component.DaggerFragmentComponent;
import com.ywb.tuyue.di.component.FragmentComponent;
import com.ywb.tuyue.di.module.ActivityModule;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.disposables.CompositeDisposable;


/**
 * @Author soonphe
 * @Date 2017-12-01 15:13
 * @Description BaseFragment(V4)
 */
public abstract class BaseFragmentV4 extends Fragment implements IBaseFragment, IBaseConstant {
    protected CompositeDisposable compositeDisposable = new CompositeDisposable();
    /**
     * 当前Fragment渲染的视图View
     **/
    private View mContextView = null;
    /**
     * 共通操作
     **/
    protected Operation mOperation = null;
    /**
     * 依附的Activity
     **/
    protected Activity mContext = null;
    /**
     * 日志输出标志
     **/
    protected final String TAG = this.getClass().getSimpleName();
    // ButterKnife绑定
    Unbinder unbinder;
    protected boolean isVisible;
    protected boolean isPrepared;
    protected boolean isFirstLoad = true;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        // 缓存当前依附的activity
        mContext = activity;
        Log.d(TAG, "BaseFragmentV4-->onAttach()");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "BaseFragmentV4-->onCreate()");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG, "BaseFragmentV4-->onCreateView()");
        //运行依赖注入
        initInjector();
        // 渲染视图View
        if (null == mContextView) {
            // 初始化参数
            Bundle parms = getArguments();
            if (null == parms) {
                parms = new Bundle();
            }
            // 初始化参数
            initParams(parms);
            //绑定视图View
            View mView = bindView();
            if (null == mView) {
                isFirstLoad = true;
                mContextView = inflater.inflate(bindLayout(), container, false);
            } else {
                mContextView = mView;
            }
            //ButterKnife注解绑定
            unbinder = ButterKnife.bind(this, mContextView);//初始化黄油刀
            // 控件初始化
            initView(mContextView);
            isPrepared = true;
            // 实例化共通操作
            mOperation = new Operation(getActivity());
            // 业务处理
            doBusiness(getActivity());
        }
        return mContextView;
    }

    /**
     * dagger依赖注入（onCreate方法中调用）
     */
    public abstract void initInjector();

    /**
     * 懒加载，判断页面是否准备完毕，是否可见，bindView为空
     */
    protected void lazyLoad() {
        if (!isPrepared || !isVisible || !isFirstLoad) {
            return;
        }
        isFirstLoad = false;

        lazyInitBusiness(getActivity());
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {
            //界面可见
            isVisible = true;
            onVisible();
        } else {
            //界面不可见
            isVisible = false;
            onInvisible();
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            isVisible = true;
            onVisible();
        } else {
            isVisible = false;
            onInvisible();
        }
    }

    protected void onVisible() {
        lazyLoad();
    }

    protected void onInvisible() {
    }

//    protected <C> C getComponent(Class<C> componentType) {
//        return componentType.cast(((HasComponent<C>) getActivity()).getFragmentComponent());
        //获取FragmentComponent 原理同上
//        FragmentComponent.class.cast(((HasComponent<FragmentComponent>) getActivity()).getFragmentComponent()).inject(this);
        //或者DaggerFragmentComponent 作用同上
//        DaggerFragmentComponent.builder().applicationComponent((ApplicationComponent)getActivity()).
//                build().inject(this);
//    }

    /**
     * 初始化FragmentComponent
     */
    public FragmentComponent getComponent() {
        return DaggerFragmentComponent.builder()
                .activityModule(getActivityModule())
                .applicationComponent(getApplicationComponent())
                .build();
    }

    /*** 获取ApplicationComponent **/
    protected ApplicationComponent getApplicationComponent() {
        return ((MyApplication) getActivity().getApplication()).getApplicationComponent();
    }

    /*** 构造ActivityModule **/
    protected ActivityModule getActivityModule() {
        return new ActivityModule(getActivity());
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        Log.d(TAG, "BaseFragmentV4-->onActivityCreated()");
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Log.d(TAG, "BaseFragmentV4-->onViewCreated()");
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        Log.d(TAG, "BaseFragmentV4-->onSaveInstanceState()");
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onStart() {
        Log.d(TAG, "BaseFragmentV4-->onStart()");
        super.onStart();
    }

    @Override
    public void onResume() {
        Log.d(TAG, "BaseFragmentV4-->onResume()");
        super.onResume();
    }

    @Override
    public void onPause() {
        Log.d(TAG, "BaseFragmentV4-->onPause()");
        super.onPause();
    }

    @Override
    public void onStop() {
        Log.d(TAG, "BaseFragmentV4-->onStop()");
        super.onStop();
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "BaseFragmentV4-->onDestroy()");
        super.onDestroy();
        if (compositeDisposable != null) {
            compositeDisposable.clear();
        }
    }

    @Override
    public void onDetach() {
        Log.d(TAG, "BaseFragmentV4-->onDetach()");
        super.onDetach();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mContextView != null && mContextView.getParent() != null) {
            ((ViewGroup) mContextView.getParent()).removeView(mContextView);    //复用Fragment的RootView，（通用）
        }
        unbinder.unbind();
    }

    @Override
    public View bindView() {
        return null;
    }

    /**
     * 查找view
     *
     * @param id
     * @return
     */
    protected View findViewById(int id) {
        if (null == mContextView) return null;

        return mContextView.findViewById(id);
    }

    /**
     * 获取当前Fragment依附在的Activity
     *
     * @return
     */
    public Activity getContext() {
        return getActivity();
    }

    /**
     * 获取共通操作机能
     */
    public Operation getOperation() {
        return this.mOperation;
    }

    public void reload() {
        Intent intent = getContext().getIntent();
        getContext().overridePendingTransition(0, 0);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        getContext().finish();
        getContext().overridePendingTransition(0, 0);
        startActivity(intent);
    }

}

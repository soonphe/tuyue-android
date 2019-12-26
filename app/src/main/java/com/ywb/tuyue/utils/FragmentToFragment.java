package com.ywb.tuyue.utils;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

/**
 * @Author soonphe
 * @Date 2018-01-25 13:51
 * @Descprition fragment跳转通用类
 */

public class FragmentToFragment {

    /**
     * 添加fragment
     *
     * @param containerViewId 指定viewId
     * @param oldFragment     初始fragment
     * @param newfragment     要添加的fragment
     */
    public static void addFragment(int containerViewId, Fragment oldFragment, Fragment newfragment) {
        //getFragmentManager
        //getChildFragmentManager
        FragmentTransaction transaction = oldFragment.getFragmentManager().beginTransaction();
        // 然后将该事务添加到返回堆栈，以便用户可以向后导航
        //replace：FragmentOne实例不会被销毁，但是视图层次依然会被销毁
        // transaction.hide(this); 隐藏当前fragment——使用结果全白×
        transaction.add(containerViewId, newfragment);
        //设置简单的过度动画
        //transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    /**
     * fragment直接回退
     *
     * @param fragment
     */
    public static void goBack(Fragment fragment) {
        FragmentTransaction transaction = fragment.getFragmentManager().beginTransaction();
        // 然后将该事务添加到返回堆栈，以便用户可以向后导航
        //replace：FragmentOne实例不会被销毁，但是视图层次依然会被销毁
        transaction.remove(fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    /**
     * fragment回退parent（适用viewPager多页面fragment的返回）
     *
     * @param fragment viewpager等子类fragment
     */
    public static void goParentBack(Fragment fragment) {
        FragmentTransaction transaction = fragment.getFragmentManager().beginTransaction();
        // 然后将该事务添加到返回堆栈，以便用户可以向后导航
        //replace：FragmentOne实例不会被销毁，但是视图层次依然会被销毁
        transaction.remove(fragment.getParentFragment());
        transaction.addToBackStack(null);
        transaction.commit();
    }
}

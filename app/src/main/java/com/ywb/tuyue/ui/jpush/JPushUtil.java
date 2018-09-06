package com.ywb.tuyue.ui.jpush;

import android.app.Notification;

import com.ywb.tuyue.MyApplication;
import com.ywb.tuyue.R;

import cn.jpush.android.api.BasicPushNotificationBuilder;
import cn.jpush.android.api.CustomPushNotificationBuilder;
import cn.jpush.android.api.JPushInterface;


/**
 * @Author soonphe
 * @Date 2018-01-23 17:01
 * @Descprition
 */

public class JPushUtil {

//    /**
//     * 设置通知提示方式 - 基础属性
//     */
//    public static void setStyleBasic() {
//        BasicPushNotificationBuilder builder = new BasicPushNotificationBuilder(MyApplication.gainContext());
//        builder.statusBarDrawable = R.mipmap.index_logo;
//        builder.notificationFlags = Notification.FLAG_AUTO_CANCEL;  //设置为点击后自动消失
//        builder.notificationDefaults = Notification.DEFAULT_SOUND;  //设置为铃声（ Notification.DEFAULT_SOUND）或者震动（ Notification.DEFAULT_VIBRATE）
//        JPushInterface.setPushNotificationBuilder(1, builder);
//
//    }
//
//    /**
//     * 设置通知栏样式 - 定义通知栏Layout
//     */
//    public static void setStyleCustom() {
//        CustomPushNotificationBuilder builder = new CustomPushNotificationBuilder(MyApplication.gainContext(), R.layout.customer_notitfication_layout, R.id.icon, R.id.title, R.id.text);
//        builder.layoutIconDrawable = R.mipmap.index_logo;
//        builder.developerArg0 = "developerArg2";
//        JPushInterface.setPushNotificationBuilder(2, builder);
//    }

    /**
     * 处理tag
     */
    public static void setJpushAlias(String alias) {
        //设置手机好别名
//        TagAliasOperatorHelper.getInstance().handleAction(MyApplication.getContext(), sequence++, "");

        //通过TagAliasBean设置alias或tag
        TagAliasOperatorHelper.TagAliasBean tagAliasBean = new TagAliasOperatorHelper.TagAliasBean();
        tagAliasBean.action = TagAliasOperatorHelper.ACTION_SET;
        tagAliasBean.alias = alias;
        tagAliasBean.isAliasAction = true;
        TagAliasOperatorHelper.getInstance().handleAction(MyApplication.gainContext(), TagAliasOperatorHelper.sequence++, tagAliasBean);
    }
}

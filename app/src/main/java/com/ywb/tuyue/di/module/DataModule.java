package com.ywb.tuyue.di.module;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.blankj.utilcode.util.SPUtils;

import org.litepal.tablemanager.Connector;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * @Author soonphe
 * @Date 2017-11-22 10:18
 * @Descprition 提供SQLiteDatabase ，SharedPreferences
 */
@Module
public class DataModule {


    @Provides
    @Singleton
    SQLiteDatabase provideSQLiteDatabase(Context context) {
        SQLiteDatabase db = Connector.getDatabase();
        return db;
    }

    @Provides
    @Singleton
    SPUtils provideSharedPreferences() {
        SPUtils spUtils = SPUtils.getInstance();
        return spUtils;
    }
}

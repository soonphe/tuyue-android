package com.ywb.tuyue.utils;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Build;

/**
 * @Author soonphe
 * @Date 2018-08-21 13:16
 * @Description 资源文件操作工具类
 */
public class ResourceUtils {
    public static int getResourceId(
            TypedArray typedArray,
            int id,
            int defaultId) {
        return typedArray.getResourceId(id, defaultId);
    }

    public static String getString(
            TypedArray typedArray,
            int id) {
        return typedArray.getString(id);
    }

    public static int getInt(
            TypedArray typedArray,
            int id,
            int defaultId) {
        return typedArray.getInt(id, typedArray.getResources().getInteger(defaultId));
    }

    public static boolean getBoolean(
            TypedArray typedArray,
            int id,
            int defaultId) {
        return typedArray.getBoolean(id, typedArray.getResources().getBoolean(defaultId));
    }

    public static int getDimenSize(
            TypedArray typedArray,
            int id,
            int defaultId) {
        return typedArray.getDimensionPixelSize(id, typedArray.getResources().getDimensionPixelSize(defaultId));
    }

    public static int getDimenOffset(
            TypedArray typedArray,
            int id,
            int defaultId) {
        return typedArray.getDimensionPixelOffset(id, typedArray.getResources().getDimensionPixelOffset(defaultId));
    }

    public static int getColor(
            TypedArray typedArray,
            int id,
            int defaultId) {
        return typedArray.getColor(id, getColor(typedArray, defaultId));
    }

    public static int getColor(TypedArray typedArray, int id) {
        return getColor(typedArray, id, null);
    }

    public static int getColor(TypedArray typedArray, int id, Resources.Theme theme) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return typedArray.getResources().getColor(id, theme);
        } else {
            return typedArray.getResources().getColor(id);
        }
    }
}

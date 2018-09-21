package com.ywb.tuyue.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;

import java.io.IOException;
import java.io.InputStream;

/**
 * Description: 获取图片并对图片进行压缩处理
 * Created by wcystart on 2018/6/20.
 */

public class BitmapUtls {
    /**
     * 获取asset目录的图片
     *
     * @param c
     * @param imagePath
     * @return
     */
    public static Bitmap getBitmapFromAsset(Context c, String imagePath) {
        Bitmap bitmap = null;
        try {
            InputStream is = c.getAssets().open(imagePath);
            bitmap = BitmapFactory.decodeStream(is);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return bitmap;
    }

    /**
     * 等比缩放图片
     *
     * @param bm 图片
     * @param scale 缩放比例
     * @return
     */
    public static Bitmap zoomImg(Bitmap bm, float scale) {
        // 获得图片的宽高
        int width = bm.getWidth();
        int height = bm.getHeight();
//        // 计算缩放比例
//        float scaleWidth = ((float) newWidth) / width;
//        float scaleHeight = ((float) newHeight) / height;
        // 取得想要缩放的matrix参数
        Matrix matrix = new Matrix();
        matrix.postScale(scale, scale);
        // 得到新的图片
        Bitmap newbm = Bitmap.createBitmap(bm, 0, 0, width, height, matrix, true);
        //  if (null != bm && !bm.isRecycled()) {
        //     bm.recycle();
        // }
        return newbm;
    }
}

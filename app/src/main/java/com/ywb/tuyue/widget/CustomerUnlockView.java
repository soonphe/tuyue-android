package com.ywb.tuyue.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

import com.ywb.tuyue.R;
import com.ywb.tuyue.ui.unlock.OnLockListener;
import com.ywb.tuyue.utils.BitmapUtls;

/**
 * Description:
 * Created by wcystart on 2018/6/20.
 */

public class CustomerUnlockView extends View {
    private final Context context;
    /**
     * 画笔
     */
    private Paint paint;
    /**
     * 屏幕宽度
     */
    private int screenWidth;
    /**
     * 屏幕高度
     */
    private int screenHeight;

    /**
     * 左边的图片
     */
    private Bitmap leftBitmap;
    /**
     * 右边的图片
     */
    private Bitmap rightBitmap;
    /**
     * 滑道
     */
    private Bitmap roadBitmap;
    /**
     * 滑道的x,y坐标
     */
    private float roadX, roadY;
    /**
     * 左边图片的x,y坐标
     */
    private float leftX, leftY;
    /**
     * 初始图片的x,y坐标
     */
    private float startX, startY;
    /**
     * 右边图片的x,y坐标
     */
    private float rightX, rightY;
    /**
     * 中间提示字体的坐标
     */
    private float textX, textY;

    private String hintText = "滑动解锁 unlock it";

    private boolean isOk = false;

    private boolean isInside = false;

    private OnLockListener onLockListener;
    private float textSize = 24;


    public void setOnLockListener(OnLockListener onLockListener) {
        this.onLockListener = onLockListener;
    }

    public CustomerUnlockView(Context context) {
        this(context, null);
    }

    public CustomerUnlockView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomerUnlockView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        initScreen();
        initView();
    }



    /**
     * 初始化屏幕的宽高
     */
    private void initScreen() {
        //获取屏幕的宽高
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        screenWidth = dm.widthPixels;         // 屏幕宽度（像素）
        screenHeight = dm.heightPixels;       // 屏幕高度（像素）
    }

    /**
     * 初始化图片和画笔的参数
     */
    private void initView() {
        paint = new Paint();
        paint.setAntiAlias(true);//抗锯齿

        leftBitmap = BitmapUtls.getBitmapFromAsset(context, "mipmap-xhdpi/icon_key.png");
        rightBitmap = BitmapUtls.getBitmapFromAsset(context, "mipmap-xhdpi/icon_unlock.png");
        roadBitmap = BitmapUtls.getBitmapFromAsset(context, "mipmap-xhdpi/bg_lock_road.png");

        //设置缩放比例 屏幕的一半
        float scale = (screenWidth >> 1) * 1.0f / roadBitmap.getWidth();
        roadBitmap = BitmapUtls.zoomImg(roadBitmap, scale);
        leftBitmap = BitmapUtls.zoomImg(leftBitmap, scale);
        rightBitmap = BitmapUtls.zoomImg(rightBitmap, scale);


        //滑道的x,y坐标
        roadX = (screenWidth - roadBitmap.getWidth()) >> 1;
        roadY = screenHeight * 0.6f;
        //初始图片的x,y坐标
        startX = ((screenWidth - roadBitmap.getWidth()) >> 1) + 13;
        startY = (roadBitmap.getHeight() - leftBitmap.getHeight()) / 2 + roadY - 2;
        //左边图片的x,y坐标
        leftX = ((screenWidth - roadBitmap.getWidth()) >> 1) + 13;
        leftY = (roadBitmap.getHeight() - leftBitmap.getHeight()) / 2 + roadY - 2;
        //右边图片的x,y坐标
        rightX = roadX + roadBitmap.getWidth() - rightBitmap.getWidth() - 13;
        rightY = (roadBitmap.getHeight() - rightBitmap.getHeight()) / 2 + roadY - 2;
        //提示文字字体的x,y坐标
        textX = roadX + roadBitmap.getWidth() / 2;
        textY = roadY + roadBitmap.getHeight() / 2 + textSize / 2 - 2;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //如果解锁了，不让其滑动
        if (isOk) {
            return true;
        }
        float y = event.getY();
        float x = event.getX();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                //第一次点击区域限制
                float distance = (float) Math.sqrt(Math.pow(x - leftX, 2) + Math.pow(Math.abs(y - leftY), 2));
                if (distance < leftBitmap.getWidth()) {
                    isInside = true;
                }
                break;
            case MotionEvent.ACTION_MOVE:
                if (isInside)
                    leftX = event.getX();
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                //如果滑道了右边图片宽度的一半，代表解锁
                if (rightX - leftX < rightBitmap.getWidth() / 2) {
                    isOk = true;
                    leftX = rightX;
                } else {
                    isOk = false;
                    leftX = startX;
                }
                //监听是否解锁 true代表解锁
                if (onLockListener != null) {
                    onLockListener.onLockListener(isOk);
                }
                isInside = false;
                break;
        }
        //不能让其左滑超出屏幕
        if (leftX < startX) {
            leftX = startX;
        }
        //不能让其右滑超出屏幕
        if (leftX > rightX) {
            leftX = rightX;
        }
        postInvalidate();
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(roadBitmap, roadX, roadY, paint);
        paint.setColor(getResources().getColor(R.color.unlock_text_color));
        paint.setTextSize(textSize);
        paint.setTextAlign(Paint.Align.CENTER);//设置锚点
        canvas.drawText(hintText, textX, textY, paint);
        paint.reset();
        paint.setAntiAlias(true);
        canvas.drawBitmap(rightBitmap, rightX, rightY, paint);
        canvas.drawBitmap(leftBitmap, leftX, leftY, paint);

    }
}

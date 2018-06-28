package com.ywb.tuyue.services.hotspot;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.ywb.tuyue.R;


/**
 * Created by ZY on 2016/8/10.
 * 悬浮球
 */
public class FloatBall extends View {

    public int width = 100;

    public int height = 60;
    //默认显示的文本
    private String text = "返回";
    //是否在拖动
    private boolean isDrag;

    private Paint ballPaint;

    private Paint textPaint;

    private Bitmap bitmap;

    public FloatBall(Context context) {
        super(context);
        init();
    }

    public FloatBall(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public FloatBall(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void init() {
        ballPaint = new Paint();
        ballPaint.setColor(Color.GRAY);
        ballPaint.setAntiAlias(true);

        textPaint = new Paint();
        textPaint.setTextSize(20);
        textPaint.setColor(Color.WHITE);
        textPaint.setAntiAlias(true);
        textPaint.setFakeBoldText(false);


        Bitmap src = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        //将图片裁剪到指定大小
        bitmap = Bitmap.createScaledBitmap(src, width, height, true);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (!isDrag) {
            canvas.drawRect(height/2,width / 2, height / 2, width / 2, ballPaint);
            float textWidth = textPaint.measureText(text);
            Paint.FontMetrics fontMetrics = textPaint.getFontMetrics();
            float dy = -(fontMetrics.descent + fontMetrics.ascent) / 2;
            canvas.drawText(text, width / 2 - textWidth / 2, height / 2 + dy, textPaint);
        } else {
            //正在被拖动时则显示指定图片
            canvas.drawBitmap(bitmap, 0, 0, null);
        }
    }

    //设置当前移动状态
    public void setDragState(boolean isDrag) {
        this.isDrag = isDrag;
        invalidate();
    }
}
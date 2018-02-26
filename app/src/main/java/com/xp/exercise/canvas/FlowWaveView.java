package com.xp.exercise.canvas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.xp.exercise.util.LogUtils;

/**
 * @类描述：流量波动球
 * @创建人：Wangxiaopan
 * @创建时间：2018/2/23 0023 17:58
 * @修改人：
 * @修改时间：2018/2/23 0023 17:58
 * @修改备注：
 */

public class FlowWaveView extends View {
    public FlowWaveView(Context context) {
        super(context);
    }

    public FlowWaveView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public FlowWaveView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public FlowWaveView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mWidth = getWidth();
        mHeight = getHeight();
        mStartPoint = new Point(-cycle * 4, mHeight / 2);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    private int mWidth, mHeight;
    private int cycle = 120; //波纹的1/4宽度
    private int waveHeight = 40;
    private int offset = 40;
    private int duration = 150;
    private Point mStartPoint;
    private int progress = 0;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        drawWave(canvas);
        drawFlowWave(canvas);
    }

    //画波纹
    private void drawWave(Canvas canvas) {
        Path path = new Path();
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setColor(Color.GREEN);
        path.moveTo(mStartPoint.x, mStartPoint.y);
        int j = 1;
        for (int i = 1; i <= 8; i++) {
            if (i % 2 == 0) {
                //波谷
                path.quadTo(mStartPoint.x + (cycle * j), mStartPoint.y + waveHeight,
                        mStartPoint.x + (cycle * 2) * i, mStartPoint.y);
            } else {
                //波峰
                path.quadTo(mStartPoint.x + (cycle * j), mStartPoint.y - waveHeight,
                        mStartPoint.x + (cycle * 2) * i, mStartPoint.y);
            }
            j += 2;
        }
        //绘制封闭的曲线
        path.lineTo(mWidth, mHeight);//右下角
        path.lineTo(mStartPoint.x, mHeight);//左下角
        path.lineTo(mStartPoint.x, mStartPoint.y);//起点
        path.close();
        canvas.drawPath(path, paint);

        //判断是不是平移完了
        if (mStartPoint.x + offset >= 0) {
            //满了一个周期恢复默认起点继续平移
            mStartPoint.x = -cycle * 4;
        }
        mStartPoint.x += offset;//每次便宜40
        postInvalidateDelayed(duration);
        path.reset();
    }

    //画流量水波纹
    private void drawFlowWave(Canvas canvas) {
        canvas.save();
        Paint circlePaint = new Paint();
        circlePaint.setColor(Color.RED);
        circlePaint.setStyle(Paint.Style.STROKE);
        circlePaint.setStrokeWidth(10f);
        canvas.drawCircle(mWidth / 2, mHeight / 2, mWidth / 4 + 5, circlePaint);

        Paint circlePaint1 = new Paint();
        circlePaint1.setColor(Color.RED);
        circlePaint1.setStyle(Paint.Style.STROKE);
        circlePaint1.setStrokeWidth(100f);
        circlePaint1.setAlpha(125);
        canvas.drawCircle(mWidth / 2, mHeight / 2, mWidth / 4 + 50, circlePaint1);
        canvas.restore();

        Path circlePath = new Path(); //剪裁的话 就只能在剪裁区显示
        circlePath.addCircle(mWidth / 2, mHeight / 2, mWidth / 4, Path.Direction.CW);
        canvas.clipPath(circlePath);

        Paint wavePaint = new Paint();
        wavePaint.setColor(Color.RED);
        wavePaint.setAlpha(20);
        wavePaint.setStyle(Paint.Style.FILL_AND_STROKE);

        //以下操作都是在圆形画布中操作
        //根据进度改变七点坐标的Y值
        mStartPoint.y = (int) ((mHeight / 2 + mWidth / 4) - (progress / 100.0 * mWidth / 2));
        LogUtils.d("Test", "mStartPoint.y = " + mStartPoint.y + " progress = " + progress);
        //起点
        Path path = new Path();
        path.moveTo(mStartPoint.x, mStartPoint.y);

        Path path1 = new Path();
        path1.moveTo(mStartPoint.x - 100, mStartPoint.y);

        int j = 1;
        for (int i = 1; i <= 8; i++) {
            if (i % 2 == 0) {
                //波谷
                path.quadTo(mStartPoint.x + (cycle * j), mStartPoint.y + waveHeight,
                        mStartPoint.x + (cycle * 2) * i, mStartPoint.y);
                path1.quadTo(mStartPoint.x - 100 + (cycle * j), mStartPoint.y + waveHeight,
                        mStartPoint.x - 100 + (cycle * 2) * i, mStartPoint.y);
            } else {
                //波峰
                path.quadTo(mStartPoint.x + (cycle * j), mStartPoint.y - waveHeight,
                        mStartPoint.x + (cycle * 2) * i, mStartPoint.y);
                path1.quadTo(mStartPoint.x - 100 + (cycle * j), mStartPoint.y - waveHeight,
                        mStartPoint.x - 100 + (cycle * 2) * i, mStartPoint.y);
            }
            j += 2;
        }
        //绘制封闭的曲线
        path.lineTo(mWidth, mHeight);//右下角
        path.lineTo(mStartPoint.x, mHeight);//左下角
        path.lineTo(mStartPoint.x, mStartPoint.y);//起点
        path.close();
        path1.lineTo(mWidth, mHeight);//右下角
        path1.lineTo(mStartPoint.x, mHeight);//左下角
        path1.lineTo(mStartPoint.x, mStartPoint.y);//起点
        path1.close();
        canvas.drawPath(path, wavePaint);
        canvas.drawPath(path1, wavePaint);
        canvas.restore();
        Paint textPaint = new Paint();
        textPaint.setTextSize(40f);
        textPaint.setTextAlign(Paint.Align.CENTER);
        canvas.drawText(progress + "%", mWidth / 2, mHeight / 2, textPaint);
        //画一条线
        Paint linePaint = new Paint();
        linePaint.setColor(Color.RED);
        linePaint.setAlpha(60);
        linePaint.setStrokeWidth(2f);
        canvas.drawLine(mWidth / 2 - mWidth / 4 * 0.5f, mHeight / 2 + mWidth / 4 * 0.45f, mWidth / 2 + mWidth / 4 * 0.5f, mHeight / 2 + mWidth / 4 * 0.45f, linePaint);

        //判断是不是平移完了
        if (mStartPoint.x + offset >= 0) {
            //满了一个周期恢复默认起点继续平移
            mStartPoint.x = -cycle * 4;
        }
        mStartPoint.x += offset;//每次偏移40
        if (progress >= 100) {
            progress = 0;
        } else {
            progress++;
        }
        postInvalidateDelayed(duration);
        path.reset();
    }



}

package com.xp.exercise.canvas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.Point;
import android.graphics.RectF;
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
        drawScaleTest(canvas);
        drawScale(canvas);
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

    private int totalScale = 100; //100个刻度
    private float[] pos11 = new float[2];
    private float[] pos22 = new float[2];
    private int currentScale = 1;
    //画流量水波纹
    private void drawFlowWave(Canvas canvas) {
        canvas.save();
        //在画一个 不是闭合圆的刻度吧
        Point point = new Point(mWidth / 2, mHeight / 2);
        Paint circleTransparentPaint = new Paint();
        circleTransparentPaint.setColor(Color.TRANSPARENT);
        circleTransparentPaint.setStyle(Paint.Style.STROKE);
        circleTransparentPaint.setStrokeWidth(2f);
        //画第一个圆
        Path circleScalepath = new Path();
        circleScalepath.addCircle(point.x, point.y, mWidth / 4 + 55, Path.Direction.CW);
        canvas.drawPath(circleScalepath, circleTransparentPaint);

        //画第二个圆
        Path circleScalepath1 = new Path();
        circleScalepath1.addCircle(point.x, point.y, mWidth / 4 + 75, Path.Direction.CW);
        canvas.drawPath(circleScalepath1, circleTransparentPaint);

        //获取两个path的相关属性
        PathMeasure pathMeasure = new PathMeasure(circleScalepath, false);
        PathMeasure pathMeasure1 = new PathMeasure(circleScalepath1, false);

        Paint lineScalePaint = new Paint();
        lineScalePaint.setColor(Color.RED);
        lineScalePaint.setStyle(Paint.Style.STROKE);
        lineScalePaint.setStrokeWidth(2f);
        for (int i = 0; i < 101; i++) { //画刻度
            float distance = 0.01f * i;
            pathMeasure.getPosTan(pathMeasure.getLength() * distance, pos11, null);
            pathMeasure1.getPosTan(pathMeasure1.getLength() * distance, pos22, null);
            canvas.drawLine(pos11[0], pos11[1], pos22[0], pos22[1], lineScalePaint);
        }
        currentScale++;
        for (int i = 0; i < currentScale; i++) { //画递变刻度
            float distance = 0.01f * i;
            pathMeasure.getPosTan(pathMeasure.getLength() * distance, pos11, null);
            pathMeasure1.getPosTan(pathMeasure1.getLength() * distance, pos22, null);
            lineScalePaint.setColor(Color.BLUE);
            canvas.drawLine(pos11[0], pos11[1], pos22[0], pos22[1], lineScalePaint);
        }
        if (currentScale > 100) {
            currentScale = 1;
        }


        //画圆
        Paint circlePaint = new Paint();
        circlePaint.setColor(Color.RED);
        circlePaint.setStyle(Paint.Style.STROKE);
        circlePaint.setStrokeWidth(10f);
        canvas.drawCircle(mWidth / 2, mHeight / 2, mWidth / 4 + 5, circlePaint);

        //画淡一点的圆
        Paint circlePaint1 = new Paint();
        circlePaint1.setColor(Color.RED);
        circlePaint1.setStyle(Paint.Style.STROKE);
        circlePaint1.setStrokeWidth(50f);
        circlePaint1.setAlpha(125);
        canvas.drawCircle(mWidth / 2, mHeight / 2, mWidth / 4 + 30, circlePaint1);
        canvas.restore();

        //画剪裁区 剪裁的话 就只能在剪裁区显示
        Path circlePath = new Path();
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



    //画一个不闭合圆的刻度
    private void drawScale(Canvas canvas) {
        float radius = mWidth / 4 + 120f;
        float radius1 = radius + 40;
        Paint circlePaint = new Paint();
        circlePaint.setColor(Color.BLACK);
        circlePaint.setAntiAlias(true);
        circlePaint.setStyle(Paint.Style.STROKE);
        circlePaint.setStrokeWidth(2f);
        RectF rectF = new RectF(mWidth / 2 - radius, mHeight / 2 - radius, mWidth / 2 + radius, mHeight / 2 + radius);
        Path path = new Path();
        path.arcTo(rectF, startAngle, sweepAngle, false);
        canvas.drawPath(path, circlePaint);
        RectF rectF1 = new RectF(mWidth / 2 - radius1, mHeight / 2 - radius1, mWidth / 2 + radius1, mHeight / 2 + radius1);
        Path path1 = new Path();
        path1.arcTo(rectF1, startAngle, sweepAngle, false);
        circlePaint.setColor(Color.TRANSPARENT);
        canvas.drawPath(path1, circlePaint);

        //获取两个path的相关属性
        PathMeasure pathMeasure = new PathMeasure(path, false);
        PathMeasure pathMeasure1 = new PathMeasure(path1, false);

        Paint lineScalePaint = new Paint();
        lineScalePaint.setColor(Color.BLACK);
        lineScalePaint.setStyle(Paint.Style.STROKE);
        lineScalePaint.setStrokeWidth(2f);
        for (int i = 0; i < 101; i++) { //画刻度
            float distance = 0.01f * i;
            pathMeasure.getPosTan(pathMeasure.getLength() * distance, pos11, null);
            pathMeasure1.getPosTan(pathMeasure1.getLength() * distance, pos22, null);
            canvas.drawLine(pos11[0], pos11[1], pos22[0], pos22[1], lineScalePaint);
        }
        for (int i = 0; i < currentScale; i++) { //画递变刻度
            float distance = 0.01f * i;
            pathMeasure.getPosTan(pathMeasure.getLength() * distance, pos11, null);
            pathMeasure1.getPosTan(pathMeasure1.getLength() * distance, pos22, null);
            lineScalePaint.setColor(Color.RED);
            canvas.drawLine(pos11[0], pos11[1], pos22[0], pos22[1], lineScalePaint);
        }

    }

    private float startAngle = 120;
    private float sweepAngle = 300;
    private float radius = 500;

    //画圆形刻度 第二种 用canvas.rotate旋转画布
    private void drawScaleTest(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStrokeWidth(2f);
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);
        //先画一个圆弧
        RectF rectF = new RectF(getWidth() / 2 - radius, getHeight() / 2 - radius, getWidth() / 2 + radius, getHeight() / 2 + radius);
        canvas.drawArc(rectF, startAngle, sweepAngle, false, paint);
        canvas.save();
        canvas.translate(getWidth() / 2, getHeight() / 2);
        canvas.drawPoint(0, 0, paint);
        canvas.rotate(30);
        //确定每次旋转的角度
        float rotateAngle = sweepAngle / 99;
        for (int i = 0; i < 100; i++) {
            //画刻度线
            canvas.drawLine(0, radius, 0, radius - 40, paint);
            canvas.rotate(rotateAngle);
        }
        canvas.restore();
    }

}

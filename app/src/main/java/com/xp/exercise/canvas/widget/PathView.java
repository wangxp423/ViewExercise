package com.xp.exercise.canvas.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * @类描述：Path练习
 * @创建人：Wangxiaopan
 * @创建时间：2018/1/30 0030 18:36
 * @修改人：
 * @修改时间：2018/1/30 0030 18:36
 * @修改备注：
 */

public class PathView extends View {
    public PathView(Context context) {
        super(context);
    }

    public PathView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public PathView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public PathView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    //Path多重复合路径(多个轮廓)
    //r开头的是偏移量，上一个坐标Line(x,y),rLine(dx,dy) 真是位置是(x+dx,y+dy)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        setLayerType(LAYER_TYPE_SOFTWARE, paint);
//        PathLine(canvas,paint);
//        PathMove(canvas,paint);
//        PathDirection(canvas,paint);
//        PathGraphics(canvas,paint);
        PathArc(canvas, paint);
//        PathClose(canvas,paint);
    }

    //画线
    private void PathLine(Canvas canvas, Paint paint) {
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(10f);

        Path path = new Path();
        //屏幕左上角0，0 到 200,400
        path.lineTo(200, 400);
        //屏幕左上角200,400 到 400,600
        path.lineTo(400, 600);
        //以400,600为起始点  0,0到400,600为偏移量画线 其实重点坐标是800,1200
        path.rLineTo(400, 600);
        path.rLineTo(400, 400);
        canvas.drawPath(path, paint);
    }

    //move 只是移动起始坐标
    //moveTo影响后面操作的起始坐标 不影响前面的
    //setLastPoint 影响前一步操作的坐标 同时将坐标改变为自己 变相改变了(影响)后面的坐标
    private void PathMove(Canvas canvas, Paint paint) {
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(10f);

        Path path = new Path();
        path.moveTo(100, 100);
        path.lineTo(400, 400);
//        path.rMoveTo(0,100);
        path.setLastPoint(100, 800);
        path.lineTo(400, 800);
        canvas.drawPath(path, paint);
    }

    //方向
    private void PathDirection(Canvas canvas, Paint paint) {
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(3f);
        paint.setTextSize(40f);

        Path path = new Path();
//        path.addCircle(400,400,200, Path.Direction.CW);
//        canvas.drawTextOnPath("我就是画一个圆试一试啊，就酱紫",path,0,0,paint);
        path.addCircle(400, 900, 200, Path.Direction.CCW);
        canvas.drawTextOnPath("我就是画一个圆试一试啊，就酱紫", path, 0, 0, paint);
        canvas.drawPath(path, paint);
    }

    //绘制常规图形
    private void PathGraphics(Canvas canvas, Paint paint) {
        //初始化Paint
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(10f);
        Path path = new Path();
        //以（400,200）为圆心，半径为100绘制圆
        path.addCircle(400, 200, 100, Path.Direction.CW);

        //绘制椭圆
        RectF rectF = new RectF(100, 350, 500, 600);
        //第一种方法绘制椭圆
        path.addOval(rectF, Path.Direction.CW);
        //第二种方法绘制椭圆
        path.addOval(600, 350, 1000, 600, Path.Direction.CW);

        //绘制矩形
        RectF rect = new RectF(100, 650, 500, 900);
        //第一种方法绘制矩形
        path.addRect(rect, Path.Direction.CW);
        //第一种方法绘制矩形
        path.addRect(600, 650, 1000, 900, Path.Direction.CCW);

        //绘制圆角矩形
        RectF roundRect = new RectF(100, 950, 300, 1100);
        //第一种方法绘制圆角矩形
        path.addRoundRect(roundRect, 20, 20, Path.Direction.CW);
        //第二种方法绘制圆角矩形
        path.addRoundRect(350, 950, 550, 1100, 10, 50, Path.Direction.CCW);
        //第三种方法绘制圆角矩形
        //float[] radii中有8个值，依次为左上角，右上角，右下角，左下角的rx,ry
        RectF roundRectT = new RectF(600, 950, 800, 1100);
        path.addRoundRect(roundRectT, new float[]{50, 50, 50, 50, 50, 50, 0, 0}, Path.Direction.CCW);
        //第四种方法绘制圆角矩形
        path.addRoundRect(850, 950, 1050, 1100, new float[]{0, 0, 0, 0, 50, 50, 50, 50}, Path.Direction.CCW);
        canvas.drawPath(path, paint);
    }

    //绘制圆弧
    private void PathArc(Canvas canvas, Paint paint) {
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(10f);
        //addArc和arcTo都是添加圆弧到path中，不过他们之间还是有区别的：
        // addArc是直接添加圆弧到path中，
        // arcTo会判断要绘制圆弧的起点与绘制圆弧之前path中最后的点是否是同一个点，如果不是同一个点的话，就会连接两个点。
        Path path = new Path();
        //在(400, 200, 600, 400)区域内绘制一个300度的圆弧/如果是正方形就是圆  长方形就是椭圆
        RectF rectF = new RectF(400, 200, 700, 400);
        path.addRect(rectF, Path.Direction.CW);
        path.addArc(rectF, 0, 300);
        //在(400, 600, 600, 800)区域内绘制一个90度的圆弧，并且不连接两个点
        RectF rectFTo = new RectF(400, 600, 600, 800);
        path.arcTo(rectFTo, 0, 90, true); //这里如果是false 会连接两个点
        //等价于path.addArc(rectFTo, 0, 90);
        canvas.drawPath(path, paint);
    }

    //闭合
    private void PathClose(Canvas canvas, Paint paint) {
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(10f);

        Path path = new Path();
        path.moveTo(300, 300);
        path.lineTo(300, 600);
        path.lineTo(600, 600);
        path.close();
        canvas.drawPath(path, paint);

    }
}

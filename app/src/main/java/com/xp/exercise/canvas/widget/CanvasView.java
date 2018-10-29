package com.xp.exercise.canvas.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Path.Direction;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.xp.exercise.R;

public class CanvasView extends View {

    private Path mClipPath;

    public CanvasView(Context context) {
        super(context);
    }

    public CanvasView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CanvasView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        setLayerType(View.LAYER_TYPE_SOFTWARE, new Paint());
//        DrawTest(canvas);
//        DrawCircleImage(canvas);
//        clipImageView(canvas);
//        drawPoint(canvas);
//        drawRect(canvas);
//        drawCircle(canvas);
//        drawArc(canvas);
//        drawTranslate(canvas);
//        drawRotate(canvas);
//        drawScale(canvas);
        drawSaveRestore(canvas);

    }

    private void DrawTest(Canvas canvas) {
        canvas.drawRGB(180, 250, 250);
        Paint paint = new Paint();
        canvas.save();
        canvas.clipRect(new Rect(100, 100, 300, 300));
        canvas.drawColor(Color.BLUE);//裁剪区域的rect变为蓝色   
        canvas.drawRect(new Rect(0, 0, 100, 100), paint);//在裁剪的区域之外，不能显示
        canvas.drawCircle(150, 150, 50, paint);//在裁剪区域之内，能显示
        canvas.restore();
    }

    private Canvas DrawCircleImage(Canvas canvas) {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.background_mine_head).copy(Bitmap.Config.ARGB_8888, true);
        canvas.drawBitmap(bitmap, new Matrix(), new Paint());
        int bitmapWidth = bitmap.getWidth();
        int bitmapHeight = bitmap.getHeight();
        canvas.save();
        Path clipPath = new Path();
        RectF clipRect = new RectF(0, 0, bitmapHeight, bitmapHeight);
        clipPath.addOval(clipRect, Direction.CW);
//        clipPath.addCircle(bitmapWidth/2, bitmapHeight/2, bitmapHeight/2, Direction.CW);
        canvas.clipPath(clipPath);
//        canvas.clipRect(clipRect);
        canvas.drawBitmap(bitmap, null, clipRect, new Paint());
        return canvas;
    }

    private Canvas clipImageView(Canvas canvas) {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.background_mine_head).copy(Bitmap.Config.ARGB_8888, true);
        Log.d("Test", "bitmap.width = " + bitmap.getWidth() + "   bitmap.height = " + bitmap.getHeight());
//        canvas.drawBitmap(bitmap, null, clipRect, new Paint());
        canvas.save();
        Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight() / 2);
        canvas.clipRect(rect);
        Matrix matrix = new Matrix();
        matrix.postScale(1f, 1f); //图片正常比例显示
//        canvas.drawBitmap(bitmap, new Matrix(), new Paint()); //正常显示图片
//        canvas.drawBitmap(bitmap, null, rect , new Paint()); //剪切 但是图片压缩了
        canvas.drawBitmap(bitmap, matrix, new Paint()); //剪切 图片正常显示(只有上半部分)
        canvas.restore();
        return canvas;
    }

    //画点 划线
    private void drawPoint(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStrokeWidth(15f);
        canvas.drawPoint(100, 100, paint);
        float[] points = {200, 200, 200, 300};//每两个点代表一个坐标
        canvas.drawPoints(points, paint);

        canvas.drawLine(200, 1200, 1200, 1200, paint);
        float[] pts = {200, 700, 700, 1200, 700, 1200, 1200, 700};
        canvas.drawLines(pts, paint);
    }

    //画矩形 圆角矩形
    private void drawRect(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStrokeWidth(10f);
        paint.setStyle(Paint.Style.STROKE);
        //1 通过坐标画
        canvas.drawRect(100, 100, 1000, 300, paint);
        //2 通过Rect画
        Rect rect = new Rect(100, 350, 1000, 550);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        canvas.drawRect(rect, paint);
        //通过 Rectf画
        RectF rectF = new RectF(100, 600, 1000, 800);
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawRect(rectF, paint);

        //圆角矩形
        float rRadius = 20f;
        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(10f);
        //第一种 必须SDK>=21以上使用
        canvas.drawRoundRect(100, 850, 1000, 1050, rRadius, rRadius, paint);
        //第二种通过RectF 来画圆角矩形
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        RectF mRect = new RectF(100, 1200, 1000, 1500);
        canvas.drawRoundRect(mRect, rRadius, rRadius, paint);
    }

    //画圆 椭圆
    private void drawCircle(Canvas canvas) {
        Paint mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(10F);
        mPaint.setColor(Color.RED);
        canvas.drawCircle(250, 250, 200, mPaint);

        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.BLUE);
        mPaint.setStrokeWidth(10f);
        //第一种，通过left top right bottom来确定矩形，然后画矩形的内切椭圆
        canvas.drawOval(300, 500, 900, 700, mPaint);
        RectF mRectF = new RectF(300, 800, 1000, 1000);
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        //第二种，通过RectF 画椭圆，和第一种原理是一样的
        canvas.drawOval(mRectF, mPaint);
    }

    //画弧
    private void drawArc(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStrokeWidth(5f);
        paint.setAntiAlias(true);
        paint.setDither(true);
        paint.setStyle(Paint.Style.FILL);
        //这里的弧度 是一个圆360° X正方向为0° 计算的。0-360
        //这个TranstionManger中弧线过度动画中的ARC应该是一样的 那个弧度只能在0-90因为以屏幕左上角为原点只有90度(我猜的)
        //通过下面前两个圆弧对比很明显，useCenter为true最后图形是一个扇形
        //useCenter为false，则最后图形是起始点之间连线和圆弧围成的面积
        canvas.drawArc(100, 100, 500, 500, 0, 90, true, paint);
        canvas.drawArc(100, 500, 500, 900, 30, 90, false, paint);
        //通过RectF 设置的区域范围画弧
        RectF mRectF = new RectF(100, 900, 500, 1300);
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawArc(mRectF, 30, 90, true, paint);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawArc(mRectF, 120, 60, false, paint);
    }

    //translate平移
    private void drawTranslate(Canvas canvas) {
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        canvas.translate(getMeasuredWidth() / 2, 400);
        //绘制一个红圆
        paint.setColor(Color.RED);
        canvas.drawCircle(0, 0, 100, paint);
        //坐标系原点在前面位置的基础上再往下移动200像素
        canvas.translate(0, 200);
        //绘制一个黄色圆
        paint.setColor(Color.YELLOW);
        canvas.drawCircle(0, 0, 100, paint);
        //坐标系原点在前面位置的基础上再往下移动200像素
        canvas.translate(0, 200);
        //绘制一个绿色圆
        paint.setColor(Color.GREEN);
        canvas.drawCircle(0, 0, 100, paint);
    }

    //rotate旋转
    private void drawRotate(Canvas canvas) {
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        //将坐标系移动到屏幕中心
        canvas.translate(getMeasuredWidth() / 2, getMeasuredHeight() / 2);
        RectF rectF = new RectF(-300, -300, 0, 0);
        //绘制一个红色矩形
        paint.setColor(Color.RED);
        canvas.drawRect(rectF, paint);
        //将坐标系旋转180度，不会影响前面已经绘制的图形
        canvas.rotate(180);
        paint.setStyle(Paint.Style.FILL);
        //绘制一个绿色矩形
        paint.setColor(Color.GREEN);
        canvas.drawRect(rectF, paint);
        //X平移-150 在旋转 发现是上下显示 因为中心点变了。
        canvas.rotate(180, -150, 0);
    }

    //scale缩放 save保存 restore恢复
    private void drawScale(Canvas canvas) {
        Paint paint = new Paint();
        //坐标原点移到屏幕中心
        canvas.translate(getMeasuredWidth() / 2, getMeasuredHeight() / 2);
        RectF rectF = new RectF(-300, -300, 0, 0);
        paint.setColor(Color.RED);
        //左上角绘制红色矩形
        canvas.drawRect(rectF, paint);
        //X轴 Y轴分别缩放到原来的1/2并以原点(0,0)
//        canvas.scale(0.5f,0.5f);
//        canvas.scale(0.5f,-0.5f);
//        canvas.scale(-0.5f,0.5f);
        canvas.scale(-0.5f, -0.5f); //负数 坐标系反向缩放 需要翻转(平移180°)
//        canvas.scale(-0.5f, -0.5f,100,0); //px py中心点平移
        //绘制绿色的圆
        paint.setColor(Color.GREEN);
        canvas.drawRect(rectF, paint);
    }

    //    save：保存之前Canvas的状态，save之后，可以调用Canvas的平移、缩放、旋转、错切、裁剪等操作。
//    restore：恢复Canvas之前保存的状态，防止save后对Canvas执行的操作对后续的绘制有影响。
//    canvas的save 和 restore是成对使用(restore只能比save少，不能多)
    private void drawSaveRestore(Canvas canvas) {
        //如果 注释掉 save restore 两个圆在屏幕中间重合
        Paint paint = new Paint();
        //保存画布
        canvas.save();
        //坐标原点移到屏幕中心
        canvas.translate(getMeasuredWidth() / 2, getMeasuredHeight() / 2);
        //以屏幕中心为坐标原点在（60,50）为圆心处绘制红色圆
        paint.setColor(Color.RED);
        canvas.drawCircle(60, 50, 100, paint);
        //恢复画布
        canvas.restore();
        //恢复画布后，坐标原点（0,0）默认在屏幕左上角，
        //即以屏幕左上角为坐标原点在（60,50）为圆心处绘制黑色圆
        paint.setColor(Color.BLACK);
        canvas.drawCircle(60, 50, 50, paint);
    }


}

package com.xp.exercise.canvas;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

import com.xp.exercise.R;
import com.xp.exercise.util.LogUtils;

/**
 * @类描述：贝塞尔曲线 练习
 * @创建人：Wangxiaopan
 * @创建时间：2018/1/31 0031 15:14
 * @修改人：
 * @修改时间：2018/1/31 0031 15:14
 * @修改备注：
 */

public class PathCubic extends View {
    public PathCubic(Context context) {
        super(context);
    }

    public PathCubic(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public PathCubic(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public PathCubic(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
//        int min = Math.min(width,height);
//        setMeasuredDimension(min,min);
        LogUtils.d("Test", "width = " + width + " height = " + height);
        LogUtils.d("Test", "getWidth = " + getWidth() + " getHeight = " + getHeight());
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        LogUtils.d("Test", "change = " + changed + " left = " + left + " top = " + top + "right = " + right + " bottom = " + bottom);
    }

    Path mPath = new Path();

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(10f);
        //加下面这一句话 onDraw会不停的刷新 但是它并不影响方法中的全局变量值 而且会占用比较大的CUP资源
//        setLayerType(LAYER_TYPE_SOFTWARE,null);
        //LAYER_TYPE_NONE:view按一般方式绘制，不使用离屏缓冲．这是默认的行为．
        //LAYER_TYPE_HARDWARE:如果应用被硬加速了，view会被绘制到一个硬件纹理中．如果应用没被硬加速，此类型的layer的行为同于LAYER_TYPE_SOFTWARE．
        //LAYER_TYPE_SOFTWARE:view被绘制到一个bitmap中．
//        drawTest(canvas,paint);

//        drawBubble(canvas, paint);

//        drawWave(canvas, paint);

//        startAnimator();
//        startWave(canvas, paint);

//        canvas.save();
//        canvas.translate(getWidth()/2,getHeight()/2);
//        userCubicDrawCircle(canvas, paint);
//        canvas.restore();

        drawScaleTest(canvas, paint);
        drawPurifier(canvas, paint);
        arrawRotate(canvas, paint);

        //轨迹
//        canvas.drawPath(mPath,paint);//重写onTouchEvent
    }

    private void drawTest(Canvas canvas, Paint paint) {
        Path path = new Path();
        path.moveTo(200, 200);
        path.quadTo(300, 400, 600, 200);
        path.moveTo(200, 700);
        path.cubicTo(300, 400, 500, 0, 700, 700);
        canvas.drawPath(path, paint);

//        paint.setStyle(Paint.Style.FILL);
        path.addCircle(300, 1000, 100, Path.Direction.CW);
        Path path1 = new Path();
        paint.setColor(Color.BLUE);
        path1.addCircle(300, 1100, 100, Path.Direction.CW);
        path.op(path1, Path.Op.UNION); //两个path交叉的几种情况
        canvas.drawPath(path, paint);

    }

    //画一个聊天气泡
    private void drawBubble(Canvas canvas, Paint paint) {
        RectF rectF = null;
        final float startX = 300f;
        final float startY = 400f;
        final float radius = 100f;
        final float sweepAngle = 90f; //弧度暂且是90°吧 比较好计算
        final float width = 600f;
        final float heith = 300f;
        Path path = new Path();
        path.moveTo(startX, startY + radius);
        //画左上角
        rectF = new RectF(startX, startY, startX + radius, startY + radius);
        path.arcTo(rectF, 180, sweepAngle);
        path.lineTo(width + startX - radius, startY);
        //画右上角圆角
        rectF = new RectF(width + startX - radius, startY, width + startX, startY + radius);
        path.arcTo(rectF, 270, sweepAngle);
        path.lineTo(width + startX, heith + startY - radius);
        //画右下角圆角
        rectF = new RectF(width + startX - radius, heith + startY - radius, width + startX, heith + startY);
        path.arcTo(rectF, 0, sweepAngle);
        path.lineTo(startX + radius, heith + startY);
        //画左下角
        rectF = new RectF(startX, heith + startY - radius, startX + radius, heith + startY);
        path.arcTo(rectF, 90, sweepAngle);

        //画一个小三角
        path.lineTo(startX, startY + heith - (heith / 3));
        path.lineTo(startX / 2, startY + heith - (heith / 2));
        path.lineTo(startX, startY + heith - (heith / 3 * 2));
        path.close();
        canvas.drawPath(path, paint);
    }

    //画一个波浪线
    private void drawWave(Canvas canvas, Paint paint) {
        paint.setStrokeWidth(2f);
        int mWaveHeight = 100;
        Path path = new Path();
        int mWaveWidth = getWidth();
        int centerY = getHeight() / 2;
        //屏幕外 画第一段波纹的第一条线
        path.moveTo(-mWaveWidth, centerY);
        path.quadTo(-mWaveWidth * 3 / 4, centerY + mWaveHeight, -mWaveWidth / 2, centerY);
        //屏幕外 画第一段波纹的第二条线
        path.quadTo(-mWaveWidth / 4, centerY - mWaveHeight, 0, centerY);

        //屏幕内 画第二条波纹的第一条线
        path.quadTo(mWaveWidth / 4, centerY + mWaveHeight, mWaveWidth / 2, centerY);
        //屏幕内 画第二条波纹的第二条线
        path.quadTo(mWaveWidth * 3 / 4, centerY - mWaveHeight, mWaveWidth, centerY);

        //下面是 实体填充波浪 将path闭合 然后使用fill进行填充色
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        path.lineTo(getWidth(), getHeight());
        path.lineTo(0, getHeight());
        path.close();

        canvas.drawPath(path, paint);

    }

    //波浪效果 加一个offset平移值(不断绘制来实现)
    int offset, startY;
    boolean isStart = false;
    int pianyi = 5;

    private void startAnimator() {
        if (isStart) return;
        startY = getHeight() / 2;
        ValueAnimator animator = ValueAnimator.ofInt(0, getWidth());
        animator.setDuration(1000);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setInterpolator(new LinearInterpolator());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                offset = (int) animation.getAnimatedValue();
                LogUtils.d("Test", "offset = " + offset);
                postInvalidate();
            }
        });
        if (!isStart) {
            animator.start();
            isStart = true;
        }
    }

    private void startWave(Canvas canvas, Paint paint) {
        paint.setStrokeWidth(2f);
        int mWaveHeight = 100;
        Path path = new Path();
        int mWaveWidth = getWidth();
        int centerY = getHeight() / 2;
        path.reset();
        startY = startY + pianyi;
        if (startY >= getHeight()) return;
        //使用for循环
        path.moveTo(-mWaveWidth + offset, startY);//设置一个起点
        for (int i = 0; i < 2; i++) {
            path.quadTo(-mWaveWidth * 3 / 4 + (i * mWaveWidth) + offset, startY + mWaveHeight, -mWaveWidth / 2 + (i * mWaveWidth) + offset, startY);
            path.quadTo(-mWaveWidth / 4 + (i * mWaveWidth) + offset, startY - mWaveHeight, 0 + (i * mWaveWidth) + offset, startY);
        }

        //下面是 实体填充波浪 将path闭合 然后使用fill进行填充色
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        path.lineTo(getWidth(), getHeight());
        path.lineTo(0, getHeight());
        path.close();
        canvas.drawPath(path, paint);
    }

    //使用四条三阶曲线画一个圆
    private void userCubicDrawCircle(Canvas canvas, Paint paint) {
        Path path = new Path();
        int radius = 200;
        float magic = /**(float) Math.sin(Math.PI * 15 / 180)**/0.551915024494f;
        canvas.drawCircle(0, 0, radius, paint);
        paint.setColor(Color.GREEN);
        // 4条三阶曲线来表示圆
        // 右上1/4圆
        path.lineTo(0, -radius);
        path.cubicTo(radius * magic, -radius
                , radius, -radius * magic
                , radius, 0);
        path.lineTo(0, 0);

        // 右下1/4圆
        path.lineTo(0, radius);
        path.cubicTo(radius * magic, radius
                , radius, radius * magic
                , radius, 0);
        path.lineTo(0, 0);

        // 左上1/4圆
        path.lineTo(0, -radius);
        path.cubicTo(-radius * magic, -radius
                , -radius, -radius * magic
                , -radius, 0);
        path.lineTo(0, 0);

        // 左下1/4圆
        path.lineTo(0, radius);
        path.cubicTo(-radius * magic, radius
                , -radius, radius * magic
                , -radius, 0);
        path.lineTo(0, 0);
        canvas.drawPath(path, paint);
    }

    //箭头旋转
    float currentValue = 0.0f;
    float[] pos = new float[2];
    float[] tan = new float[2];
    Bitmap mIndicator = BitmapFactory.decodeResource(getResources(), R.drawable.icon_back);//箭头
    Matrix matrix = new Matrix();

    private void arrawRotate(Canvas canvas, Paint paint) {
        paint.setStrokeWidth(2f);
        paint.setColor(Color.GREEN);
        //原点移动到屏幕中央
        canvas.translate(getWidth() / 2, getHeight() / 2);
        //画圆
        Path path = new Path();
        path.addCircle(0, 0, 200, Path.Direction.CW);
        canvas.drawPath(path, paint);

        PathMeasure pathMeasure = new PathMeasure(path, false);
        currentValue += 0.005;
        if (currentValue >= 1) {
            currentValue = 0;
        }
        pathMeasure.getPosTan(pathMeasure.getLength() * currentValue, pos, tan);
        //以下两个方法的区别在于atan2能够区别表示45°和 135° 而atan不能
        float degree = (float) ((Math.atan2(tan[1], tan[0])) * 180 / Math.PI);
        float degree1 = (float) (Math.atan(tan[1] / tan[0]) * 180 / Math.PI); //这是一个弧度
        LogUtils.d("Test", "degree = " + degree + "  degree1 = " + degree1);
        //第一种方式 旋转canvas
//        canvas.save();
//        canvas.rotate(degree,pos[0],pos[1]);
//        int halfDrawableWidth = mIndicator.getWidth()/2;
//        int halfDrawableHeight = mIndicator.getHeight()/2;
//        Rect bounds = new Rect((int)pos[0]-halfDrawableWidth,(int)pos[1]-halfDrawableHeight,(int)pos[0]+halfDrawableWidth,(int)pos[1]+halfDrawableHeight);
//        canvas.drawBitmap(mIndicator,null,bounds,paint);
//        canvas.restore();
//        invalidate();
        //第二种方式 利用Matrix  这种方式最好
        matrix.reset();
        matrix.postRotate(degree, mIndicator.getWidth() / 2, mIndicator.getHeight() / 2); //旋转图片
        //便宜图片绘制中心，将绘制中心与当前点重合
        matrix.postTranslate(pos[0] - mIndicator.getWidth() / 2, pos[1] - mIndicator.getHeight() / 2);
        canvas.drawBitmap(mIndicator, matrix, paint);
        invalidate();
        //第三种方式 利用getMatrix
//        matrix.reset();
//        pathMeasure.getMatrix(pathMeasure.getLength() * currentValue, matrix, PathMeasure.TANGENT_MATRIX_FLAG | PathMeasure.POSITION_MATRIX_FLAG);
//        matrix.preTranslate(-mIndicator.getWidth() / 2, -mIndicator.getHeight() / 2);
//        canvas.drawBitmap(mIndicator, matrix, paint);
//        invalidate();

        drawScale(canvas, paint);
    }

    private int totalScale = 100; //100个刻度
    private float[] pos11 = new float[2];
    private float[] pos22 = new float[2];
    private int currentScale = 1;

    //画圆形刻度 第一种 用path
    private void drawScale(Canvas canvas, Paint paint) {
        Paint paint1 = new Paint();
        paint1.setColor(Color.TRANSPARENT);
        paint1.setStyle(Paint.Style.STROKE);
        paint1.setStrokeWidth(2f);
        //画圆
        Path path = new Path();
        path.addCircle(0, 0, 240, Path.Direction.CW);
        canvas.drawPath(path, paint1);
        //画圆
        Path path1 = new Path();
        path1.addCircle(0, 0, 280, Path.Direction.CW);
        canvas.drawPath(path1, paint1);

        PathMeasure pathMeasure = new PathMeasure(path, false);
        PathMeasure pathMeasure1 = new PathMeasure(path1, false);

        for (int i = 1; i < 101; i++) {
            float distance = 0.01f * i;
            pathMeasure.getPosTan(pathMeasure.getLength() * distance, pos11, null);
            pathMeasure1.getPosTan(pathMeasure1.getLength() * distance, pos22, null);
            canvas.drawLine(pos11[0], pos11[1], pos22[0], pos22[1], paint);
        }
        currentScale++;
        for (int i = 1; i < currentScale; i++) {
            float distance = 0.01f * i;
            pathMeasure.getPosTan(pathMeasure.getLength() * distance, pos11, null);
            pathMeasure1.getPosTan(pathMeasure1.getLength() * distance, pos22, null);
            canvas.drawLine(pos11[0], pos11[1], pos22[0], pos22[1], new Paint());
        }
        if (currentScale > 100) {
            currentScale = 1;
        }
        postInvalidateDelayed(100);
    }

    private float startAngle = 120;
    private float sweepAngle = 300;
    private float radius = 350;

    //画圆形刻度 第二种 用canvas.rotate旋转画布
    private void drawScaleTest(Canvas canvas, Paint paint) {
        paint.setStrokeWidth(2f);
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

    //画 空气净化器
    private float startPAngle = 0;
    private float sweepPAngle = 30;
    private float pRadius = 300;
    private float pRadiu1 = 240;

    private void drawPurifier(Canvas canvas, Paint paint) {
        canvas.drawCircle(getWidth() / 2, getHeight() / 2, pRadius, paint);
        paint.setStrokeWidth(120f);
        paint.setAntiAlias(true);
        paint.setAlpha(60);
        Path path = new Path();
        RectF rectF = new RectF(getWidth() / 2 - pRadiu1, getHeight() / 2 - pRadiu1, getWidth() / 2 + pRadiu1, getHeight() / 2 + pRadiu1);
        int total = (int) (360 / sweepPAngle);
        for (int i = 0; i < total; i++) {
            if (i % 2 == 0) {
                //这一步用path画的话 如果加了alpha 不知道为啥 色块颜色不一样
//                path.arcTo(rectF,sweepPAngle * i, sweepPAngle,true);
//                canvas.drawPath(path, paint);
                canvas.drawArc(rectF, sweepPAngle * i, sweepPAngle, false, paint);
            }
        }
    }

//    float mPreX,mPreY;
//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        switch (event.getAction()){
//            case MotionEvent.ACTION_DOWN:{
//                mPath.moveTo(event.getX(),event.getY());
//                mPreX = event.getX();
//                mPreY = event.getY();
//                return true;
//            }
//            case MotionEvent.ACTION_MOVE:{
////                mPath.lineTo(event.getX(),event.getY());//不够平滑
//                float endX = (mPreX+event.getX())/2;
//                float endY = (mPreY+event.getY())/2;
//                mPath.quadTo(mPreX,mPreY,endX,endY);
//                mPreX = event.getX();
//                mPreY =event.getY();
//                invalidate();
//            }
//            default:
//                break;
//        }
//        return super.onTouchEvent(event);
//    }
}

package com.xp.exercise.canvas.widget;

import android.animation.PropertyValuesHolder;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.SweepGradient;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.xp.exercise.R;
import com.xp.exercise.util.LogUtils;

import java.util.Calendar;

/**
 * @类描述：时钟
 * @创建人：Wangxiaopan
 * @创建时间：2018/3/1 0001 11:13
 * @修改人：
 * @修改时间：2018/3/1 0001 11:13
 * @修改备注：
 */

public class OclockView extends View {
    public OclockView(Context context) {
        super(context);
        init(context);
    }

    public OclockView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public OclockView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public OclockView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        width = widthSize;
        height = heightSize;
        //圆心坐标
        mCenterX = width / 2;
        mCenterY = height / 2;
        //圆半径
        radius = width / 3;
        mMaxCanvasTranslate = 0.02f * radius;

        mSweepGradient = new SweepGradient(mCenterX, mCenterY,
                new int[]{grayColor, weithColor}, new float[]{0.75f, 1});
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    //时钟正方形的宽高
    private int width, height;
    //圆心点x和y坐标,x=y
    private int mCenterX, mCenterY;
    //刻度笔,为浅白色
    private Paint paint;
    //当前刻度笔，为白色
    private Paint time_paint;
    //中心圆环笔
    private Paint center_paint;
    //渐变比
    private Paint change_paint;
    //秒针比
    private Paint sPaint;
    //分针比
    private Paint mPaint;
    //是真比
    private Paint hPaint;
    //圆的半径,设置为屏幕的三分之一
    private int radius;
    //屏幕边缘到圆环的距离
    private int distance;
    /* 触摸时作用在Camera的矩阵 */
    private Matrix mCameraMatrix;
    //用于实现3d效果
    private Camera mCamera;
    //处理钟表动画
    private ValueAnimator mValueAnimator;
    //camera旋转的最大角度
    private float mMaxCameraRotate = 10;
    /* camera绕X轴旋转的角度 */
    private float mCameraRotateX;
    /* camera绕Y轴旋转的角度 */
    private float mCameraRotateY;
    /* 指针的在x轴的位移 */
    private float mCanvasTranslateX;
    /* 指针的在y轴的位移 */
    private float mCanvasTranslateY;
    /* 指针的最大位移 */
    private float mMaxCanvasTranslate;
    /* 时针角度 */
    private float mHourDegree;
    /* 分针角度 */
    private float mMinuteDegree;
    /* 秒针角度 */
    private float mSecondDegree;

    /* 渐变矩阵，作用在SweepGradient */
    private Matrix mGradientMatrix;
    /* 梯度扫描渐变 */
    private SweepGradient mSweepGradient;

    private int weithColor;
    private int grayColor;
    private Canvas mCanvas;

    /* 时针路径 */
    private Path mHourHandPath;
    /* 分针路径 */
    private Path mMinuteHandPath;
    /* 秒针路径 */
    private Path mSecondHandPath;
    private RectF mCircleRectF;

    private void init(Context context) {
        paint = new Paint();
        paint.setColor(Color.parseColor("#88ffffff"));
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);
        center_paint = new Paint();
        center_paint.setColor(Color.parseColor("#ffffff"));
        center_paint.setStyle(Paint.Style.STROKE);
        center_paint.setAntiAlias(true);
        center_paint.setStrokeWidth(5f);
        time_paint = new Paint();
        time_paint.setColor(Color.parseColor("#88ffffff"));
        time_paint.setStyle(Paint.Style.STROKE);
        time_paint.setAntiAlias(true);
        DashPathEffect dashPathEffect = new DashPathEffect(new float[]{5, 5}, 0);
        time_paint.setPathEffect(dashPathEffect);

        mCameraMatrix = new Matrix();
        mCamera = new Camera();

        mGradientMatrix = new Matrix();

        weithColor = Color.parseColor("#ffffff");
        grayColor = Color.parseColor("#88ffffff");

        mSecondHandPath = new Path();
        mMinuteHandPath = new Path();
        mHourHandPath = new Path();

        sPaint = new Paint();
        sPaint.setColor(Color.parseColor("#ffffff"));
        sPaint.setStyle(Paint.Style.FILL);
        sPaint.setAntiAlias(true);

        change_paint = new Paint();
        change_paint.setStyle(Paint.Style.STROKE);
        change_paint.setAntiAlias(true);
        DashPathEffect dashPathEffect2 = new DashPathEffect(new float[]{5, 5}, 0);
        change_paint.setPathEffect(dashPathEffect2);

        mCircleRectF = new RectF();
        mPaint = new Paint();
        mPaint.setColor(Color.parseColor("#ffffff"));
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setAntiAlias(true);

        hPaint = new Paint();
        hPaint.setColor(Color.parseColor("#88ffffff"));
        hPaint.setStyle(Paint.Style.FILL);
        hPaint.setAntiAlias(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(getResources().getColor(R.color.ocbg));
        set3DMatrix(canvas);
        getTime();
        drawOutSideCircle(canvas);
        drawOclockScale(canvas);
        drawSecondPointer(canvas);
        drawMinPointer(canvas);
        drawHourPointer(canvas);
        invalidate();
    }

    //画外部圆弧和数字
    private void drawOutSideCircle(Canvas canvas) {
        paint.setTextSize(30);
        int outRadius = radius + 40;
        canvas.drawText("12", mCenterX - 20, mCenterY - outRadius + 10, paint);
        canvas.drawText("3", mCenterX + outRadius - 10, mCenterY + 10, paint);
        canvas.drawText("6", mCenterX - 10, mCenterY + outRadius + 10, paint);
        canvas.drawText("9", mCenterX - outRadius - 10, mCenterY + 10, paint);
        RectF rectF = new RectF(mCenterX - outRadius, mCenterY - outRadius, mCenterX + outRadius, mCenterY + outRadius);
        canvas.drawArc(rectF, 275, 80, false, paint);
        canvas.drawArc(rectF, 5, 80, false, paint);
        canvas.drawArc(rectF, 95, 80, false, paint);
        canvas.drawArc(rectF, 185, 80, false, paint);
        //画一个小圆  作为针的旋转圈
        canvas.drawCircle(mCenterX, mCenterY, 8, center_paint);
    }

    //画外面刻度
    private void drawOclockScale(Canvas canvas) {
        canvas.save();
        //处理渐变色
        mGradientMatrix.setRotate(mSecondDegree - 90, mCenterX, mCenterY);
        mSweepGradient.setLocalMatrix(mGradientMatrix);
        change_paint.setShader(mSweepGradient);
        change_paint.setStrokeWidth(30);
        canvas.drawCircle(mCenterX, mCenterY, radius, change_paint);
        canvas.restore();
    }

    //画秒针
    private void drawSecondPointer(Canvas canvas) {
        canvas.save();
        canvas.rotate(mSecondDegree, mCenterX, mCenterY);
        mSecondHandPath.reset();
        //半径是radius 但是 stroke = 30,所以半径里面还有15
        int paintStroke = 15;
        //小三角高度,边长
        int mTriangleHeight = 25;
        float mTriangleSide = (float) (Math.atan(20 * Math.PI / 180) * 40 * 2);
        float secondY = mCenterY - radius + paintStroke + 10;
        mSecondHandPath.moveTo(mCenterX, secondY);
        mSecondHandPath.lineTo(mCenterX - mTriangleSide / 2, secondY + mTriangleHeight);
        mSecondHandPath.lineTo(mCenterX + mTriangleSide / 2, secondY + mTriangleHeight);
        mSecondHandPath.close();
        canvas.drawPath(mSecondHandPath, sPaint);
        canvas.restore();
    }

    //画分针
    private void drawMinPointer(Canvas canvas) {
        canvas.save();
        canvas.rotate(mMinuteDegree, mCenterX, mCenterY);
        mMinuteHandPath.reset();
        float minY = mCenterY - radius + 50;
        float minLength = radius - 50 - 6; //还要减去中心园的半径
        //移动path坐标点到分针跟左下角的位置
        mMinuteHandPath.moveTo(mCenterX - 2.5f, mCenterY - 5);
        mMinuteHandPath.lineTo(mCenterX - 1.5f, minY + 1);
        //画圆弧 二阶贝塞尔曲线
        mMinuteHandPath.quadTo(mCenterX, minY, mCenterX + 1.5f, minY + 1);
        mMinuteHandPath.lineTo(mCenterX + 2.5f, mCenterY - 5);
        mMinuteHandPath.close();
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setAntiAlias(true);
        canvas.drawPath(mMinuteHandPath, mPaint);
        canvas.restore();
    }

    //画时针
    private void drawHourPointer(Canvas canvas) {
        canvas.save();
        canvas.rotate(mHourDegree, mCenterX, mCenterY);
        mHourHandPath.reset();
        float minY = mCenterY - radius + 80;
        float minLength = radius - 80 - 6; //还要减去中心园的半径
        //移动path坐标点到分针跟左下角的位置
        mHourHandPath.moveTo(mCenterX - 4, mCenterY - 5);
        mHourHandPath.lineTo(mCenterX - 2, minY + 1);
        //画圆弧 二阶贝塞尔曲线
        mHourHandPath.quadTo(mCenterX, minY, mCenterX + 2, minY + 1);
        mHourHandPath.lineTo(mCenterX + 4, mCenterY - 5);
        mHourHandPath.close();
        hPaint.setStyle(Paint.Style.FILL);
        hPaint.setAntiAlias(true);
        canvas.drawPath(mHourHandPath, hPaint);
        canvas.restore();
    }

    //获取系统时间
    private void getTime() {
        Calendar calendar = Calendar.getInstance();
        //毫秒
        float milliSecond = calendar.get(Calendar.MILLISECOND);
        //秒
        float second = calendar.get(Calendar.SECOND) + milliSecond / 1000;
        //分
        float minute = calendar.get(Calendar.MINUTE) + second / 60;
        //时
        float hour = calendar.get(Calendar.HOUR) + minute / 60;
        //求出三个指针的角度 按照360来计算
        mSecondDegree = second * 6;
        mMinuteDegree = 6 * minute;
        mHourDegree = 30 * hour;
        LogUtils.d("second = " + mSecondDegree + "  min = " + mMinuteDegree + "  hour = " + mHourDegree);
    }

    //当手指触摸View 通过得到的触摸点，然后通过定义的比例值，得到Camera的旋转大小和平移大小
    //Camera默认旋转中心在View左上角，我们通过Matrix来改变旋转中心
    private void set3DMatrix(Canvas canvas) {
        mCameraMatrix.reset();
        mCamera.save();
        mCamera.rotateX(mCameraRotateX);
        mCamera.rotateY(mCameraRotateY);
        mCamera.getMatrix(mCameraMatrix);
        mCamera.restore();
        //camera默认旋转是View左上角为旋转中心
        //所以动作之前要，设置矩阵位置 -mTextHeight-mOutSideRadius
        mCameraMatrix.preTranslate(-getWidth() / 2, -getHeight() / 2);
        //动作之后恢复位置
        mCameraMatrix.postTranslate(getWidth() / 2, getHeight() / 2);
        //基于 Canvas 当前的变换，叠加上 Matrix 中的变换。
        canvas.concat(mCameraMatrix);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (mValueAnimator != null && mValueAnimator.isRunning()) {
                    mValueAnimator.cancel();
                }
                getCameraRotate(event);
                getViewTranslate(event);
                break;
            case MotionEvent.ACTION_MOVE:
                getCameraRotate(event);
                getViewTranslate(event);
                break;
            case MotionEvent.ACTION_UP:
                startOclockAnmi();
                break;
        }
        return true;
    }

    private void startOclockAnmi() {
        final String camerRotateXName = "cameraRotateX";
        final String camerRotateYName = "cameraRotateY";
        final String canvasTranslateXName = "canvasTranslateX";
        final String canvasTranslateYName = "canvasTranslateY";
        PropertyValuesHolder cameraRotateXHolder =
                PropertyValuesHolder.ofFloat(camerRotateXName, mCameraRotateX, 0);
        PropertyValuesHolder cameraRotateYHolder =
                PropertyValuesHolder.ofFloat(camerRotateYName, mCameraRotateY, 0);
        PropertyValuesHolder canvasTranslateXHolder =
                PropertyValuesHolder.ofFloat(canvasTranslateXName, mCanvasTranslateX, 0);
        PropertyValuesHolder canvasTranslateYHolder =
                PropertyValuesHolder.ofFloat(canvasTranslateYName, mCanvasTranslateY, 0);
        mValueAnimator = ValueAnimator.ofPropertyValuesHolder(cameraRotateXHolder,
                cameraRotateYHolder, canvasTranslateXHolder, canvasTranslateYHolder);

        mValueAnimator.setInterpolator(new TimeInterpolator() {
            @Override
            public float getInterpolation(float input) {
                //http://inloop.github.io/interpolator/
                float f = 0.571429f;
                return (float) (Math.pow(2, -2 * input) * Math.sin((input - f / 4) * (2 * Math.PI) / f) + 1);
            }
        });
        mValueAnimator.setDuration(1000);
        mValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mCameraRotateX = (float) animation.getAnimatedValue(camerRotateXName);
                mCameraRotateY = (float) animation.getAnimatedValue(camerRotateXName);
                mCanvasTranslateX = (float) animation.getAnimatedValue(canvasTranslateXName);
                mCanvasTranslateY = (float) animation.getAnimatedValue(canvasTranslateYName);
            }
        });
        mValueAnimator.start();

    }

    private void getViewTranslate(MotionEvent event) {
        float translateX = (event.getX() - getWidth() / 2);
        float translateY = (event.getY() - getHeight() / 2);
        //求出此时位移的大小与半径之比
        float[] percentArr = getPercent(translateX, translateY);
        //最终位移的大小按比例匀称改变
        mCanvasTranslateX = percentArr[0] * mMaxCanvasTranslate;
        mCanvasTranslateY = percentArr[1] * mMaxCanvasTranslate;
    }

    //获取camera旋转角度
    private void getCameraRotate(MotionEvent event) {
        float retateX = -(event.getY() - getHeight() / 2);
        float retateY = -(event.getX() - getWidth() / 2);
        //求出自旋转大小与半径比
        float[] precentArc = getPercent(retateX, retateY);
        mCameraRotateX = precentArc[0] * mMaxCameraRotate;
        mCameraRotateY = precentArc[1] * mMaxCameraRotate;

    }

    // 获取一个操作旋转或位移大小的比例
    private float[] getPercent(float x, float y) {
        float[] percentArr = new float[2];
        float percentX = x / radius;
        float percentY = y / radius;
        if (percentX > 1) {
            percentX = 1;
        } else if (percentX < -1) {
            percentX = -1;
        }
        if (percentY > 1) {
            percentY = 1;
        } else if (percentY < -1) {
            percentY = -1;
        }
        percentArr[0] = percentX;
        percentArr[1] = percentY;
        return percentArr;
    }
}

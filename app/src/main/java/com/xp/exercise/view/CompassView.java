package com.xp.exercise.view;

import android.animation.PropertyValuesHolder;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RadialGradient;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.xp.exercise.R;

/**
 * @类描述：指南针View
 * @创建人：Wangxiaopan
 * @创建时间：2018/3/1 0001 10:55
 * @修改人：
 * @修改时间：2018/3/1 0001 10:55
 * @修改备注：
 */

public class CompassView extends View {
    public CompassView(Context context) {
        super(context);
        init(context);
    }

    public CompassView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CompassView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public CompassView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
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
        //为指南针上面的文字预留空间，定为1/3边张
        mTextHeight = width / 3;
        //设置圆心点坐标
        mCenterX = width / 2;
        mCenterY = height / 2;
        //外部圆的外径
        mOutSideRadius = width * 3 / 8;
        //外接圆的半径
        mCircumRadius = mOutSideRadius * 4 / 5;
        //camera最大平移距离
        mMaxCameraTranslate = 0.02f * mOutSideRadius;

    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    private Canvas mCanvas;
    private Context mContext;
    //View矩形的宽度
    private int width, height;
    //指南针圆心点坐标
    private int mCenterX;
    private int mCenterY;
    //外圆半径
    private int mOutSideRadius;
    //外接圆半径
    private int mCircumRadius;
    //指南针文字大小空间高度
    private int mTextHeight;
    //暗红色 外圈笔
    private Paint mDarkRedPaint;
    //深灰 外圈笔
    private Paint mDeepGrayPaint;
    //外三角笔
    private Paint mOutSideCircumPaint;
    //浅灰 外圈笔
    private Paint mLightGrayPaint;
    //指南针上面 文字笔
    private Paint mTextPaint;
    //外接圆，三角形笔
    private Paint mCircumPaint;
    //指南针上面文字的外接矩形,用来测文字大小让文字居中
    private Rect mTextRect;
    //外圈小三角形的Path
    private Path mOutsideTriangle;
    //外接圆小三角形的Path
    private Path mCircumTriangle;

    //NESW 文字笔 和文字外接矩形
    private Paint mNorthPaint;
    private Paint mOthersPaint;
    private Rect mPositionRect;
    //小刻度文字大小矩形和画笔
    private Paint mSamllDegreePaint;
    //两位数的
    private Rect mSencondRect;
    //三位数的
    private Rect mThirdRect;
    //圆心数字矩形
    private Rect mCenterTextRect;

    //中心文字笔
    private Paint mCenterPaint;

    //内心圆是一个颜色辐射渐变的圆
    private Shader mInnerShader;
    private Paint mInnerPaint;

    //定义个点击属性动画
    private ValueAnimator mValueAnimator;
    // camera绕X轴旋转的角度
    private float mCameraRotateX;
    // camera绕Y轴旋转的角度
    private float mCameraRotateY;
    //camera最大旋转角度
    private float mMaxCameraRotate = 10;

    // camera绕X轴旋转的角度
    private float mCameraTranslateX;
    // camera绕Y轴旋转的角度
    private float mCameraTranslateY;
    //camera最大旋转角度
    private float mMaxCameraTranslate;
    //camera矩阵
    private Matrix mCameraMatrix;
    //设置camera
    private Camera mCamera;

    private float val = 0f;
    private float valCompare;
    //偏转角度红线笔
    private Paint mAnglePaint;

    //方位文字
    private String text = "北";

    public float getVal() {
        return val;
    }

    public void setVal(float val) {
        this.val = val;
        invalidate();
    }

    private void init(Context context) {
        mContext = context;

        mDarkRedPaint = new Paint();
        mDarkRedPaint.setStyle(Paint.Style.STROKE);
        mDarkRedPaint.setAntiAlias(true);
        mDarkRedPaint.setColor(context.getResources().getColor(R.color.darkRed));


        mDeepGrayPaint = new Paint();
        mDeepGrayPaint.setStyle(Paint.Style.STROKE);
        mDeepGrayPaint.setAntiAlias(true);
        mDeepGrayPaint.setColor(context.getResources().getColor(R.color.deepGray));


        mLightGrayPaint = new Paint();
        mLightGrayPaint.setStyle(Paint.Style.FILL);
        mLightGrayPaint.setAntiAlias(true);
        mLightGrayPaint.setColor(context.getResources().getColor(R.color.lightGray));

        mTextPaint = new Paint();
        mTextPaint.setStyle(Paint.Style.STROKE);
        mTextPaint.setAntiAlias(true);
        mTextPaint.setTextSize(80);
        mTextPaint.setColor(context.getResources().getColor(R.color.white));

        mCircumPaint = new Paint();
        mCircumPaint.setStyle(Paint.Style.FILL);
        mCircumPaint.setAntiAlias(true);
        mCircumPaint.setColor(context.getResources().getColor(R.color.red));

        mOutSideCircumPaint = new Paint();
        mOutSideCircumPaint.setStyle(Paint.Style.FILL);
        mOutSideCircumPaint.setAntiAlias(true);
        mOutSideCircumPaint.setColor(context.getResources().getColor(R.color.lightGray));

        mTextRect = new Rect();
        mOutsideTriangle = new Path();
        mCircumTriangle = new Path();

        mNorthPaint = new Paint();
        mNorthPaint.setStyle(Paint.Style.STROKE);
        mNorthPaint.setAntiAlias(true);
        mNorthPaint.setTextSize(40);
        mNorthPaint.setColor(context.getResources().getColor(R.color.red));

        mOthersPaint = new Paint();
        mOthersPaint.setStyle(Paint.Style.STROKE);
        mOthersPaint.setAntiAlias(true);
        mOthersPaint.setTextSize(40);
        mOthersPaint.setColor(context.getResources().getColor(R.color.white));

        mPositionRect = new Rect();
        mCenterTextRect = new Rect();

        mCenterPaint = new Paint();
        mCenterPaint.setStyle(Paint.Style.STROKE);
        mCenterPaint.setAntiAlias(true);
        mCenterPaint.setTextSize(60);
        mCenterPaint.setTextAlign(Paint.Align.CENTER);//有了这个center,X坐标不受字体宽度影响
        mCenterPaint.setColor(context.getResources().getColor(R.color.white));

        mSamllDegreePaint = new Paint();
        mSamllDegreePaint.setStyle(Paint.Style.STROKE);
        mSamllDegreePaint.setAntiAlias(true);
        mSamllDegreePaint.setTextSize(30);
        mSamllDegreePaint.setTextAlign(Paint.Align.CENTER);
        mSamllDegreePaint.setColor(context.getResources().getColor(R.color.lightGray));

        mSencondRect = new Rect();
        mThirdRect = new Rect();

        mInnerPaint = new Paint();
        mInnerPaint.setStyle(Paint.Style.FILL);
        mInnerPaint.setAntiAlias(true);

        mAnglePaint = new Paint();
        mAnglePaint.setStyle(Paint.Style.STROKE);
        mAnglePaint.setAntiAlias(true);
        mAnglePaint.setColor(context.getResources().getColor(R.color.red));

        mCameraMatrix = new Matrix();
        mCamera = new Camera();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mCanvas = canvas;
        canvas.drawColor(Color.BLACK);
        set3DMatrix();
        drawText();
        drawCompassOutSide();
        drawCompassCircum();
        drawInnerCircle();
        drawCompassDegreeScale();
        drawCenterText();
    }

    //绘制方向文字
    private void drawText() {
        if (val <= 15 || val >= 345) {
            text = "北";
        } else if (val > 15 && val <= 75) {
            text = "东北";
        } else if (val > 75 && val <= 105) {
            text = "东";
        } else if (val > 105 && val <= 165) {
            text = "东南";
        } else if (val > 165 && val <= 195) {
            text = "南";
        } else if (val > 195 && val <= 255) {
            text = "西南";
        } else if (val > 255 && val <= 285) {
            text = "西";
        } else if (val > 285 && val < 345) {
            text = "西北";
        }
        mTextPaint.getTextBounds(text, 0, text.length(), mTextRect);
        int textWidth = mTextRect.width();
        mCanvas.drawText(text, width / 2 - textWidth / 2, mTextHeight / 2, mTextPaint);
    }

    //画指南针外层圆弧和三角
    //1，三角 2，圆弧
    private void drawCompassOutSide() {
        mCanvas.save();
        //小三角高度,边长
        int mTriangleHeight = 40;
        float mTriangleSide = 46.18f;
        //定义path画小三角
        mOutsideTriangle.moveTo(mCenterX, mCenterY - mOutSideRadius - mTriangleHeight);
        mOutsideTriangle.lineTo(mCenterX - mTriangleSide / 2, mCenterY - mOutSideRadius);
        mOutsideTriangle.lineTo(mCenterX + mTriangleSide / 2, mCenterY - mOutSideRadius);
        mOutsideTriangle.close();
        mCanvas.drawPath(mOutsideTriangle, mOutSideCircumPaint);
        //画圆弧
        mDarkRedPaint.setStrokeWidth(5f);
        mLightGrayPaint.setStrokeWidth(5f);
        mDeepGrayPaint.setStrokeWidth(3f);
        mLightGrayPaint.setStyle(Paint.Style.STROKE);
        RectF rectF = new RectF(mCenterX - mOutSideRadius, mCenterY - mOutSideRadius, mCenterX + mOutSideRadius, mCenterY + mOutSideRadius);
        mCanvas.drawArc(rectF, 280, 120, false, mLightGrayPaint);
        mCanvas.drawArc(rectF, 40, 20, false, mDeepGrayPaint);
        mCanvas.drawArc(rectF, 120, 120, false, mDarkRedPaint);
        mCanvas.drawArc(rectF, 240, 20, false, mLightGrayPaint);
        mCanvas.restore();
    }

    //内层圆弧和三角
    private void drawCompassCircum() {
        mCanvas.save();
        int mTriangleHeight = (mOutSideRadius - mCircumRadius) / 2;

        mCanvas.rotate(-val, mCenterX, mCenterY);
        mCircumTriangle.moveTo(mCenterX, mCenterY - mCircumRadius - mTriangleHeight);
        //内接三角形的边长,简单数学运算
        float mTriangleSide = (float) ((mTriangleHeight / (Math.sqrt(3))) * 2);
        mCircumTriangle.lineTo(mCenterX - mTriangleSide / 2, mCenterY - mCircumRadius);
        mCircumTriangle.lineTo(mCenterX + mTriangleSide / 2, mCenterY - mCircumRadius);
        mCircumTriangle.close();
        mCanvas.drawPath(mCircumTriangle, mCircumPaint);
        RectF rectF = new RectF(mCenterX - mCircumRadius, mCenterY - mCircumRadius, mCenterX + mCircumRadius, mCenterY + mCircumRadius);
        mCanvas.drawArc(rectF, 275, 350, false, mLightGrayPaint);
        //这里需要画左右圆弧根据方向确定
        if (val <= 180) {
            valCompare = val;
            mCanvas.drawArc(rectF, 265, valCompare, false, mAnglePaint);
        } else {
            valCompare = 360 - val;
            mCanvas.drawArc(rectF, 275, -valCompare, false, mAnglePaint);
        }
        mCanvas.restore();
    }

    //画内芯 颜色辐射渐变的圆
    private void drawInnerCircle() {
        int[] colors = {Color.GRAY, Color.BLACK};
        float[] positions = {0f, 0.75f};
        mInnerShader = new RadialGradient(mCenterX, mCenterY, mCircumRadius - 40, colors, positions, Shader.TileMode.CLAMP);
        mInnerPaint.setShader(mInnerShader);
        mCanvas.drawCircle(mCenterX, mCenterY, mCircumRadius - 40, mInnerPaint);
    }

    //画指南针 刻度表 和 圆心数字
    private void drawCompassDegreeScale() {
        mCanvas.save();
        mNorthPaint.getTextBounds("N", 0, 1, mPositionRect);
        int mPositionTextWidth = mPositionRect.width();
        int mPositionTextHeight = mPositionRect.height();
        //获取W文字宽度,因为W比较宽 所以要单独获取
        mNorthPaint.getTextBounds("W", 0, 1, mPositionRect);
        int mWPositionTextWidth = mPositionRect.width();
        int mWPositionTextHeight = mPositionRect.height();
        //获取小刻度，两位数的宽度
        mSamllDegreePaint.getTextBounds("30", 0, 1, mSencondRect);
        int mSencondTextWidth = mSencondRect.width();
        int mSencondTextHeight = mSencondRect.height();
        //获取小刻度，3位数的宽度
        mSamllDegreePaint.getTextBounds("120", 0, 1, mThirdRect);
        int mThirdTextWidth = mThirdRect.width();
        int mThirdTextHeight = mThirdRect.height();
        mCanvas.rotate(-val, mCenterX, mCenterY);
        //画刻度线
        for (int i = 0; i < 240; i++) {
            if (i == 0 || i == 60 || i == 120 || i == 180) {//0,90,180,270三个正方向
                mCanvas.drawLine(mCenterX, mCenterY - mCircumRadius + 10, mCenterX, mCenterY - mCircumRadius + 30, mDeepGrayPaint);
            } else {
                mCanvas.drawLine(mCenterX, mCenterY - mCircumRadius + 10, mCenterX, mCenterY - mCircumRadius + 30, mLightGrayPaint);
            }
            if (i == 0) {
                mCanvas.drawText("N", mCenterX - mPositionTextWidth / 2, mCenterY - mCircumRadius + 40 + mPositionTextHeight, mNorthPaint);
            } else if (i == 60) {
                mCanvas.drawText("E", mCenterX - mPositionTextWidth / 2, mCenterY - mCircumRadius + 40 + mPositionTextHeight, mOthersPaint);
            } else if (i == 120) {
                mCanvas.drawText("S", mCenterX - mPositionTextWidth / 2, mCenterY - mCircumRadius + 40 + mPositionTextHeight, mOthersPaint);
            } else if (i == 180) {
                mCanvas.drawText("W", mCenterX - mWPositionTextWidth / 2, mCenterY - mCircumRadius + 40 + mWPositionTextHeight, mOthersPaint);
            } else if (i == 20) {
                mCanvas.drawText("30", mCenterX - mSencondTextWidth / 2, mCenterY - mCircumRadius + 40 + mSencondTextHeight, mSamllDegreePaint);
            } else if (i == 40) {
                mCanvas.drawText("60", mCenterX - mSencondTextWidth / 2, mCenterY - mCircumRadius + 40 + mSencondTextHeight, mSamllDegreePaint);
            } else if (i == 80) {
                mCanvas.drawText("120", mCenterX - mThirdTextWidth / 2, mCenterY - mCircumRadius + 40 + mThirdTextHeight, mSamllDegreePaint);
            } else if (i == 100) {
                mCanvas.drawText("150", mCenterX - mThirdTextWidth / 2, mCenterY - mCircumRadius + 40 + mThirdTextHeight, mSamllDegreePaint);
            } else if (i == 140) {
                mCanvas.drawText("210", mCenterX - mThirdTextWidth / 2, mCenterY - mCircumRadius + 40 + mThirdTextHeight, mSamllDegreePaint);
            } else if (i == 160) {
                mCanvas.drawText("240", mCenterX - mThirdTextWidth / 2, mCenterY - mCircumRadius + 40 + mThirdTextHeight, mSamllDegreePaint);
            } else if (i == 200) {
                mCanvas.drawText("300", mCenterX - mThirdTextWidth / 2, mCenterY - mCircumRadius + 40 + mThirdTextHeight, mSamllDegreePaint);
            } else if (i == 220) {
                mCanvas.drawText("330", mCenterX - mThirdTextWidth / 2, mCenterY - mCircumRadius + 40 + mThirdTextHeight, mSamllDegreePaint);
            }
            //1.5 * 240 = 360
            mCanvas.rotate(1.5f, mCenterX, mCenterY);
        }
        mCanvas.restore();
    }

    //画中心数字
    private void drawCenterText() {
        String centerText = String.valueOf((int) val + "°");
        mCenterPaint.getTextBounds(centerText, 0, centerText.length(), mCenterTextRect);
        int textHeight = mCenterTextRect.height();
        mCanvas.drawText(centerText, mCenterX, mCenterY + textHeight / 2, mCenterPaint);
    }

    //当手指触摸View 通过得到的触摸点，然后通过定义的比例值，得到Camera的旋转大小和平移大小
    //Camera默认旋转中心在View左上角，我们通过Matrix来改变旋转中心
    private void set3DMatrix() {
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
        mCanvas.concat(mCameraMatrix);
    }

    /**
     * 获取Camera，平移大小
     *
     * @param event
     */
    private void getCameraTranslate(MotionEvent event) {
        float translateX = (event.getX() - getWidth() / 2);
        float translateY = (event.getY() - getHeight() / 2);
        //求出此时位移的大小与半径之比
        float[] percentArr = getPercent(translateX, translateY);
        //最终位移的大小按比例匀称改变
        mCameraTranslateX = percentArr[0] * mMaxCameraTranslate;
        mCameraTranslateY = percentArr[1] * mMaxCameraTranslate;
    }

    /**
     * 让Camera旋转,获取旋转偏移大小
     *
     * @param event
     */
    private void getCameraRotate(MotionEvent event) {
        float mRotateX = -(event.getY() - (getHeight()) / 2);
        float mRotateY = (event.getX() - getWidth() / 2);
        //求出旋转大小与半径之比
        float[] percentArr = getPercent(mRotateX, mRotateY);
        mCameraRotateX = percentArr[0] * mMaxCameraRotate;
        mCameraRotateY = percentArr[1] * mMaxCameraRotate;
    }

    /**
     * 获取比例
     *
     * @param mCameraRotateX
     * @param mCameraRotateY
     * @return
     */
    private float[] getPercent(float mCameraRotateX, float mCameraRotateY) {
        float[] percentArr = new float[2];
        float percentX = mCameraRotateX / width;
        float percentY = mCameraRotateY / height;
        //处理一下比例值
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

    private void startRestore() {
        final String cameraRotateXName = "cameraRotateX";
        final String cameraRotateYName = "cameraRotateY";
        final String canvasTranslateXName = "canvasTranslateX";
        final String canvasTranslateYName = "canvasTranslateY";
        PropertyValuesHolder cameraRotateXHolder =
                PropertyValuesHolder.ofFloat(cameraRotateXName, mCameraRotateX, 0);
        PropertyValuesHolder cameraRotateYHolder =
                PropertyValuesHolder.ofFloat(cameraRotateYName, mCameraRotateY, 0);
        PropertyValuesHolder canvasTranslateXHolder =
                PropertyValuesHolder.ofFloat(canvasTranslateXName, mCameraTranslateX, 0);
        PropertyValuesHolder canvasTranslateYHolder =
                PropertyValuesHolder.ofFloat(canvasTranslateYName, mCameraTranslateY, 0);
        mValueAnimator = ValueAnimator.ofPropertyValuesHolder(cameraRotateXHolder,
                cameraRotateYHolder, canvasTranslateXHolder, canvasTranslateYHolder);
        mValueAnimator.setInterpolator(new TimeInterpolator() {
            @Override
            public float getInterpolation(float input) {
                float f = 0.571429f;
                return (float) (Math.pow(2, -2 * input) * Math.sin((input - f / 4) * (2 * Math.PI) / f) + 1);
            }
        });
        mValueAnimator.setDuration(1000);
        mValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mCameraRotateX = (float) animation.getAnimatedValue(cameraRotateXName);
                mCameraRotateY = (float) animation.getAnimatedValue(cameraRotateYName);
                mCameraTranslateX = (float) animation.getAnimatedValue(canvasTranslateXName);
                mCameraTranslateX = (float) animation.getAnimatedValue(canvasTranslateYName);
            }
        });
        mValueAnimator.start();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (mValueAnimator != null && mValueAnimator.isRunning()) {
                    mValueAnimator.cancel();
                }
                //3D 效果让Camera旋转,获取旋转偏移大小
                getCameraRotate(event);
                //获取平移大小
                getCameraTranslate(event);
                break;
            case MotionEvent.ACTION_MOVE:
                //3D 效果让Camera旋转,获取旋转偏移大小
                getCameraRotate(event);
                //获取平移大小
                getCameraTranslate(event);
                break;
            case MotionEvent.ACTION_UP:
                //松开手 复原动画
                startRestore();
                break;
        }
        return true;

    }
}

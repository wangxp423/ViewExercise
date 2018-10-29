package com.xp.exercise.canvas.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.PointFEvaluator;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.LinearInterpolator;

import com.xp.exercise.R;
import com.xp.exercise.util.LogUtils;
import com.xp.exercise.util.MathUtil;

/**
 * @类描述：贝塞尔 圆形指示器
 * @创建人：Wangxiaopan
 * @创建时间：2018/10/10 0010 17:43
 * @修改人：
 * @修改时间：2018/10/10 0010 17:43
 * @修改备注：
 */
@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class BezierRoundIndicatorView extends View {
    public BezierRoundIndicatorView(Context context) {
        super(context);
        init();
    }

    public BezierRoundIndicatorView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BezierRoundIndicatorView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private int[] dismissDrawables = {R.drawable.explode1, R.drawable.explode2, R.drawable.explode3, R.drawable.explode4, R.drawable.explode5};
    private Bitmap[] dismissBitmaps;
    private int mState;//当前红点的状态
    private static final int STATE_INIT = 0;//默认静止状态
    private static final int STATE_DRAG = 1;//拖拽状态
    private static final int STATE_MOVE = 2;//移动状态
    private static final int STATE_DISMISS = 3;//消失状态
    private float dragDistance;
    private float maxDistance = 300;
    private int defaultRadius = 30;//默认半径
    private int stickRadius = defaultRadius; //固定圆半径
    private int dragRadius = 50;//拖拽圆半径
    private PointF dragPointF, stickPointF, controlPointF;//拖拽圆和固定圆
    private Paint mPaint;
    private Path mPath;

    private void init() {
        //初始化path
        mPath = new Path();
        //初始化画笔
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setColor(Color.RED);
        initPointF();
        initDismissBitmaps();

    }

    //手指是否点击在圆圈上
    private boolean isOnTouch(float x, float y) {
        if (x >= dragPointF.x - dragRadius && x <= dragPointF.x + dragRadius && y >= dragPointF.y - dragRadius && y <= dragPointF.y + dragRadius) {
            return true;
        }
        return false;
    }

    float touchX, touchY;

    @Override
    public boolean onTouchEvent(MotionEvent event) { //我们必须知道move事件的处理流程，只有当在down的消费层面，才有move，不然中间层是没有move的。
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                LogUtils.d("Test", "MotionEvent.ACTION_DOWN");
                touchX = event.getX();
                touchY = event.getY();
                LogUtils.d("Test", "x = " + touchX + "  y = " + touchY);
                if (isOnTouch(touchX, touchY)) {
                    return true;
                }
                return false;

            case MotionEvent.ACTION_MOVE:
                LogUtils.d("Test", "MotionEvent.ACTION_MOVE");
                touchX = event.getX();
                touchY = event.getY();
                setDragRoundLocation(touchX, touchY);
                break;

            case MotionEvent.ACTION_UP:
                LogUtils.d("Test", "MotionEvent.ACTION_UP");
                setDragUp();
                break;
        }
        return super.onTouchEvent(event);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        LogUtils.d("Test", "onDraw");
        if (mState == STATE_INIT || mState == STATE_DRAG) {
            //绘制固定小圆,和固定大圆
            canvas.drawCircle(stickPointF.x, stickPointF.y, stickRadius, mPaint);
            canvas.drawCircle(dragPointF.x, dragPointF.y, dragRadius, mPaint);
        }
        if (inRange() && mState == STATE_DRAG) {
            //首先获得两圆心之间的斜率
            Float link = MathUtil.getLineSlope(dragPointF, stickPointF);
            //根据斜率和已经的圆心和半径获取外切线的切点坐标
            PointF[] stickPoints = MathUtil.getIntersectionPoints(stickPointF, stickRadius, link);
            PointF[] dragPoints = MathUtil.getIntersectionPoints(dragPointF, dragRadius, link);
            //二阶贝塞尔曲线的控制点，取两个圆心的中心点
            controlPointF = MathUtil.getMiddlePoint(dragPointF, stickPointF);
            mPath.reset();
            mPath.moveTo(stickPoints[0].x, stickPoints[0].y);
            mPath.quadTo(controlPointF.x, controlPointF.y, dragPoints[0].x, dragPoints[0].y);
            mPath.lineTo(dragPoints[1].x, dragPoints[1].y);
            mPath.quadTo(controlPointF.x, controlPointF.y, stickPoints[1].x, stickPoints[1].y);
            mPath.lineTo(stickPoints[0].x, stickPoints[0].y);
            canvas.drawPath(mPath, mPaint);
        }
        if (mState == STATE_MOVE) {
            canvas.drawCircle(dragPointF.x, dragPointF.y, dragRadius, mPaint);
        }
        if (mState == STATE_DISMISS) {
            canvas.drawBitmap(dismissBitmaps[dismissDrawableIndex], dragPointF.x - dragRadius / 2, dragPointF.y - dragRadius / 2, mPaint);
        }
    }

    //初始化两个圆心点的坐标
    private void initPointF() {
        mState = STATE_INIT;
        //初始化点
        if (null == stickPointF) {
            stickPointF = new PointF();
        }
        if (null == dragPointF) {
            dragPointF = new PointF();
        }
        stickPointF.set(400, 400);
        dragPointF.set(400, 400);
    }

    private void initDismissBitmaps() {
        dismissBitmaps = new Bitmap[dismissDrawables.length];
        for (int i = 0; i < dismissDrawables.length; i++) {
            dismissBitmaps[i] = BitmapFactory.decodeResource(getResources(), dismissDrawables[i]);
        }
    }

    private float getDragDistance(float x, float y) {
        PointF pointF = new PointF();
        pointF.set(x, y);
        dragDistance = MathUtil.getTwoPointDistance(pointF, stickPointF);
        return dragDistance;
    }

    //是否在最大拖拽范围内
    private boolean inRange() {
        if (dragDistance > maxDistance) {
            return false;
        }
        return true;
    }

    //设置红点拖拽过程中的状态变化
    private void setDragRoundLocation(float x, float y) {
        getDragDistance(touchX, touchY);
        dragPointF.set(touchX, touchY);
        if (inRange()) {
            mState = STATE_DRAG;
            stickRadius = (int) (defaultRadius - dragDistance / 10) < 10 ? 10 : (int) (defaultRadius - dragDistance / 10);
        } else {
            mState = STATE_MOVE;
        }
        invalidate();
    }

    private void setDragUp() {
        if (inRange() && mState == STATE_DRAG) {
            startResetAnimator();
        } else if (mState == STATE_MOVE) {
            if (inRange()) {
                startResetAnimator();
            } else {
                mState = STATE_DISMISS;
                startDismissAnim();
            }
        }
    }

    //松手回弹动画
    private void startResetAnimator() {
        ValueAnimator animator = ValueAnimator.ofObject(new PointFEvaluator(), new PointF(dragPointF.x, dragPointF.y), new PointF(stickPointF.x, stickPointF.y));
        animator.setInterpolator(new LinearInterpolator());
        animator.setDuration(500);
        //回弹时间的插值器
        animator.setInterpolator(new TimeInterpolator() {
            @Override
            public float getInterpolation(float input) {
                float f = 0.571429f;
                return (float) (Math.pow(2, -4 * input) * Math.sin((input - f / 4) * (2 * Math.PI) / f) + 1);
            }
        });
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                PointF curPoint = (PointF) animation.getAnimatedValue();
                dragPointF.set(curPoint.x, curPoint.y);
                invalidate();
            }
        });
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {

            }
        });
        animator.start();
    }

    private int dismissDrawableIndex;

    //松手爆炸动画
    private void startDismissAnim() {
        ValueAnimator animator = ValueAnimator.ofInt(0, dismissDrawables.length - 1);
        animator.setDuration(300);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                dismissDrawableIndex = (int) animation.getAnimatedValue();
                invalidate();
            }
        });
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                initPointF();
                invalidate();
            }
        });
        animator.start();
    }
}

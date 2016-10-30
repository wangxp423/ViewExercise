package com.xp.exercise.radar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import com.xp.exercise.R;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.Scroller;

public class CircleRippleView extends ImageView implements Runnable {

    /********************用Scroller去实现太卡了**************************/
    private int mCircleWidth, mMinWidth, mMaxWidth;
    private int mCenterX, mCenterY;
    private int mNormalCircleR;
    private int index = 0;
    private SparseArray<ScrollerModel> mScrollArray = new SparseArray<ScrollerModel>();

    /****************** 用线程去实现 **************************/
    private Paint mCirclePaint; // 圆圈Paint
    private float floatRadius; // 变化的半径
    private int circleInterval = 60; // 圆环间隔
    private volatile boolean started = false;
    private ArrayList<Integer> mCircleList = new ArrayList<Integer>();
    private ConcurrentHashMap<Integer, Integer> mHashMap = new ConcurrentHashMap<Integer, Integer>();

    public CircleRippleView(Context context) {
        super(context);
        initPaint(context);
    }

    public CircleRippleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initPaint(context);
    }

    public CircleRippleView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initPaint(context);
    }

    private void initPaint(Context context) {
        mMinWidth = getResources().getDimensionPixelSize(R.dimen.radar_head_image_width) / 2;
        int width = getResources().getDimensionPixelSize(R.dimen.radar_circle_width);
        mCircleWidth = width;

        mCirclePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mCirclePaint.setStyle(Paint.Style.STROKE);
        mCirclePaint.setStrokeWidth(mCircleWidth);
        mCirclePaint.setColor(getResources().getColor(R.color.radar_circle_color));

    }

    public void startRipple() {
        // startScroller();
        // post(this);
        // invalidate();
        
        new Thread(this).start();
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        Log.d("Test", "onLayout.changed = " + changed);
        if (changed) {
            mMaxWidth = (right - left) / 2;
            mCenterX = left + (right - left) / 2;
            mCenterY = top + (bottom - top) / 2;
            mNormalCircleR = mMinWidth;
            floatRadius = (mMaxWidth % circleInterval);
            Log.d("Test", "onLayout.mMaxWidth = " + mMaxWidth);
            Log.d("Test", "onLayout.mCenterX = " + mCenterX);
            Log.d("Test", "onLayout.mCenterY = " + mCenterY);
            Log.d("Test", "onLayout.mCurCircleR = " + mNormalCircleR);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // onScrollerDraw(canvas);
        for (Entry<Integer, Integer> entry : mHashMap.entrySet()) {
            int width = entry.getValue();
            float percent = (float)width / 306;
            float circleWidth = (1f - percent) * mCircleWidth;
            mCirclePaint.setStrokeWidth(circleWidth);
            int alpha = (int) (255 * (1f - percent));
            mCirclePaint.setAlpha(alpha);
            canvas.drawCircle(mCenterX, mCenterY, width, mCirclePaint);
        }
    }
    

    @Override
    public void run() {
        // onComputScroller();

        Thread thread = Thread.currentThread();
        int id = (int) thread.getId();
        int minWidth = mMinWidth - 30;
        int maxWidth = mMaxWidth;
        Log.d("Test", "Thread id = " + thread.getId());
        while (true) {
            minWidth = minWidth + 6;
            mHashMap.put(id, minWidth);
            if (minWidth >= maxWidth) {
                mHashMap.remove(id);
//                thread.destroy();
            }
            postInvalidate();
            try {
                Thread.sleep(50L);
            } catch (InterruptedException localInterruptedException) {
                localInterruptedException.printStackTrace();
            }
        }

    }

    /*****************************
     * 以下是用Scroller去实现 不太好效果  超过三个圈 会卡
     *****************************************************/
    /**
     * 开启一个 Scroller 去控制不停地移动
     */
    private void startScroller() {
        Paint mCirclePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mCirclePaint.setStyle(Paint.Style.STROKE);
        mCirclePaint.setStrokeWidth(mCircleWidth);
        mCirclePaint.setColor(getResources().getColor(R.color.radar_circle_color));

        Scroller mScroller = new Scroller(getContext(), new LinearInterpolator());
        mScroller.startScroll(0, 0, mMaxWidth - mMinWidth, 0, 2000);

        ScrollerModel model = new ScrollerModel();
        model.setScroller(mScroller);
        model.setPaint(mCirclePaint);
        model.setCurR(mNormalCircleR);
        model.setCurX(0);
        mScrollArray.put(index, model);
        index++;
    }

    /**
     * scroller 执行过程
     */
    private void onComputScroller() {
        for (int i = 0; i < index; i++) {
            ScrollerModel model = mScrollArray.get(i);
            if (null == model)
                continue;
            Scroller curScroller = model.getScroller();
            boolean circleMore = curScroller.computeScrollOffset();
            if (circleMore) {
                setCurModelData(model);
                post(this);
            } else {
                mScrollArray.remove(i);
            }
        }
    }

    private void setCurModelData(ScrollerModel model) {
        Scroller curScroll = model.getScroller();
        Log.d("Test", "setCurModelData.getCurrx = " + curScroll.getCurrX());
        Paint paint = model.getPaint();
        int moveX = mMaxWidth - mMinWidth;
        if (0 == model.getCurX())
            model.setCurX(curScroll.getCurrX());
        int currX = curScroll.getCurrX() - model.getCurX();
        model.setCurX(curScroll.getCurrX());
        float percent = (model.getCurX() / (float) moveX);
        float curWidth = (1f - percent) * mCircleWidth;
        paint.setStrokeWidth(curWidth);
        if (moveX == model.getCurX()) {
            paint.setAlpha(0);
        }
        int curR = model.getCurR();
        model.setCurR(curR + currX);
        invalidate();
    }

    private void onScrollerDraw(Canvas canvas) {
        for (int i = 0; i < index; i++) {
            ScrollerModel model = mScrollArray.get(i);
            if (null == model)
                continue;
            // Log.d("Test", "onDraw.model = " + model + " curR = " +
            // model.getCurR());
            canvas.drawCircle(mCenterX, mCenterY, model.getCurR(), model.getPaint());
        }
    }

    public class ScrollerModel {
        Scroller scroller;
        Paint paint;
        int curX; // 当前移动距离
        int curR; // 当前半径

        public Scroller getScroller() {
            return scroller;
        }

        public void setScroller(Scroller scroller) {
            this.scroller = scroller;
        }

        public Paint getPaint() {
            return paint;
        }

        public void setPaint(Paint paint) {
            this.paint = paint;
        }

        public int getCurX() {
            return curX;
        }

        public void setCurX(int curX) {
            this.curX = curX;
        }

        public int getCurR() {
            return curR;
        }

        public void setCurR(int curR) {
            this.curR = curR;
        }
    }
    /*****************************
     * 以上是用Scroller去实现 不太好效果
     *****************************************************/
}

package com.xp.exercise.viewdraghelper.lesson2;

import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

import com.xp.exercise.R;

/**
 * Created by jacob-wj on 2015/4/14.
 */
public class DragLayoutTwo extends LinearLayout {
    private View mDragView;
    private ViewDragHelper mViewDragHelper;

    //给出3个位置等级
    public static int TOP_SNAPPING_POINT = 0;
    public static int MIDDLE_SNAPPING_POINT = 0;
    public static int BOTTOM_SNAPPING_POINT = 0;

    public DragLayoutTwo(Context context) {
        this(context, null);
    }

    public DragLayoutTwo(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DragLayoutTwo(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mDragView = findViewById(R.id.dragview);
        mViewDragHelper = ViewDragHelper.create(this, 1f, new DragLayoutCallBack());
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        TOP_SNAPPING_POINT = 0;
        MIDDLE_SNAPPING_POINT = getMeasuredHeight() / 2 - mDragView.getMeasuredHeight() / 2;
        BOTTOM_SNAPPING_POINT = getMeasuredHeight() - mDragView.getMeasuredHeight();

    }

    private class DragLayoutCallBack extends ViewDragHelper.Callback {

        @Override
        public boolean tryCaptureView(View child, int pointerId) {
            return child == mDragView;
        }

        @Override
        public int clampViewPositionVertical(View child, int top, int dy) {
            if (top < getPaddingTop()) {
                return getPaddingTop();
            }

            if (top > getHeight() - child.getMeasuredHeight()) {
                return getHeight() - child.getMeasuredHeight();
            }
            return top;
        }

        @Override
        public int getViewVerticalDragRange(View child) {
            return getMeasuredHeight() - child.getMeasuredHeight();
        }

        @Override
        public void onViewReleased(View releasedChild, float xvel, float yvel) {
            Log.e("TAG", "onViewReleased");
            snapToPoint(releasedChild.getTop());
        }
    }

    private void snapToPoint(int paddingTop) {
        if (paddingTop <= MIDDLE_SNAPPING_POINT) {
            if ((MIDDLE_SNAPPING_POINT - paddingTop) > paddingTop) {
                snapTop();
            } else {
                snapMiddle();
            }
        } else {
            if ((paddingTop - MIDDLE_SNAPPING_POINT) > (BOTTOM_SNAPPING_POINT - paddingTop)) {
                snapBottom();
            } else {
                snapMiddle();
            }
        }
        invalidate();
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if (mViewDragHelper.continueSettling(true)) {
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mViewDragHelper.processTouchEvent(event);
        return true;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
                mViewDragHelper.cancel();
                return false;
        }
        return mViewDragHelper.shouldInterceptTouchEvent(ev);
    }

    private void snapTop() {
        Log.e("TAG", "snapTop");
        mViewDragHelper.settleCapturedViewAt(0, TOP_SNAPPING_POINT);
    }

    private void snapMiddle() {
        Log.e("TAG", "snapMiddle");
        mViewDragHelper.settleCapturedViewAt(0, MIDDLE_SNAPPING_POINT);
    }

    private void snapBottom() {
        Log.e("TAG", "snapBottom");
        mViewDragHelper.settleCapturedViewAt(0, BOTTOM_SNAPPING_POINT);
    }

}

package com.xp.exercise.viewdraghelper.lesson5;

import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

import com.xp.exercise.R;


/**
 * Package : com.jacob.viewdraghelper.lesson5
 * Author : jacob
 * Date : 15-4-15
 * Description : 这个类是用来xxx
 */
public class DragLayoutFive extends RelativeLayout {
    private static final String TAG = "DragLayoutFive";
    private final double AUTO_OPEN_SPEED_LIMIT = 800;
    private int mDragRange;
    private int mDragBorder;
    private ViewDragHelper mViewDragHelper;
    private boolean isOpen;

    public DragLayoutFive(Context context) {
        this(context, null);
    }

    public DragLayoutFive(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DragLayoutFive(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        isOpen = false;
    }


    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mViewDragHelper = ViewDragHelper.create(this, 1f, new ViewDragCallBack());
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        mDragRange = (int) (r * 0.25f);
    }

    private class ViewDragCallBack extends ViewDragHelper.Callback {


        @Override
        public boolean tryCaptureView(View view, int i) {
            return R.id.linear_main == view.getId();
        }

        @Override
        public void onViewReleased(View releasedChild, float xvel, float yvel) {
//            super.onViewReleased(releasedChild, xvel, yvel);
            Log.e(TAG, "xvel:" + xvel + "++yvel:" + yvel);
            float checkRange = mDragRange;

            if (mDragBorder == 0) {
                isOpen = false;
                return;
            }

            if (mDragBorder == -checkRange) {
                isOpen = true;
                return;
            }

            boolean settleToOpen = false;
            if (xvel > AUTO_OPEN_SPEED_LIMIT) {
                settleToOpen = false;
            } else if (xvel < -AUTO_OPEN_SPEED_LIMIT) {
                settleToOpen = true;
            } else if (mDragBorder > -checkRange / 2) {
                settleToOpen = false;
            } else if (mDragBorder < -checkRange / 2) {
                settleToOpen = true;
            }

            int settleDestX = settleToOpen ? -mDragRange : 0;

            if (mViewDragHelper.settleCapturedViewAt(settleDestX, 0)) {
                ViewCompat.postInvalidateOnAnimation(DragLayoutFive.this);
            }


        }

        @Override
        public int clampViewPositionHorizontal(View child, int left, int dx) {
//            return super.clampViewPositionHorizontal(child, left, dx);
            if (left >= 0) {
                left = 0;
            }

            if (left <= -mDragRange) {
                left = -mDragRange;
            }
            return left;
        }

        @Override
        public int getViewHorizontalDragRange(View child) {
            return mDragRange;
        }

        @Override
        public void onViewPositionChanged(View changedView, int left, int top, int dx, int dy) {
            mDragBorder = left;
        }
    }

    @Override
    public void computeScroll() {
        if (mViewDragHelper.continueSettling(true)) {
            ViewCompat.postInvalidateOnAnimation(this);
        }
        super.computeScroll();
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                mViewDragHelper.cancel();
                break;
        }
        return mViewDragHelper.shouldInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mViewDragHelper.processTouchEvent(event);
        return super.onTouchEvent(event);
    }
}

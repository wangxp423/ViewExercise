package com.xp.exercise.viewdraghelper.lesson4;

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
 * Created by jacob-wj on 2015/4/15.
 */
public class DragLayoutFour extends RelativeLayout {
    private static final String TAG = "draglayoutfour";
    private final double AUTO_OPEN_SPEED_LIMIT = 800;
    private ViewDragHelper mViewDragHelper;
    private int mVerticalRange;
    private boolean isOpen;
    private int mDragState;
    private int mDragBorder;


    public DragLayoutFour(Context context) {
        this(context, null);
    }

    public DragLayoutFour(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DragLayoutFour(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        isOpen = false;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mViewDragHelper = ViewDragHelper.create(this, 1f, new ViewDragCallBack());
        isOpen = false;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mVerticalRange = (int) (h * 0.5f);
        Log.e(TAG, "onSizeChanged" + mVerticalRange);
    }

    @Override
    public void computeScroll() {
        if (mViewDragHelper.continueSettling(true)) {
            ViewCompat.postInvalidateOnAnimation(this);
        }
        super.computeScroll();
    }

    private class ViewDragCallBack extends ViewDragHelper.Callback {

        @Override
        public boolean tryCaptureView(View child, int pointerId) {
            return R.id.linear_main == child.getId();
        }

        @Override
        public void onViewDragStateChanged(int state) {
            if (state == mDragState) {
                return;
            }

            if ((state == ViewDragHelper.STATE_IDLE)) {
                if (mDragBorder == mVerticalRange) {
                    isOpen = true;
                }
            }

            mDragState = state;
            super.onViewDragStateChanged(state);
        }

        @Override
        public void onViewPositionChanged(View changedView, int left, int top, int dx, int dy) {
            mDragBorder = top;
        }

        @Override
        public int getViewVerticalDragRange(View child) {
            return mVerticalRange;
        }

        @Override
        public int clampViewPositionVertical(View child, int top, int dy) {
            int topBound = getPaddingTop();
            int bottomBound = mVerticalRange;
            return Math.min(Math.max(top, topBound), bottomBound);
        }

        @Override
        public void onViewReleased(View releasedChild, float xvel, float yvel) {
//            super.onViewReleased(releasedChild, xvel, yvel);
            Log.e(TAG, "xvel:" + xvel + "++yvel:" + yvel);
            float rangeToCheck = mVerticalRange;
            if (mDragBorder == 0) {
                isOpen = false;
                return;
            }

            if (mDragBorder == rangeToCheck) {
                isOpen = true;
                return;
            }

            boolean settleToOpen = false;
            if (yvel > AUTO_OPEN_SPEED_LIMIT) {
                settleToOpen = true;
            } else if (yvel < -AUTO_OPEN_SPEED_LIMIT) {
                settleToOpen = false;
            } else if (mDragBorder > rangeToCheck / 2) {
                settleToOpen = true;
            } else if (mDragBorder < rangeToCheck / 2) {
                settleToOpen = true;
            }
            int settleDestY = settleToOpen ? mVerticalRange : 0;

            if (mViewDragHelper.settleCapturedViewAt(0, settleDestY)) {
                ViewCompat.postInvalidateOnAnimation(DragLayoutFour.this);
            }


        }
    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return mViewDragHelper.shouldInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mViewDragHelper.processTouchEvent(event);
        return super.onTouchEvent(event);
    }
}

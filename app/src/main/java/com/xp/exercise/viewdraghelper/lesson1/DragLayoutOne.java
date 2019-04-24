package com.xp.exercise.viewdraghelper.lesson1;

import android.content.Context;
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
public class DragLayoutOne extends LinearLayout {
    private static final String TAG = "DragLayoutOne";
    private ViewDragHelper mViewDragHelper;
    private View mDragView;

    public DragLayoutOne(Context context) {
        this(context, null);
    }

    public DragLayoutOne(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DragLayoutOne(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mViewDragHelper = ViewDragHelper.create(this, 1f, new ViewDragCallBack());
    }

    private class ViewDragCallBack extends ViewDragHelper.Callback {

        @Override
        public boolean tryCaptureView(View child, int pointerId) {
            return mDragView.getId() == child.getId();
        }

        /**
         * 处理水平方向上的拖动
         *
         * @param child 拖动的View
         * @param left  移动到x轴的距离
         * @param dx    建议的移动的x距离
         */
        @Override
        public int clampViewPositionHorizontal(View child, int left, int dx) {
            Log.e(TAG, "left:" + left + "++++dx:" + dx);
            //两个if主要是让view在ViewGroup中
            if (left < getPaddingLeft()) {
                return getPaddingLeft();
            }

            if (left > getWidth() - child.getMeasuredWidth()) {
                return getWidth() - child.getMeasuredWidth();
            }
            return left;
        }

        @Override
        public int clampViewPositionVertical(View child, int top, int dy) {
            Log.e(TAG, "top:" + top + "++++dy:" + dy);
            //两个if主要是让view在ViewGroup中
            if (top < getPaddingTop()) {
                return getPaddingTop();
            }

            if (top > getHeight() - child.getMeasuredHeight()) {
                return getHeight() - child.getMeasuredHeight();
            }

            return top;
        }

        @Override
        public void onViewDragStateChanged(int state) {
            switch (state) {
                case ViewDragHelper.STATE_DRAGGING://正在拖动过程中
                    break;
                case ViewDragHelper.STATE_IDLE://view没有被拖动，或者正在进行fling
                    break;
                case ViewDragHelper.STATE_SETTLING://fling完毕后被放置到一个位置
                    break;
            }
            super.onViewDragStateChanged(state);
        }
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
        return true;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mDragView = findViewById(R.id.dragview);
    }
}

package com.xp.exercise.listview.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

/**
 * 
 * Function: 能获取到滚动距离的listView
 *
 * @author wangxiaopan
 * @Date 2016-05-20
 */
public class ListViewForScrollView extends ListView {
    private OnMyListViewScrollListener listener;
    private int mImageWidth,mImageHeight;
    private ImageView mHeaderImage;

    public ListViewForScrollView(Context context) {
        super(context);
    }

    public ListViewForScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ListViewForScrollView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    // @Override
    // /**
    // * 重写该方法，达到使ListView适应ScrollView的效果
    // */
    // protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    // int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
    // MeasureSpec.AT_MOST);
    // super.onMeasure(widthMeasureSpec, expandSpec);
    // }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        t = getCurrentScrollY();
        if (null != listener)
            this.listener.onMyListViewScrollChange(l, t, oldl, oldt);
    }


    public void setMyScrollListener(OnMyListViewScrollListener mListener) {
        this.listener = mListener;
    }

    public interface OnMyListViewScrollListener {
        public void onMyListViewScrollChange(int l, int t, int oldl, int oldt);
    }

    public int getCurrentScrollY() {
        View c = this.getChildAt(0);
        if (c == null) {
            return 0;
        }
        int firstVisiblePosition = this.getFirstVisiblePosition();
        int top = c.getTop();
        return -top + firstVisiblePosition * c.getHeight();
    }

    private int downX;
    private int downY;
    private int moveX;
    private int moveY;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
        case MotionEvent.ACTION_DOWN:
            downX = (int) event.getX();
            downY = (int) event.getY();
            getWidthAndHeight();
            break;
        case MotionEvent.ACTION_MOVE:
            moveX = (int) event.getX() - downX;
            moveY = (int) event.getY() - downY;
            setHeaderImageParams(moveY);
            break;
        case MotionEvent.ACTION_UP:
            setHeaderImageParams(0);
            break;
        }
        return super.onTouchEvent(event);
    }
    
    private void getWidthAndHeight(){
        View child = getChildAt(0);
        if (child instanceof ViewGroup){
            ViewGroup headerLayout = (ViewGroup)child;
            mHeaderImage = (ImageView) headerLayout.getChildAt(0);
            mImageHeight = headerLayout.getHeight();
            mImageWidth = headerLayout.getWidth();
        }
    }
    private void setHeaderImageParams(int scrollY) {
        if (scrollY <0) scrollY = 0;
        if (scrollY > 330) scrollY = 330;
        int width = mImageWidth;
        int height = mImageHeight;
        int curHeight = height + scrollY;
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width,curHeight);
        mHeaderImage.setLayoutParams(params);
    }

}

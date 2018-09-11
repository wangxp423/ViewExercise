package com.xp.exercise.scroller;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.LinearLayout;
import android.widget.Scroller;

public class MyLinearLayout extends LinearLayout{
    
    private Scroller mScroller;

    public MyLinearLayout(Context context) {
        super(context);
        initScroller(context);
    }
    public MyLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initScroller(context);
    }
    public MyLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initScroller(context);
    }
    
    private void initScroller(Context context){
//        mScroller = new Scroller(context,new LinearInterpolator());//匀速
//        mScroller = new Scroller(context,new AccelerateInterpolator());//加速
//        mScroller = new Scroller(context,new DecelerateInterpolator());//减速
        mScroller = new Scroller(context, new AccelerateDecelerateInterpolator());//先加速后减速
    }
    
    public void startScroller(int dx,int dy,int duration){
        mScroller.startScroll(0, 0, dx, dy, duration);
        invalidate();
    }
    
    @Override
    public void computeScroll() {
        // TODO Auto-generated method stub
        Log.d("Test", "MyLinearLayout.computeScroll()");
        if (mScroller.computeScrollOffset()) {
            // 因为调用computeScroll函数的是MyLinearLayout实例，
            // 所以调用scrollTo移动的将是该实例的孩子，也就是MyButton实例
            scrollTo(mScroller.getCurrX(), 0);
            Log.d("Test", "MyLinearLayout.getCurrX = " + mScroller.getCurrX());
            postInvalidate();
        }
        super.computeScroll();
    }

}

package com.xp.exercise.canvas.secoo;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Scroller;

import com.xp.exercise.R;

public class LineCircleTransitionView extends FrameLayout implements Runnable{
    
    int mCircleRadius;
    int mLineWidth;
    boolean isFromLeftToRight;
    private ImageView mLineLeft,mLineRight;
    private ImageView mCircleTop,mCircleBottom;
    private LayoutInflater mInflater;
    private Scroller mLineScroller;
    private Scroller mCircleScroller;

    public LineCircleTransitionView(Context context) {
        super(context);
        initUI(context);
    }
    public LineCircleTransitionView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initUI(context);
    }
    public LineCircleTransitionView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initUI(context);
    }
    
    private void initUI(Context context){
        mInflater = LayoutInflater.from(context);
        View view = mInflater.inflate(R.layout.line_circle_translate_view, this, true);
        mLineLeft = (ImageView) view.findViewById(R.id.lct_line_left);
        mLineRight = (ImageView) view.findViewById(R.id.lct_line_right);
        mCircleTop = (ImageView) view.findViewById(R.id.lct_circle_up);
        mCircleBottom = (ImageView) view.findViewById(R.id.lct_circle_bottom);
        
        Resources res = context.getResources();
        mLineLeft.setImageDrawable(new LineClipDrawable(res.getDrawable(R.drawable.speech_line), Gravity.LEFT, ClipDrawable.HORIZONTAL, true));
        mLineRight.setImageDrawable(new LineClipDrawable(res.getDrawable(R.drawable.speech_line), Gravity.LEFT, ClipDrawable.HORIZONTAL, false));
        mCircleTop.setImageDrawable(new CircleClipDrawable(res.getDrawable(R.drawable.speech_mic_circle), Gravity.LEFT, ClipDrawable.HORIZONTAL, true));
        mCircleBottom.setImageDrawable(new CircleClipDrawable(res.getDrawable(R.drawable.speech_mic_circle), Gravity.LEFT, ClipDrawable.HORIZONTAL, false));
        
        mLineScroller = new Scroller(context, new LinearInterpolator());
        mCircleScroller = new Scroller(context, new LinearInterpolator());
        
        mCircleRadius = -1;
        mLineWidth = -1;
    }
    
    @Override
    public void run() {
        boolean circleMore = mCircleScroller.computeScrollOffset();
        if (circleMore) {
            computScrollCircle();
            post(this);
        }
        
        boolean lineMore = mLineScroller.computeScrollOffset();
        if (lineMore) {
           computScrollLine();
           post(this);
        }
        
    }
    
    public void transition(){
        mLineScroller.startScroll(0, 0, mLineWidth, 300);
        mCircleScroller.startScroll(CircleClipDrawable.PI, 0, -CircleClipDrawable.PI, 0, 500);
        post(this);
        invalidate();
    }
    
    public void computScrollCircle(){
        int level = mCircleScroller.getCurrX();
        mCircleTop.setImageLevel(level);
        mCircleBottom.setImageLevel(level);
        invalidate();
    }
    public void computScrollLine(){
        int level = mLineScroller.getCurrX();
        mLineLeft.setImageLevel(level);
        mLineRight.setImageLevel(level);
        invalidate();
    }
    
    class LineClipDrawable extends ClipDrawable{
        Rect mTmpRect = new Rect();
        Drawable mDrawable;
        int mOrientation;
        boolean isLeft;
        
        LineClipDrawable(Drawable drawable, int gravity, int orientation,boolean isLeft){
            super(drawable, gravity, orientation);
            this.mDrawable = drawable;
            this.mOrientation = orientation;
            this.isLeft = isLeft;
        }
        
        @Override
        public void draw(Canvas canvas) {
            final Rect rect = mTmpRect;
            final Rect bounds = getBounds();

            final int width = bounds.width(); // image width
//            Log.d("Test", "LineClipDrawable.width = " + width);
            final int L = width/2; // line max show width
            if(mLineWidth < 0){
                mLineWidth = L;
            }
//            Log.d("Test", "LineClipDrawable.level = " + getLevel());
            int ldw = Math.min(L, getLevel());
            final int dx = (L - ldw);
            final int cdx = mCircleRadius*ldw/L;
//            Log.d("Test", "LineClipDrawable.dx = " + dx);
//            Log.d("Test", "LineClipDrawable.width = " + width + "  level = " + getLevel() + "  dx = " + dx + "  cdx = " + cdx);
            int r;
            int l;
            if(isLeft){
                r = L - cdx;
                l = r - dx;
            }else{
                l = L + cdx;
                r = l + dx;
            }
            
            r = Math.min(r, bounds.right);
            l = Math.max(l, bounds.left);
//            Log.d("Test", "LineClipDrawable.rect.bottom = " + bounds.bottom);
//            Log.d("Test", "LineClipDrawable.rect.top = " + bounds.top);
            // draw line
            rect.bottom = bounds.bottom;
            rect.top = bounds.top;
            rect.right = r;
            rect.left = l;
            canvas.save();
            canvas.clipRect(rect);
            mDrawable.draw(canvas);
            canvas.restore();
        }
    }
    
    class CircleClipDrawable extends ClipDrawable{
        Rect mTmpRect = new Rect();
        Drawable mDrawable;
        int mOrientation;
        boolean isUp;
        final static int PI = 180;
        final static int PI2 = PI/2;
        
        CircleClipDrawable(Drawable drawable, int gravity, int orientation,boolean isUp){
            super(drawable, gravity, orientation);
            this.mDrawable = drawable;
            this.mOrientation = orientation;
            this.isUp = isUp;
        }
        
        @Override
        public void draw(Canvas canvas) {
            final Rect rect = mTmpRect;
            final Rect bounds = getBounds();
//            Log.d("Test", "bouds = " +  bounds);
            if(mCircleRadius < 0){
                mCircleRadius = bounds.width()/2;
            }
            final int cy = (bounds.bottom - bounds.top)/2;
            final int cx = (bounds.right - bounds.left)/2;
            final int R = mCircleRadius;
            int level = getLevel();
//            Log.d("Test", "CircleClipDrawable.level = " + level);
            final double angle = Math.PI * level / PI;
//            Log.d("Test", "CircleClipDrawable.angle = " + angle);
            final int x = (int)(R * Math.cos(angle));
            final int y = (int)(R * Math.sin(angle));
//            Log.d("Test", "CircleClipDrawable.x = " + x);
//            Log.d("Test", "CircleClipDrawable.level = "+ level + " angle = " + angle+ " x = " + x + " y = " + y);
            int r = 0;
            int l = 0;
            int t = 0;
            int b = 0;
            // draw top circle
            if(isUp){
//                Log.d("Test", "CircleClipDrawable.isUp = ");
                b = cy;
                t = level>=PI2 ? bounds.top : cy - y;
                if(isFromLeftToRight){
                    l =  bounds.left;
                    r = cx - x;
                }else{
                    r = bounds.right;
                    l =  cx + x;
                }
            }else{
            // draw bottom circle
//                Log.d("Test", "CircleClipDrawable.isBottom = ");
              t = cy;
              b = level>=PI2 ? bounds.bottom : cy + y;
              if(!isFromLeftToRight){
                  l = bounds.left;
                  r = cx - x;
              }else{
                  l = cx + x;
                  r = bounds.right;
              }
            }
            rect.top = t;
            rect.bottom = b;
            rect.left = l;
            rect.right = r;
//            Log.d("Test", "CircleClipDrawable.rect.t = " + t + "  b = " + b + "  l = " + l +"  r = " + r);
            canvas.save();
            Log.d("Test",isUp +  "    rect = " +  rect);
            canvas.clipRect(rect);
            mDrawable.draw(canvas);
            canvas.restore();
        
        }
    }
}

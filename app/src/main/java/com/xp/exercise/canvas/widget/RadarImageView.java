package com.xp.exercise.canvas.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.xp.exercise.R;

public class RadarImageView extends ImageView {

    private int mBorderColor;
    private int mBorderWidth;
    private Paint mBorderPaint;
    private RectF mBorderRect;

    private Path mClipPath;

    public RadarImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
        init();
        initAttr(context, attrs);
    }

    public RadarImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
        initAttr(context, attrs);
    }

    private void initAttr(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CircleImageView);
        final int mWidth = typedArray.getDimensionPixelSize(R.styleable.CircleImageView_borderWidth, getResources().getDimensionPixelSize(R.dimen.radar_circle_width));
        final int mColor = typedArray.getDimensionPixelSize(R.styleable.CircleImageView_borderColor, getResources().getColor(R.color.white));
        setBorderWidth(mWidth);
        setBorderColor(mColor);
//        final int N = typedArray.getIndexCount();
//        Log.d("Test", "CircleImageView.N = " + N);
//        for (int i = 0; i < N; i++) {
//            int attr = typedArray.getIndex(i);
//            if (attr == R.styleable.CircleImageView_borderWidth) {
//                final int borderWidth = typedArray.getDimensionPixelSize(R.styleable.CircleImageView_borderWidth, 3);
//                setBorderWidth(borderWidth);
//                Log.d("Test", "CircleImageView.setBorderWidth");
//            } else if (attr == R.styleable.CircleImageView_borderColor) {
//                final int borderColor = typedArray.getDimensionPixelSize(R.styleable.CircleImageView_borderColor,
//                        Color.RED);
//                setBorderColor(borderColor);
//                Log.d("Test", "CircleImageView.setBorderColor");
//            }
//        }
    }

    private void init() {
        mBorderPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mBorderPaint.setStyle(Paint.Style.STROKE);
    }

    @Override
    public void onLayout(boolean changed, int left, int top, int right, int bottom) {
        if (changed) {
            if (mClipPath == null)
                mClipPath = new Path();
            mClipPath.reset();

            RectF rect = new RectF(0, 0, right - left, bottom - top);
            mBorderRect = rect;
            mClipPath.addOval(rect, Path.Direction.CW);
        }
    }

    @Override
    public void onDraw(Canvas canvas) {
        if (getDrawable() == null) {
            return;
        }
        final int saveCount = canvas.save();
        if (mClipPath != null)
            canvas.clipPath(mClipPath);
        super.onDraw(canvas);
        if (mBorderWidth > 0 && mBorderRect != null) {
            canvas.drawArc(mBorderRect, 0, 360, false, mBorderPaint);
        }
        canvas.restoreToCount(saveCount);
    }

    public int getBorderColor() {
        return mBorderColor;
    }

    public void setBorderColor(int borderColor) {
        mBorderColor = borderColor;
        mBorderPaint.setColor(mBorderColor);
        invalidate();
    }

    public int getBorderWidth() {
        return mBorderWidth;
    }

    public void setBorderWidth(int borderWidth) {
        mBorderWidth = borderWidth;
        mBorderPaint.setStrokeWidth(borderWidth);
    }
}
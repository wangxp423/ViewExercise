package com.xp.exercise.speed;

import java.util.Random;

import com.xp.exercise.R;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;


public class SoundWaveView extends View {
    
    private static final int COUNT_LINE = 40;
    private Paint mPaint;
    private float[] mLineHeights;
    private float[] mNextLineHeights = new float[COUNT_LINE];
    private float[] mDetalYs = new float[COUNT_LINE];
    private final int LINE_WIDTH, LINE_SPACE;
    private long mLastChangeTime, mLastDrawTime;
    
    public SoundWaveView(Context context) {
        this(context, null, -1);
    }
    
    public SoundWaveView(Context context, AttributeSet attrs) {
        this(context, attrs, -1);
    }
    
    public SoundWaveView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LINE_WIDTH = getResources().getDimensionPixelSize(R.dimen.sound_wave_line_width);
        LINE_SPACE = getResources().getDimensionPixelSize(R.dimen.sound_wave_line_space);
        init();
    }
    
    public void init () {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(0xFFffffff);
        mPaint.setStrokeWidth(LINE_WIDTH);
    }
    
    public void reset () {
        mLineHeights = new float[COUNT_LINE];
        mLastDrawTime = System.currentTimeMillis();
    }
    
    public void setSoundVolume (int volume) {
        long now = System.currentTimeMillis();
        if (now - mLastChangeTime < 20) return;
        final int maxHeight = getHeight() >> 1;
        final Random ran = new Random();
        int index = 0;
        float curValue = 0;
        do {
            int step = 1 + ran.nextInt(2);
            int keyIndex = index + step;
            Log.d("Test","step = " + step + " index = " + index + "  keyIndex =" + keyIndex);
            float curMaxHeight = maxHeight * (volume + 0.2f) / 15;
            float random = ran.nextFloat();
            float value;
            if (keyIndex % 8 == 0) value = random * curMaxHeight / 4;
            else if (keyIndex % 5 == 0) value = curMaxHeight / 2 + random * curMaxHeight / 2;
            else value = random * curMaxHeight;
            float gap = (value - curValue) / step;
            for (int i=0;i<step;i++) {
//                Log.d("Test","step = " + step + " index = " + index + "  i =" + i);
                final int curIndex = index + i;
                if (curIndex >= COUNT_LINE) break;
                mNextLineHeights[curIndex] = curValue + gap * i;
            }
            curValue = value;
            index = keyIndex;
        } while (index < COUNT_LINE);
        if (mLineHeights == null) mLineHeights = new float[COUNT_LINE];
        for (int i=0;i<COUNT_LINE;i++) {
            float detal = mNextLineHeights[i] - mLineHeights[i];
            mDetalYs[i] = detal / (180 + ran.nextInt(50));
        }
        mLastChangeTime = now;
    }
    
    @Override
    public void dispatchDraw (Canvas canvas) {
        super.dispatchDraw(canvas);
//        Log.d("Test", "width = " + getWidth() + "  height = " + getHeight());
        final int centerY = getHeight() >> 1;
        final int unitWidth = LINE_WIDTH + LINE_SPACE;
        final int startX = (getWidth() - unitWidth * COUNT_LINE - LINE_SPACE) >> 1;
        if (mLastDrawTime == 0) mLastDrawTime = System.currentTimeMillis();
        final long elapse = System.currentTimeMillis() - mLastDrawTime;
//        Log.d("Test", "elapse = " + elapse);
        for (int i=0;i<COUNT_LINE;i++) {
            final int lineX = startX + unitWidth*i;
            if (mLineHeights == null) break;
            float height = mLineHeights[i];
            if (height < 0) height = 0;
            canvas.drawLine(lineX, centerY - height, lineX, centerY + height, mPaint);
//            Log.d("Test", " i = "+ i +"   height = " + height + "   mDetalYs[i] = " + mDetalYs[i]);
            height += mDetalYs[i] * elapse;
//            Log.d("Test", " i = "+ i + "   height = " + height);
            if (height < 0) {
                height = 0;
                mDetalYs[i] = -mDetalYs[i];
            } else if (height > centerY) {
                height = centerY;
                mDetalYs[i] = -mDetalYs[i];
            }
            mLineHeights[i] = height;
        }
        invalidate();
        mLastDrawTime = System.currentTimeMillis();
    }
}

package com.xp.exercise.canvas;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * @类描述：流量波动球
 * @创建人：Wangxiaopan
 * @创建时间：2018/2/23 0023 17:58
 * @修改人：
 * @修改时间：2018/2/23 0023 17:58
 * @修改备注：
 */

public class FlowWaveView extends View {
    public FlowWaveView(Context context) {
        super(context);
    }

    public FlowWaveView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public FlowWaveView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public FlowWaveView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
}

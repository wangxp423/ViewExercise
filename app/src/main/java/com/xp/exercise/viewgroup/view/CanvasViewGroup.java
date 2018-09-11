package com.xp.exercise.viewgroup.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.xp.exercise.R;
import com.xp.exercise.util.LogUtils;

/**
 * @类描述：应用常量类
 * @创建人：Wangxiaopan
 * @创建时间：2018/8/24 0024 18:06
 * @修改人：
 * @修改时间：2018/8/24 0024 18:06
 * @修改备注：
 */
public class CanvasViewGroup extends ViewGroup {
    public CanvasViewGroup(Context context) {
        super(context);
        //默认ViewGroup下的onDraw是不走的。
        //下面两个方法中的任何一个 都会使ViewGroup走onDraw方法。m
        setWillNotDraw(false);
        setBackgroundColor(getResources().getColor(R.color.red));
    }

    public CanvasViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CanvasViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        LogUtils.d("Test", "onDraw");
        drawCircleImage(canvas);
        super.onDraw(canvas);
    }

    @Override
    public void draw(Canvas canvas) {
        LogUtils.d("Test", "draw");
//        drawCircleImage(canvas);
        super.draw(canvas);
    }

    @Override
    protected boolean drawChild(Canvas canvas, View child, long drawingTime) {
        LogUtils.d("Test", "drawChild");
        drawCircleImage(canvas);
        return super.drawChild(canvas, child, drawingTime);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        LogUtils.d("Test", "onLayout");

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        LogUtils.d("Test", "onMeasure");
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    private Canvas drawCircleImage(Canvas canvas) {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.background_mine_head).copy(Bitmap.Config.ARGB_8888, true);
        canvas.drawBitmap(bitmap, new Matrix(), new Paint());
        int bitmapWidth = bitmap.getWidth();
        int bitmapHeight = bitmap.getHeight();
        canvas.save();
        Path clipPath = new Path();
        RectF clipRect = new RectF(0, 0, bitmapHeight, bitmapHeight);
        clipPath.addOval(clipRect, Path.Direction.CW);
//        clipPath.addCircle(bitmapWidth/2, bitmapHeight/2, bitmapHeight/2, Direction.CW);
        canvas.clipPath(clipPath);
//        canvas.clipRect(clipRect);
        canvas.drawBitmap(bitmap, null, clipRect, new Paint());
        return canvas;
    }
}

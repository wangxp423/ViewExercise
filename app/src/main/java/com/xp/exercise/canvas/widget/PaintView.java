package com.xp.exercise.canvas.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ComposeShader;
import android.graphics.DashPathEffect;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.xp.exercise.R;

/**
 * @类描述：Paint 练习
 * @创建人：Wangxiaopan
 * @创建时间：2018/1/29 0029 16:03
 * @修改人：
 * @修改时间：2018/1/29 0029 16:03
 * @修改备注：
 */

public class PaintView extends View {
    public PaintView(Context context) {
        super(context);
    }

    public PaintView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public PaintView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public PaintView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
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

    //https://www.jianshu.com/p/2aa0b8585f68
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //加这行代码 圆形阴影才能生效
        //这个最好不要使用会导致onDraw不停的执行 而且会导致cup内存开销变大
        setLayerType(LAYER_TYPE_SOFTWARE, null);
//        Test1(canvas);
//        Test2(canvas);
//        Test3(canvas);
//        Test4(canvas);
//        Test5(canvas);
        Test6(canvas);
//        Test7(canvas);
//        Test8(canvas);
    }

    private void Test1(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStrokeWidth(10);
        float radius = 100f;
        paint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(200, 200, radius, paint);
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawCircle(200, 500, radius, paint);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        canvas.drawCircle(200, 800, radius, paint);
    }

    private void Test2(Canvas canvas) {
        //主要在TileModel的三个参数CLAMP用边缘区填充剩下的位置，MIRROR用镜子倒影去填充，REPEAT用当前图片去填充
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.meinv_small).copy(Bitmap.Config.ARGB_8888, true);
        BitmapShader bitmapShader = new BitmapShader(bitmap, Shader.TileMode.MIRROR, Shader.TileMode.MIRROR);
        Paint paint = new Paint();
        paint.setShader(bitmapShader);
        canvas.drawRect(0, 0, bitmap.getWidth() * 2, bitmap.getHeight() * 2, paint);
    }

    private void Test3(Canvas canvas) {
        LinearGradient linearGradient = new LinearGradient(200, 200, 600, 600, Color.GREEN, Color.YELLOW, Shader.TileMode.REPEAT);
        Paint paint = new Paint();
        paint.setShader(linearGradient);
        canvas.drawRect(200, 200, 600, 600, paint);

        int[] colors = {Color.GREEN, Color.GRAY, Color.RED, Color.BLUE};
        float[] positions = {0f, 0.5f, 0.75f, 1f};
        LinearGradient linearGradient1 = new LinearGradient(200, 800, 600, 1200, colors, positions, Shader.TileMode.CLAMP);
        paint.setShader(linearGradient1);
        canvas.drawRect(200, 800, 600, 1200, paint);
    }

    //放射状梯度渐变
    private void Test4(Canvas canvas) {
        final float radius = 200f;
        RadialGradient gradient = new RadialGradient(400, 400, radius, Color.GREEN, Color.BLACK, Shader.TileMode.CLAMP);
        Paint paint = new Paint();
        paint.setShader(gradient);
        canvas.drawCircle(400, 400, radius, paint);

        int[] colors = {Color.GREEN, Color.GRAY, Color.RED, Color.BLUE};
        float[] positions = {0f, 0.5f, 0.75f, 1f};
        RadialGradient gradient1 = new RadialGradient(400, 800, radius, colors, positions, Shader.TileMode.CLAMP);
        paint.setShader(gradient1);
        canvas.drawCircle(400, 800, radius, paint);
    }

    //扫描渐变
    private void Test5(Canvas canvas) {
        final float radius = 200f;
        SweepGradient gradient = new SweepGradient(400, 400, Color.GREEN, Color.RED);
        Paint paint = new Paint();
        paint.setShader(gradient);
        canvas.drawCircle(400, 400, radius, paint);

        int[] colors = {Color.GREEN, Color.GRAY, Color.RED, Color.BLUE};
        float[] positions = {0f, 0.5f, 0.75f, 1f};
        SweepGradient gradient1 = new SweepGradient(400, 800, colors, positions);
        paint.setShader(gradient1);
        canvas.drawCircle(400, 800, radius, paint);
    }

    //扫描渐变圆环
    private void Test6(Canvas canvas) {
        canvas.save();
        Paint mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setAntiAlias(true);
        //绘制5px的实线,再绘制5px的透明 从0位置开始
        DashPathEffect dashPathEffect2 = new DashPathEffect(new float[]{5, 5}, 0);
        mPaint.setPathEffect(dashPathEffect2);
        mPaint.setStrokeWidth(30);
        final float radius = 200f;
        int[] colors = {Color.GRAY, Color.WHITE};
        float[] positions = {0.75f, 1f};
        SweepGradient gradient1 = new SweepGradient(400, 800, colors, positions);
        mPaint.setShader(gradient1);
        canvas.drawCircle(400, 800, radius, mPaint);
        canvas.restore();
    }

    private void Test7(Canvas canvas) {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.icon_red_start).copy(Bitmap.Config.ARGB_8888, true);
        BitmapShader bitmapShader = new BitmapShader(bitmap, Shader.TileMode.MIRROR, Shader.TileMode.MIRROR);
        int[] colors = {Color.YELLOW, Color.BLACK, Color.WHITE, Color.BLUE};
        LinearGradient linearGradient = new LinearGradient(0, 0, 0, 100, colors, null, Shader.TileMode.MIRROR);
        ComposeShader composeShader = new ComposeShader(bitmapShader, linearGradient, PorterDuff.Mode.MULTIPLY);
        Paint paint = new Paint();
        paint.setShader(composeShader);
        canvas.drawCircle(400, 600, 400, paint);
    }

    private void Test8(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.RED);
        paint.setTextSize(100);
        paint.setShadowLayer(10, 10, 10, Color.YELLOW); //参数1 阴影模糊半径 23 偏移位置
        canvas.drawText("Hello World", 0, 200, paint);
        canvas.drawCircle(400, 800, 200, paint);
    }
}

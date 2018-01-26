package com.xp.exercise.canvas;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Path.Direction;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.xp.exercise.R;

public class CanvasView extends View {
    
    private Path mClipPath;

    public CanvasView(Context context) {
        super(context);
    }

    public CanvasView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CanvasView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    
    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        
    }
    
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        setLayerType(View.LAYER_TYPE_SOFTWARE, new Paint());
//        DrawTest(canvas);
        DrawCircleImage(canvas);
//        clipImageView(canvas);
    }
    private void DrawTest(Canvas canvas){
        canvas.drawRGB(180, 250, 250);
        Paint paint=new Paint();   
        canvas.save();   
        canvas.clipRect(new Rect(100,100,300,300));  
        canvas.drawColor(Color.BLUE);//裁剪区域的rect变为蓝色   
        canvas.drawRect(new Rect(0,0,100,100), paint);//在裁剪的区域之外，不能显示   
        canvas.drawCircle(150,150, 50, paint);//在裁剪区域之内，能显示  
        canvas.restore();  
    }
    
    private Canvas DrawCircleImage(Canvas canvas){
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.background_mine_head).copy(Bitmap.Config.ARGB_8888, true);
        canvas.drawBitmap(bitmap, new Matrix(), new Paint());
        int bitmapWidth = bitmap.getWidth();
        int bitmapHeight = bitmap.getHeight();
        canvas.save();
        Path clipPath = new Path();
        RectF clipRect = new RectF(0,0,bitmapHeight,bitmapHeight);
        clipPath.addOval(clipRect, Direction.CW);
//        clipPath.addCircle(bitmapWidth/2, bitmapHeight/2, bitmapHeight/2, Direction.CW);
        canvas.clipPath(clipPath);
//        canvas.clipRect(clipRect);
        canvas.drawBitmap(bitmap, null, clipRect, new Paint());
        return canvas;
    }
    
    private Canvas clipImageView(Canvas canvas){
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.background_mine_head).copy(Bitmap.Config.ARGB_8888, true);
        Log.d("Test", "bitmap.width = " + bitmap.getWidth() + "   bitmap.height = " + bitmap.getHeight());
//        canvas.drawBitmap(bitmap, null, clipRect, new Paint());
        canvas.save();
        Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight()/2);
        canvas.clipRect(rect);
        Matrix matrix = new Matrix();
        matrix.postScale(1f, 1f); //图片正常比例显示
//        canvas.drawBitmap(bitmap, new Matrix(), new Paint()); //正常显示图片
//        canvas.drawBitmap(bitmap, null, rect , new Paint()); //剪切 但是图片压缩了
        canvas.drawBitmap(bitmap, matrix, new Paint()); //剪切 图片正常显示(只有上半部分)
        canvas.restore();
        return canvas;
    }
}

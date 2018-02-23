package com.xp.exercise.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.xp.exercise.canvas.PathCubic;

public class CanvasActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // CanvasView
//        setContentView(new CanvasView(this));
        //PaintView
//        setContentView(new PaintView(this));
        //PathView
//        setContentView(new PathView(this));
//        贝塞尔曲线
        setContentView(new PathCubic(this));
    }
}

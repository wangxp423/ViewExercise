package com.xp.exercise.canvas;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.xp.exercise.canvas.FlowWaveView;
import com.xp.exercise.canvas.PaintView;
import com.xp.exercise.canvas.PathCubic;
import com.xp.exercise.canvas.PathView;

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
//        setContentView(new PathCubic(this));
//        流量水波纹百分比显示
        setContentView(new FlowWaveView(this));
    }
}

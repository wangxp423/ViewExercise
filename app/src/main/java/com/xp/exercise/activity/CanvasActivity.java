package com.xp.exercise.activity;

import com.xp.exercise.canvas.CanvasView;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

public class CanvasActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // CanvasView
        setContentView(new CanvasView(this));
    }
}

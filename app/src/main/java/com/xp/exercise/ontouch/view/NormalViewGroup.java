package com.xp.exercise.ontouch.view;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

import com.xp.exercise.util.LogUtils;

public class NormalViewGroup extends FrameLayout {
    public NormalViewGroup(Context context) {
        super(context);
        init();
    }

    public NormalViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public NormalViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public NormalViewGroup(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtils.d("Test", "NormalViewGroup.onClick");
            }
        });
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                LogUtils.d("Test", "ACTION_DOWN");
//                return true;
                break;
            case MotionEvent.ACTION_MOVE:
                LogUtils.d("Test", "ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                LogUtils.d("Test", "ACTION_UP");
//                return true;
                break;
            case MotionEvent.ACTION_CANCEL:
                LogUtils.d("Test", "ACTION_CANCEL");
                break;
            default:
                LogUtils.d("Test", "default");
                break;
        }
        return super.dispatchTouchEvent(ev);
//        return false;
//        return true;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                LogUtils.d("Test", "ACTION_DOWN");
//                return true;
                break;
            case MotionEvent.ACTION_MOVE:
                LogUtils.d("Test", "ACTION_MOVE");
//                return true;
                break;
            case MotionEvent.ACTION_UP:
                LogUtils.d("Test", "ACTION_UP");
//                return true;
                break;
            case MotionEvent.ACTION_CANCEL:
                LogUtils.d("Test", "ACTION_CANCEL");
                break;
            default:
                LogUtils.d("Test", "default");
                break;
        }
        return super.onInterceptTouchEvent(ev);
//        return false;
//        return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                LogUtils.d("Test", "ACTION_DOWN");
//                return true;
                break;
            case MotionEvent.ACTION_MOVE:
                LogUtils.d("Test", "ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                LogUtils.d("Test", "ACTION_UP");
//                return true;
                break;
            case MotionEvent.ACTION_CANCEL:
                LogUtils.d("Test", "ACTION_CANCEL");
                break;
            default:
                LogUtils.d("Test", "default");
                break;
        }
        return super.onTouchEvent(event);
//        return false;
//        return true;
    }
}

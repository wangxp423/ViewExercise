package com.xp.exercise.ontouch.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.xp.exercise.util.LogUtils;

/**
 * 执行顺序：onTouch--->onTouchEvent--->onClick
 * 注意：onClick直接消费掉了事件，不会再向上回传了
 */
@SuppressLint("AppCompatCustomView")
public class NormalView extends TextView {
    public NormalView(Context context) {
        super(context);
        init();
    }

    public NormalView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public NormalView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public NormalView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
//        setEnabled(false);
//        setClickable(false);
        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtils.d("Test", "onClick: ======");
            }
        });
        setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
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
                return false;
            }
        });
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                LogUtils.d("Test", "ACTION_DOWN");
//                getParent().requestDisallowInterceptTouchEvent(true);
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
        return super.dispatchTouchEvent(event);
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

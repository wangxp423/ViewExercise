package com.xp.exercise.ontouch;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;

import com.xp.exercise.R;
import com.xp.exercise.util.LogUtils;

/**
 * @类描述：View 常规事件探索
 * @创建人：Wangxiaopan
 * @创建时间：2018/9/6 0006 18:38
 * @修改人：
 * @修改时间：2018/9/6 0006 18:38
 * @修改备注：
 */
public class TouchNormalActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ontouch_activity_normal);
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
//        return false
//        return true
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                LogUtils.d("Test", "ACTION_DOWN");
//                return true
                break;
            case MotionEvent.ACTION_MOVE:
                LogUtils.d("Test", "ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                LogUtils.d("Test", "ACTION_UP");
                break;
            case MotionEvent.ACTION_CANCEL:
                LogUtils.d("Test", "ACTION_CANCEL");
                break;
            default:
                LogUtils.d("Test", "default");
                break;
        }
        return super.onTouchEvent(event);
//        return true;
//        return false;
    }
}

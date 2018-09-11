package com.xp.exercise.ontouch.view;

import android.content.Context;
import android.support.v4.widget.NestedScrollView;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.xp.exercise.util.LogUtils;

/**
 * @类描述：应用常量类
 * @创建人：Wangxiaopan
 * @创建时间：2018/9/11 0011 9:34
 * @修改人：
 * @修改时间：2018/9/11 0011 9:34
 * @修改备注：
 */
public class NormalScrollView extends NestedScrollView {
    public NormalScrollView(Context context) {
        super(context);
    }

    public NormalScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public NormalScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
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
                return true;
//                break;
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

package com.xp.exercise.ontouch.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.xp.exercise.util.LogUtils;

/**
 * @类描述：应用常量类
 * @创建人：Wangxiaopan
 * @创建时间：2018/9/10 0010 12:47
 * @修改人：
 * @修改时间：2018/9/10 0010 12:47
 * @修改备注：
 */
public class NormalRecyclerView extends RecyclerView {
    public NormalRecyclerView(Context context) {
        super(context);
    }

    public NormalRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public NormalRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
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
        getParent().requestDisallowInterceptTouchEvent(true);
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

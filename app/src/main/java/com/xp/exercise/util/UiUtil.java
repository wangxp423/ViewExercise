package com.xp.exercise.util;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;

public class UiUtil {
	
	private static DisplayMetrics dm;
	public static DisplayMetrics getDisplayMetrics (Context context) {
		if (dm == null) {
//			dm = new DisplayMetrics();
//			activity.getWindowManager().getDefaultDisplay().getMetrics(dm); // 得到屏幕大小
			dm = context.getResources().getDisplayMetrics();
		}
		return dm;
	}

	/**
	 * 取得屏幕宽度像素
	 * 
	 * @param context 设备上下文
	 * @return 屏幕的宽度
	 */
	public static int getScreenWidth(Context context) {
		DisplayMetrics dm = getDisplayMetrics(context);
		return dm.widthPixels;
	}

	/**
	 * 取得屏幕高度像素
	 * 
	 * @param context 设备上下文
	 * @return 屏幕的高度
	 */
	public static int getScreenHeight(Context context) {
		DisplayMetrics dm = getDisplayMetrics(context);
		return dm.heightPixels;
	}

	/**
	 * 取得屏幕尺寸(单位：英寸)
	 * 
	 * @param context 设备上下文
	 * @return 屏幕的英寸尺寸
	 */
	public static float getScreenSize(Context context) {
		DisplayMetrics dm = getDisplayMetrics(context);
		return dm.heightPixels / dm.ydpi;
	}
	
	public static void clearChildSelected (ViewGroup parent) {
		final int count = parent.getChildCount();
		for (int i=count-1;i>=0;i--) {
			parent.getChildAt(i).setSelected(false);
		}
	}
	/**
	 * 关闭输入法
	 */
	public static void closeInputMethod (View inputView) {
        android.view.inputmethod.InputMethodManager im = (android.view.inputmethod.InputMethodManager) inputView.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (im.isActive()) {
        	im.hideSoftInputFromWindow(inputView.getWindowToken(),
            android.view.inputmethod.InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }
	/**
	 * 调出输入法
	 */
	public static void showInputMethod (View inputView) {
        android.view.inputmethod.InputMethodManager im = (android.view.inputmethod.InputMethodManager) inputView.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        inputView.setFocusable(true);
		inputView.setFocusableInTouchMode(true);
        inputView.requestFocus();
        im.showSoftInput(inputView, 0);
    }
	
	/**
	 * 获取view的顶部坐标相对与父view坐标系中值
	 * @param view
	 * @param parent
	 * @return
	 */
	public static int getViewTopInParent (View view, View parent) {
		int top = 0;
    	do {
    		top += view.getTop();
    		android.view.ViewParent vp = view.getParent();
    		if (vp == null || !(vp instanceof View)) return top;
    		view = (View)vp;
    		top -= view.getScrollY();
    	} while (view != parent);
    	
    	return top;
	}
	public static int getViewLeftInParent (View view, View parent) {
		int left = 0;
    	do {
    		left += view.getLeft();
    		android.view.ViewParent vp = view.getParent();
    		if (vp == null || !(vp instanceof View)) return left;
    		view = (View)vp;
    		left -= view.getScrollX();
    	} while (view != parent);
    	
    	return left;
	}
	
	public static boolean isChildOf (View view, Class<?> parent) {
	    do {
            android.view.ViewParent vp = view.getParent();
            if (vp == null || !(vp instanceof View)) return false;
            if ((vp.getClass() == parent) || (vp.getClass().getSuperclass() == parent)) return true;
            view = (View)vp;
        } while (view.getClass() != parent);
	    
	    return false;
	}
	
	/**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }
}

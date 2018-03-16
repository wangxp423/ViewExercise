package com.xp.exercise.statsbar;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;

import com.xp.exercise.R;
import com.xp.exercise.statsbar.util.OsUtil;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @类描述：改变状态栏 字体颜色(适配白色底标题栏)基类
 * @创建人：Wangxiaopan
 * @创建时间：2018/3/15 0015 11:35
 * @修改人：
 * @修改时间：2018/3/15 0015 11:35
 * @修改备注：
 */
public class CompatStatusBarActivity extends StatusBarBaseActivity {

    private FrameLayout mFrameLayoutContent;
    private View mViewStatusBarPlace;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_compat_status_bar);

        mViewStatusBarPlace = findViewById(R.id.view_status_bar_place);
        mFrameLayoutContent = (FrameLayout) findViewById(R.id.frame_layout_content_place);

        ViewGroup.LayoutParams params = mViewStatusBarPlace.getLayoutParams();
        params.height = getStatusBarHeight();
        mViewStatusBarPlace.setLayoutParams(params);
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        //这里能够看到 我们这里其实是一个适配基类。布局中增加了一个View 用来适配状态栏的高度并调整颜色
        //contentLayout会将继承自这个Activity的页面的layout添加进去以达到通用的目的
        View contentView = LayoutInflater.from(this).inflate(layoutResID, null);
        mFrameLayoutContent.addView(contentView);
    }

    /**
     * 设置沉浸式状态栏
     *
     * @param isDark 状态栏字体和图标颜色是否为浅色(白色)
     */
    //适配方案2, 4.4以下的不适配，4.4-6.0修改View颜色为浅灰色，6.0以上修改View颜色为白色，修改状态栏字体颜色
    protected void setImmersiveStatusBar(boolean isDark, int statusBarPlaceColor) {
        if (isDark) {
            //6.0+ 小米 魅族 可以直接适配
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M || OsUtil.isMIUI() || OsUtil.isFlyme()) {
                setStatusBarTextDark(true);
                setStatusBarPlaceColor(statusBarPlaceColor);
            } else {
                if (statusBarPlaceColor == Color.WHITE) {
                    statusBarPlaceColor = 0xffcccccc;
                }
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) { //4.4以上修改为浅灰色
                    setStatusBarPlaceColor(statusBarPlaceColor);
                } else { //4.4以下不适配
                    if (mViewStatusBarPlace.getVisibility() == View.VISIBLE) {
                        mViewStatusBarPlace.setVisibility(View.GONE);
                    }
                }
            }
        }
    }

    private void setStatusBarPlaceColor(int statusColor) {
        if (mViewStatusBarPlace != null) {
            mViewStatusBarPlace.setBackgroundColor(statusColor);
        }
    }

    public int getStatusBarHeight() {
        int statusBarHeight = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            statusBarHeight = getResources().getDimensionPixelSize(resourceId);
        }
        return statusBarHeight;
    }

    /**
     * 修改状态栏字体颜色只能在android6.0以上原生系统中，同时小米和魅族提供与方法，其他厂商可能无效过
     * 设置Android状态栏的字体颜色，状态栏为亮色的时候字体和图标是黑色，状态栏为暗色的时候字体和图标为白色
     *
     * @param dark 状态栏字体和图标是否为深色
     */
    protected void setStatusBarTextDark(boolean dark) {
        // 小米MIUI
        try {
            Window window = getWindow();
            Class clazz = getWindow().getClass();
            Class layoutParams = Class.forName("android.view.MiuiWindowManager$LayoutParams");
            Field field = layoutParams.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE");
            int darkModeFlag = field.getInt(layoutParams);
            Method extraFlagField = clazz.getMethod("setExtraFlags", int.class, int.class);
            if (dark) {    //状态栏亮色且黑色字体
                extraFlagField.invoke(window, darkModeFlag, darkModeFlag);
            } else {       //清除黑色字体
                extraFlagField.invoke(window, 0, darkModeFlag);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 魅族FlymeUI
        try {
            Window window = getWindow();
            WindowManager.LayoutParams lp = window.getAttributes();
            Field darkFlag = WindowManager.LayoutParams.class.getDeclaredField("MEIZU_FLAG_DARK_STATUS_BAR_ICON");
            Field meizuFlags = WindowManager.LayoutParams.class.getDeclaredField("meizuFlags");
            darkFlag.setAccessible(true);
            meizuFlags.setAccessible(true);
            int bit = darkFlag.getInt(null);
            int value = meizuFlags.getInt(lp);
            if (dark) {
                value |= bit;
            } else {
                value &= ~bit;
            }
            meizuFlags.setInt(lp, value);
            window.setAttributes(lp);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // android6.0+系统
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (dark) {
                getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            }
        }
    }
}

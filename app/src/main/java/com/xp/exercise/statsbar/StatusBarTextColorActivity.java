package com.xp.exercise.statsbar;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.xp.exercise.R;
import com.xp.exercise.statsbar.util.OsUtil;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @类描述：改变状态栏 字体颜色(适配白色底标题栏)
 * @创建人：Wangxiaopan
 * @创建时间：2018/3/15 0015 11:35
 * @修改人：
 * @修改时间：2018/3/15 0015 11:35
 * @修改备注：
 */

public class StatusBarTextColorActivity extends StatusBarBaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statusbar_text);
        //此页面主要做的是当标题栏为白色的时候 适配状态栏透明的同时 修改状态栏的字体颜色
        //因修改状态栏字体颜色只支持6.0以上系统，适配低版本的时候，我们修改状态栏颜色为浅灰色(大部分APP适配规则)
        setImmersiveStatusBar(true, 0);
    }

    /**
     * 设置沉浸式状态栏
     *
     * @param isDark 状态栏字体和图标颜色是否为浅色(白色)
     */
    //适配方案1, 4.4以下的不适配，4.4-5.0的也不适配，5.0-6.0修改状态栏颜色为浅灰色，6.0以上修改状态栏字体颜色
    public void setImmersiveStatusBar(boolean isDark, int statsBarcolor) {
        if (isDark) {
            if (OsUtil.isMIUI() || OsUtil.isFlyme() || Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                setStatusBarTextDark(isDark);
            } else {
                Window window = getWindow();
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) { //5.0以上
                    //兼容5.0 状态栏半透明情况， 貌似并没有什么卵用(机型锤子T2 5.0系统)(跟厂商定制有关原生有用)
                    window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                    window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
                    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                    window.setStatusBarColor(0xffcccccc);
                } else { //4.4-5.0 以及 4.4以下
                    window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                }
            }
        }
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

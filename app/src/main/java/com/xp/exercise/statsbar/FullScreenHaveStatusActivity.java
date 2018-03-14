package com.xp.exercise.statsbar;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.xp.exercise.BaseActivity;
import com.xp.exercise.R;

/**
 * @类描述：全屏 显示状态栏
 * @创建人：Wangxiaopan
 * @创建时间：2018/3/13 0013 16:13
 * @修改人：
 * @修改时间：2018/3/13 0013 16:13
 * @修改备注：
 */

public class FullScreenHaveStatusActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fullscreen_havastatus);
        Window window = getWindow();
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.JELLY_BEAN_MR2) {
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            ViewGroup contentView = (ViewGroup) window.getDecorView().findViewById(Window.ID_ANDROID_CONTENT);
            contentView.getChildAt(0).setFitsSystemWindows(false);

//            window.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
//                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        /*
         这里fitsSystemWindows是什么意思呢?相信很多人会有疑问?
         注意不是设置 ContentView 的 FitsSystemWindows, 而是设置 ContentView 的第一个子 View . 预留出系统 View 的空间.
         看我们代码流程,首先是设置了FLAG_TRANSLUCENT_STATUS 将状态栏设置为透明的状态
         fitsSystemWindows代表的是:当设置SystemBar(包含StatusBar&NavigationBar)透明之后,
         将fitsSystemWindows至为true,则还是为SystemBar预留空间,当设置为false的时候,就是不为SystemBar预留空间
         现在这个业务场景是不为StatusBar预留空间,所以我们先将StatusBar设置为透明,然后不预留空间
         这样就实现了我们的功能,全屏但是不隐藏statusBar文字
         */
    }
}

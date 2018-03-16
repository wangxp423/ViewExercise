package com.xp.exercise.statsbar;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.xp.exercise.R;
import com.xp.exercise.statsbar.base.StatusBarBaseActivity;

/**
 * @类描述：适配白底标题栏(方案一)改变状态栏字体颜色
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
        int statusBarColor = 0xffcccccc;
        setColorStatusBar(true, statusBarColor);
    }
}

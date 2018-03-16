package com.xp.exercise.statsbar;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;

import com.xp.exercise.R;
import com.xp.exercise.statsbar.base.CompatStatusBarActivity;

/**
 * @类描述：适配白底标题栏(方案二)顶部添加View,改变View颜色
 * @创建人：Wangxiaopan
 * @创建时间：2018/3/15 0015 16:52
 * @修改人：
 * @修改时间：2018/3/15 0015 16:52
 * @修改备注：
 */

public class StatusBarTextColor2Activity extends CompatStatusBarActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statusbar_text2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_next);
        int color = 0xffffffff;
        toolbar.setBackgroundColor(color);
        //此页面主要做的是当标题栏为白色的时候 适配状态栏透明的同时 修改添加View的颜色来的达到适配的目的
        setViewColorStatusBar(true, color);
    }
}

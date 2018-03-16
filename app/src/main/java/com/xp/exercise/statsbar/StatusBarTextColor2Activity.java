package com.xp.exercise.statsbar;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;

import com.xp.exercise.R;

/**
 * @类描述：改变状态栏 字体颜色(适配白色底标题栏)方案二
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
        setImmersiveStatusBar(true, color);
    }
}

package com.xp.exercise.statsbar;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.xp.exercise.R;
import com.xp.exercise.statsbar.base.StatusBarBaseActivity;

/**
 * @类描述：全屏 显示状态栏
 * @创建人：Wangxiaopan
 * @创建时间：2018/3/13 0013 16:13
 * @修改人：
 * @修改时间：2018/3/13 0013 16:13
 * @修改备注：
 */

public class FullScreenHaveStatusActivity extends StatusBarBaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fullscreen_havastatus);
        //配置在theme里面
    }
}

package com.xp.exercise.statsbar;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.xp.exercise.statsbar.base.StatusBarBaseActivity;

/**
 * @类描述：Fragment切换 变化标题栏
 * @创建人：Wangxiaopan
 * @创建时间：2018/3/16 0016 11:11
 * @修改人：
 * @修改时间：2018/3/16 0016 11:11
 * @修改备注：
 */

public class FragmentStatusbarActivity extends StatusBarBaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //本页面主要讲解在Fragment切换的时候状态栏的变化 因为前面已经讲了状态栏变化的两种适配方式
        //第一种 修改状态栏颜色 继承StatusBarBaseActivity 调用setColorStatusBar 即可
        //第二种 顶部添加View,通过修改View颜色 继承CompatStatusBarActivity 调用setViewColorStatusBar 即可

        //本地我们以第一种适配方案来写代码

    }
}

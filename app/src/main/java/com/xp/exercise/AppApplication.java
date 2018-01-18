package com.xp.exercise;

import android.app.Application;

import com.xp.exercise.util.LogUtils;

/**
 * @类描述：应用常量类
 * @创建人：Wangxiaopan
 * @创建时间：2018/1/17 0017 14:30
 * @修改人：
 * @修改时间：2018/1/17 0017 14:30
 * @修改备注：
 */

public class AppApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        CrashCapture.getInstance().init(this);
        LogUtils.init(this);
    }
}

package com.xp.exercise.toolbar;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.xp.exercise.R;
import com.xp.exercise.toolbar.util.StatusBarUtil;

/**
 * @类描述：类似知乎(上下两个标题栏)
 * @创建人：Wangxiaopan
 * @创建时间：2018/1/18 0018 14:46
 * @修改人：
 * @修改时间：2018/1/18 0018 14:46
 * @修改备注：
 */

public class MyBehaviorActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_behavior);
        StatusBarUtil.setStatusBarColor(MyBehaviorActivity.this, R.color.colorPrimaryDark);//设置状态栏颜色
        Toolbar toolbar = (Toolbar) findViewById(R.id.tb_my_behavior);
        setSupportActionBar(toolbar);
    }
}

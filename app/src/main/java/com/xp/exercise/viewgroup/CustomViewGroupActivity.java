package com.xp.exercise.viewgroup;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.xp.exercise.statsbar.base.StatusBarBaseActivity;
import com.xp.exercise.util.LogUtils;
import com.xp.exercise.viewgroup.view.CanvasViewGroup;

/**
 * @类描述：应用常量类
 * @创建人：Wangxiaopan
 * @创建时间：2018/8/24 0024 18:08
 * @修改人：
 * @修改时间：2018/8/24 0024 18:08
 * @修改备注：
 */
public class CustomViewGroupActivity extends StatusBarBaseActivity {
    public static final String EXTRA_VIEW_TYPE = "extra_view_type";
    public static final String EXTRA_VIEW_TYPE_CANVAS = "canvas";
    public String type;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtils.d("Test", "onCreate");
        type = getIntent().getStringExtra(EXTRA_VIEW_TYPE);
        View view = null;
        if (type.equals(EXTRA_VIEW_TYPE_CANVAS)) {
            view = new CanvasViewGroup(this);
        }
        initView(view);
    }

    @Override
    protected void onStart() {
        super.onStart();
        LogUtils.d("Test", "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        LogUtils.d("Test", "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        LogUtils.d("Test", "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        LogUtils.d("Test", "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LogUtils.d("Test", "onDestroy");
    }


    private void initView(View view) {
        setContentView(view);
    }
}

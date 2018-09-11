package com.xp.exercise.viewgroup;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.xp.exercise.R;
import com.xp.exercise.statsbar.base.CompatStatusBarActivity;
import com.xp.exercise.util.LogUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @类描述：应用常量类
 * @创建人：Wangxiaopan
 * @创建时间：2018/8/24 0024 17:58
 * @修改人：
 * @修改时间：2018/8/24 0024 17:58
 * @修改备注：
 */
public class MainViewGroupActivity extends CompatStatusBarActivity implements View.OnClickListener {
    @BindView(R.id.viewgroup_canvas)
    TextView tv_canvas;
    @BindView(R.id.viewgroup_draghelper)
    TextView tv_draghelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewgroup_main);
        LogUtils.d("Test", "onCreate");
        ButterKnife.bind(this);
        tv_canvas.setOnClickListener(this);
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

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        intent.setClass(this, CustomViewGroupActivity.class);
        switch (v.getId()) {
            case R.id.viewgroup_canvas:
                intent.putExtra(CustomViewGroupActivity.EXTRA_VIEW_TYPE, CustomViewGroupActivity.EXTRA_VIEW_TYPE_CANVAS);
                break;
        }
        startActivity(intent);
        finish();
    }
}

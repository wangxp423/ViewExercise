package com.xp.exercise.ontouch;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.xp.exercise.R;
import com.xp.exercise.statsbar.base.CompatStatusBarActivity;

/**
 * @类描述：应用常量类
 * @创建人：Wangxiaopan
 * @创建时间：2018/9/6 0006 18:24
 * @修改人：
 * @修改时间：2018/9/6 0006 18:24
 * @修改备注：
 */
public class MainTouchActivity extends CompatStatusBarActivity implements View.OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ontouch_activity_main);
        findViewById(R.id.ontouche_normal).setOnClickListener(this);
        findViewById(R.id.ontouche_recycler).setOnClickListener(this);
        findViewById(R.id.ontouche_scrollview).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ontouche_normal:
                startActivity(new Intent(MainTouchActivity.this, TouchNormalActivity.class));
                break;
            case R.id.ontouche_recycler:
                startActivity(new Intent(MainTouchActivity.this, TouchRecyclerActivity.class));
                break;
            case R.id.ontouche_scrollview:
                startActivity(new Intent(MainTouchActivity.this, TouchScrollViewActivity.class));
                break;
        }
    }
}

package com.xp.exercise.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.transition.Fade;
import android.transition.Slide;
import android.transition.TransitionManager;
import android.transition.TransitionSet;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xp.exercise.R;

/**
 * @类描述：两个activity过度
 * @创建人：Wangxiaopan
 * @创建时间：2018/1/24 0024 18:12
 * @修改人：
 * @修改时间：2018/1/24 0024 18:12
 * @修改备注：
 */

public class SwitchActivity extends AppCompatActivity implements View.OnClickListener {
    private LinearLayout llParentLayout;
    private TextView tvFirst, tvSecond;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_switch);
        initViews();
    }

    private void initViews() {
        llParentLayout = (LinearLayout) findViewById(R.id.ll_parent_layout);
        findViewById(R.id.btn_switch_anim).setOnClickListener(this);
        tvFirst = (TextView) findViewById(R.id.iv_switch_meinv);
        tvSecond = (TextView) findViewById(R.id.iv_switch_second);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_switch_anim:
                switchAnim();
                break;
            default:
                break;
        }
    }

    private void switchAnim() {
        Slide slide = new Slide(Gravity.RIGHT);
        slide.excludeTarget(tvFirst, true);
        Fade fade = new Fade();
        fade.excludeTarget(tvSecond, true);
        TransitionSet transitionSet = new TransitionSet()
                .addTransition(slide)
                .addTransition(fade);
        TransitionManager.beginDelayedTransition(llParentLayout, transitionSet);
        if (tvFirst.getVisibility() == View.VISIBLE) {
            tvFirst.setVisibility(View.GONE);
            tvSecond.setVisibility(View.GONE);
        } else {
            tvFirst.setVisibility(View.VISIBLE);
            tvSecond.setVisibility(View.VISIBLE);
        }
    }
}

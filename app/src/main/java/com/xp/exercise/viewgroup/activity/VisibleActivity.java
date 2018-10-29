package com.xp.exercise.viewgroup.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.transition.TransitionManager;
import android.util.Pair;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xp.exercise.R;

/**
 * @类描述：揭露效果 显示和隐藏
 * @创建人：Wangxiaopan
 * @创建时间：2018/1/24 0024 15:15
 * @修改人：
 * @修改时间：2018/1/24 0024 15:15
 * @修改备注：
 */

public class VisibleActivity extends AppCompatActivity implements View.OnClickListener {
    private LinearLayout llBtnLayout;
    private TextView ivMeinv, ivSecond, tvConver;
    private Button btnPathMotion;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getWindow().requestFeature(Window.FEATURE_ACTIVITY_TRANSITIONS);
//        getWindow().setExitTransition(new Explode());
//        getWindow().setEnterTransition(new Explode());
        setContentView(R.layout.activity_visible_invisible);
        initViews();
        changeBounds();
    }

    private void initViews() {
        llBtnLayout = (LinearLayout) findViewById(R.id.ll_btn_layout);
        findViewById(R.id.btn_visible_visible).setOnClickListener(this);
        findViewById(R.id.btn_visible_invisible).setOnClickListener(this);
        btnPathMotion = (Button) findViewById(R.id.btn_visible_pathmotion);
        btnPathMotion.setOnClickListener(this);
        ivMeinv = (TextView) findViewById(R.id.iv_visible_meinv);
        ivMeinv.setOnClickListener(this);
        ivSecond = (TextView) findViewById(R.id.iv_visible_second);
        ivSecond.setOnClickListener(this);
        tvConver = (TextView) findViewById(R.id.iv_visible_cover_anim);
        tvConver.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_visible_visible:
                visible();
                break;
            case R.id.btn_visible_invisible:
                invisible();
                break;
            case R.id.iv_visible_meinv:
                Intent intent = new Intent(this, SwitchActivity.class);
                //启动一个View变化
                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this, ivMeinv, "sharedView").toBundle());
                Pair first = new Pair<>(ivMeinv, ViewCompat.getTransitionName(ivMeinv));
                Pair second = new Pair<>(ivSecond, ViewCompat.getTransitionName(ivSecond));
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this, first, second);
                startActivity(intent, options.toBundle());
                break;
            case R.id.iv_visible_cover_anim:
                converAnim();
                break;
            case R.id.btn_visible_pathmotion:
                Intent intent1 = new Intent(this, PathMotionActivity.class);
                startActivity(intent1, ActivityOptions.makeSceneTransitionAnimation(this, btnPathMotion, "pathMotion").toBundle());
                break;
            default:
                break;
        }
    }

    private void visible() {
        int cx = (ivMeinv.getLeft() + ivMeinv.getRight()) / 2;
        int cy = (ivMeinv.getTop() + ivMeinv.getBottom()) / 2;
        int initialRadius = ivMeinv.getWidth() + ivMeinv.getHeight();
        Animator anim = ViewAnimationUtils.createCircularReveal(ivMeinv, cx, cy, 0, initialRadius);
        ivMeinv.setVisibility(View.VISIBLE);
        anim.start();
    }

    private void invisible() {
        int cx = (ivMeinv.getLeft() + ivMeinv.getRight()) / 2;
        int cy = (ivMeinv.getTop() + ivMeinv.getBottom()) / 2;
        int initialRadius = ivMeinv.getWidth();
        Animator anim = ViewAnimationUtils.createCircularReveal(ivMeinv, cx, cy, initialRadius, 0);
        anim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                ivMeinv.setVisibility(View.INVISIBLE);
            }
        });
        anim.start();
    }

    private boolean isConver = false;

    private void converAnim() {
        final int blue = getResources().getColor(R.color.tv_tips_blue);
        final int red = getResources().getColor(R.color.tv_tips_red);
        int cx = tvConver.getLeft();
        int cy = tvConver.getTop();
        int initialRadius = tvConver.getWidth() + tvConver.getHeight();
        Animator anim = ViewAnimationUtils.createCircularReveal(tvConver, cx, cy, initialRadius, 0);
        anim.setDuration(500);
        anim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                if (!isConver) {
                    ivSecond.setBackgroundColor(blue);
                    tvConver.setBackgroundColor(red);
                } else {
                    ivSecond.setBackgroundColor(red);
                    tvConver.setBackgroundColor(blue);
                }
                isConver = !isConver;
            }
        });
        anim.start();
    }

    private void changeBounds() {
        Transition transition = TransitionInflater.from(this).inflateTransition(R.transition.trans_bounds);
        TransitionManager.beginDelayedTransition(llBtnLayout, transition);
    }
}

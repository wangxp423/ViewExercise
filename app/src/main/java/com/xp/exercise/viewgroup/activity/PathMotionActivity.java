package com.xp.exercise.viewgroup.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.transition.ArcMotion;
import android.transition.ChangeBounds;
import android.transition.TransitionManager;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.xp.exercise.R;

/**
 * @类描述：路径过度动画
 * @创建人：Wangxiaopan
 * @创建时间：2018/1/26 0026 11:16
 * @修改人：
 * @修改时间：2018/1/26 0026 11:16
 * @修改备注：
 */

public class PathMotionActivity extends AppCompatActivity implements View.OnClickListener {
    private FrameLayout flPathLayout;
    private Button btnPathMotion;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pathmotion);
        initViews();
    }

    private void initViews() {
        flPathLayout = (FrameLayout) findViewById(R.id.fl_pathmotion_layout);
        btnPathMotion = (Button) findViewById(R.id.btn_pathmotion_btn);
        btnPathMotion.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_pathmotion_btn:
                setPathMotion();
                break;
            default:
                break;
        }
    }

    private boolean isPath;

    private void setPathMotion() {
        ChangeBounds changeBounds = new ChangeBounds();
        //或者使用PathMotion 重写Path 调用Path.cubicTo划一个弧度
        ArcMotion arcMotion = new ArcMotion();
//        arcMotion.setMaximumAngle(90f);
//        arcMotion.setMinimumVerticalAngle(90f);
        arcMotion.setMinimumHorizontalAngle(90F);
        changeBounds.setPathMotion(arcMotion);
        changeBounds.setDuration(1000);
        TransitionManager.beginDelayedTransition(flPathLayout, changeBounds);
        FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) btnPathMotion.getLayoutParams();
        params.gravity = isPath ? Gravity.TOP | Gravity.LEFT : Gravity.BOTTOM | Gravity.RIGHT;
        btnPathMotion.setLayoutParams(params);
        isPath = !isPath;
    }
}

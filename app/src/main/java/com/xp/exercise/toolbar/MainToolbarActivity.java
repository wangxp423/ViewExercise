package com.xp.exercise.toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.xp.exercise.R;

/**
 * @类描述：ToolBar
 * @创建人：Wangxiaopan
 * @创建时间：2018/1/16 0016 16:20
 * @修改人：
 * @修改时间：2018/1/16 0016 16:20
 * @修改备注：
 */

public class MainToolbarActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_toolbar);
        findViewById(R.id.btn_collapsing_toolbar).setOnClickListener(this);
        findViewById(R.id.btn_collapsing_toolbar_collaps).setOnClickListener(this);
        findViewById(R.id.btn_my_behavior).setOnClickListener(this);
        findViewById(R.id.btn_bottom_sheet).setOnClickListener(this);
        findViewById(R.id.btn_title_image).setOnClickListener(this);
        findViewById(R.id.btn_coordinator_scroll).setOnClickListener(this);
        findViewById(R.id.btn_coordinator_scroll_enteralways).setOnClickListener(this);
        findViewById(R.id.btn_coordinator_scroll_enteralways_collapsed).setOnClickListener(this);
        findViewById(R.id.btn_coordinator_scroll_exituntilcollapsed).setOnClickListener(this);
        findViewById(R.id.btn_coordinator_scroll_span).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.btn_collapsing_toolbar:
                intent.setClass(this, CollapsingToolbarActivity.class);
                break;
            case R.id.btn_collapsing_toolbar_collaps:
                intent.setClass(this, CollapsingToolbarSnapActivity.class);
                break;
            case R.id.btn_my_behavior:
                intent.setClass(this, MyBehaviorActivity.class);
                break;
            case R.id.btn_bottom_sheet:
                intent.setClass(this, BottomSheetActivity.class);
                break;
            case R.id.btn_title_image:
                intent.setClass(this, TitleImageBehaviorActivity.class);
                break;
            case R.id.btn_coordinator_scroll:
                intent.setClass(this, CoordinatorLayoutActivity.class);
                intent.putExtra(CoordinatorLayoutActivity.EXTRA_SCROLL_FLAGS, CoordinatorLayoutActivity.EXTRA_SCROLL);
                break;
            case R.id.btn_coordinator_scroll_enteralways:
                intent.setClass(this, CoordinatorLayoutActivity.class);
                intent.putExtra(CoordinatorLayoutActivity.EXTRA_SCROLL_FLAGS, CoordinatorLayoutActivity.EXTRA_SCROLL_ENTERALWAYS);
                break;
            case R.id.btn_coordinator_scroll_enteralways_collapsed:
                intent.setClass(this, CoordinatorLayoutActivity.class);
                intent.putExtra(CoordinatorLayoutActivity.EXTRA_SCROLL_FLAGS, CoordinatorLayoutActivity.EXTRA_SCROLL_ENTERALWAYS_ENTERALWAYSCOLLPASED);
                break;
            case R.id.btn_coordinator_scroll_exituntilcollapsed:
                intent.setClass(this, CoordinatorLayoutActivity.class);
                intent.putExtra(CoordinatorLayoutActivity.EXTRA_SCROLL_FLAGS, CoordinatorLayoutActivity.EXTRA_SCROLL_EXITUNTILCOLLAPSED);
                break;
            case R.id.btn_coordinator_scroll_span:
                intent.setClass(this, CoordinatorLayoutActivity.class);
                intent.putExtra(CoordinatorLayoutActivity.EXTRA_SCROLL_FLAGS, CoordinatorLayoutActivity.EXTRA_SCROLL_SPAN);
                break;
            default:
                break;
        }
        startActivity(intent);
    }
}

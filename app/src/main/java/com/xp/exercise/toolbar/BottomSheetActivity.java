package com.xp.exercise.toolbar;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xp.exercise.R;
import com.xp.exercise.toolbar.util.StatusBarUtil;
import com.xp.exercise.util.LogUtils;

/**
 * @类描述：底部拉伸展现效果
 * @创建人：Wangxiaopan
 * @创建时间：2018/1/18 0018 15:29
 * @修改人：
 * @修改时间：2018/1/18 0018 15:29
 * @修改备注：
 */

public class BottomSheetActivity extends AppCompatActivity {
    private boolean isSetBottomSheetHeight;
    private BottomSheetBehavior mBottomBehavior;
    private RelativeLayout rlBottomView, rlTopTitle;
    private ImageView ivBottom;
    private TextView tvBottomTitle;
    private CoordinatorLayout coordinatorLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_sheet);
        StatusBarUtil.setStatusBarColor(BottomSheetActivity.this, R.color.colorPrimaryDark);
        initViews();
        setListener();
    }

    private void initViews() {
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.cl_parent_layout);
        rlTopTitle = (RelativeLayout) findViewById(R.id.rl_bottom_sheet_top_title);
        rlBottomView = (RelativeLayout) findViewById(R.id.rl_bottom_sheet_parent_layout);
        ivBottom = (ImageView) findViewById(R.id.iv_bottom_sheet_bottom_content);
        tvBottomTitle = (TextView) findViewById(R.id.tv_bottom_sheet_bottom_title);
        mBottomBehavior = BottomSheetBehavior.from(rlBottomView);
    }

    private void setListener() {
        mBottomBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                LogUtils.d("Test", "status = " + newState);
                if (newState != BottomSheetBehavior.STATE_COLLAPSED && tvBottomTitle.getVisibility() == View.VISIBLE) {
                    tvBottomTitle.setVisibility(View.GONE);
                    ivBottom.setVisibility(View.VISIBLE);
                } else if (newState == BottomSheetBehavior.STATE_COLLAPSED && tvBottomTitle.getVisibility() == View.GONE) {
                    tvBottomTitle.setVisibility(View.VISIBLE);
                    ivBottom.setVisibility(View.GONE);
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
                if (bottomSheet.getTop() < 2 * rlTopTitle.getHeight()) {
                    //设置底部完全展开的时候 出现顶部工具栏的动画{
                    rlTopTitle.setVisibility(View.VISIBLE);
                    rlTopTitle.setAlpha(slideOffset);
                    rlTopTitle.setTranslationY(bottomSheet.getTop() - 2 * rlTopTitle.getHeight());
                } else {
                    rlTopTitle.setVisibility(View.INVISIBLE);
                }
            }
        });
        rlTopTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBottomBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
            }
        });
        tvBottomTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBottomBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
            }
        });
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        //修改SetBottomSheet的高度 留出顶部工具栏的位置
        if (!isSetBottomSheetHeight) {
            CoordinatorLayout.LayoutParams linearParams = (CoordinatorLayout.LayoutParams) rlBottomView.getLayoutParams();
            linearParams.height = coordinatorLayout.getHeight() - rlTopTitle.getHeight();
            rlBottomView.setLayoutParams(linearParams);
            isSetBottomSheetHeight = true;
        }
    }
}

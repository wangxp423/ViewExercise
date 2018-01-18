package com.xp.exercise.toolbar;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.AlphaAnimation;

import com.xp.exercise.R;

/**
 * @类描述：折叠标题栏(可自动折叠)
 * @创建人：Wangxiaopan
 * @创建时间：2018/1/16 0016 16:46
 * @修改人：
 * @修改时间：2018/1/16 0016 16:46
 * @修改备注：
 */

public class CollapsingToolbarSnapActivity extends AppCompatActivity {
    // 控制ToolBar的变量

    private static final int ALPHA_ANIMATIONS_DURATION = 350;

    private boolean mIsTheTitleVisible = false;
    private AppBarLayout ablLayout;
    private CollapsingToolbarLayout ctbLayout;
    private Toolbar tbLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collpasing_snap_toolbar);
        initViews();
    }

    private void initViews() {
        ablLayout = (AppBarLayout) findViewById(R.id.abl_collapsing_snap_appbar);
        tbLayout = (Toolbar) findViewById(R.id.tb_collapsing_snap);
        ctbLayout = (CollapsingToolbarLayout) findViewById(R.id.ctb_collapsing_snap_layout);
//        //设置ToolBar
        tbLayout.setTitle("标题");
        setSupportActionBar(tbLayout);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        tbLayout.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        //Toolbar 在 CollapsingToolbar里面 设置外层ToolBar(下面的)  不然直接设备Toolbar(上面的)
//        //设置CollapsingToolbarLayout
//        ctbLayout.setTitle("CollapsingToolbarLayout");
//        //通过CollapsingToolbarLayout修改字体颜色
//        ctbLayout.setExpandedTitleColor(Color.WHITE);
//        ctbLayout.setCollapsedTitleTextColor(Color.RED);
        ablLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                int maxScroll = appBarLayout.getTotalScrollRange();
                float percentage = (float) Math.abs(verticalOffset) / (float) maxScroll;
                handleToolbarTitleVisibility(percentage);
            }
        });
    }

    private void handleToolbarTitleVisibility(float percentage) {
        if (percentage >= 0.9) {
            if (!mIsTheTitleVisible) {
                startAlphaAnimation(tbLayout, ALPHA_ANIMATIONS_DURATION, View.VISIBLE);
                mIsTheTitleVisible = true;
            }
        } else {
            if (mIsTheTitleVisible) {
                startAlphaAnimation(tbLayout, ALPHA_ANIMATIONS_DURATION, View.INVISIBLE);
                mIsTheTitleVisible = false;
            }
        }
    }

    // 设置渐变的动画 这个动画要是根据距离滑动 然后渐变就更好了。
    public static void startAlphaAnimation(View v, long duration, int visibility) {
        AlphaAnimation alphaAnimation = (visibility == View.VISIBLE)
                ? new AlphaAnimation(0f, 1f)
                : new AlphaAnimation(1f, 0f);

        alphaAnimation.setDuration(duration);
        alphaAnimation.setFillAfter(true);
        v.startAnimation(alphaAnimation);
    }

}

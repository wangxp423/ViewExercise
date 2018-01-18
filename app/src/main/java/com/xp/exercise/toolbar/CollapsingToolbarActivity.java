package com.xp.exercise.toolbar;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.xp.exercise.R;

/**
 * @类描述：简单折叠标题栏(不能自动折叠(到一定位置))
 * @创建人：Wangxiaopan
 * @创建时间：2018/1/16 0016 16:46
 * @修改人：
 * @修改时间：2018/1/16 0016 16:46
 * @修改备注：
 */

public class CollapsingToolbarActivity extends AppCompatActivity {
    private ImageView ivBigImg;
    private AppBarLayout ablLayout;
    private CollapsingToolbarLayout ctbLayout;
    private Toolbar tbLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collpasing_toolbar);
        initViews();
    }

    private void initViews() {
        ivBigImg = (ImageView) findViewById(R.id.iv_collapsing_meinv);
        ablLayout = (AppBarLayout) findViewById(R.id.abl_collapsing_appbar);
        tbLayout = (Toolbar) findViewById(R.id.tb_collapse);
        ctbLayout = (CollapsingToolbarLayout) findViewById(R.id.ctb_collapsing_layout);
        //设置ToolBar
        setSupportActionBar(tbLayout);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        tbLayout.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        //设置CollapsingToolbarLayout
        ctbLayout.setTitle("CollapsingToolbarLayout");
        //通过CollapsingToolbarLayout修改字体颜色
        ctbLayout.setExpandedTitleColor(Color.WHITE);
        ctbLayout.setCollapsedTitleTextColor(Color.RED);
    }

}

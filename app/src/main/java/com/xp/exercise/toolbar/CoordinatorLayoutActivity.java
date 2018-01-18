package com.xp.exercise.toolbar;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;

import com.xp.exercise.R;

/**
 * @类描述：CoordinatorLayout 使用
 * @创建人：Wangxiaopan
 * @创建时间：2018/1/17 0017 9:58
 * @修改人：
 * @修改时间：2018/1/17 0017 9:58
 * @修改备注：
 */

public class CoordinatorLayoutActivity extends AppCompatActivity {
    private AppBarLayout ablLayout;
    private Toolbar tbLayout;

    public final static String EXTRA_SCROLL_FLAGS = "extra_scroll_flags";
    public final static String EXTRA_SCROLL = "extra_scroll";
    public final static String EXTRA_SCROLL_ENTERALWAYS = "extra_scroll_enteralways";
    public final static String EXTRA_SCROLL_ENTERALWAYS_ENTERALWAYSCOLLPASED = "extra_scroll_enteralways_enteralwayscollpased";
    public final static String EXTRA_SCROLL_EXITUNTILCOLLAPSED = "extra_scroll_exituntilcollapsed";
    public final static String EXTRA_SCROLL_SPAN = "extra_scroll_span";
    private String mScrollFlag;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coordinator_layout);
        mScrollFlag = getIntent().getStringExtra(EXTRA_SCROLL_FLAGS);
        if (TextUtils.isEmpty(mScrollFlag)) {
            mScrollFlag = EXTRA_SCROLL;
        }
        initViews();
    }

    private void initViews() {
        ablLayout = (AppBarLayout) findViewById(R.id.abl_layout_coordinator);
        tbLayout = (Toolbar) findViewById(R.id.tb_layout_coordinator);
        AppBarLayout.LayoutParams params = (AppBarLayout.LayoutParams) tbLayout.getLayoutParams();
        switch (mScrollFlag) {
            case EXTRA_SCROLL: //标题栏随着 ScrollView 滚出滚进
                params.setScrollFlags(AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL);
                break;
            case EXTRA_SCROLL_ENTERALWAYS: //发生向下滚动的时候优先滚动子View(ToolBar(标题栏))
                params.setScrollFlags(AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL | AppBarLayout.LayoutParams.SCROLL_FLAG_ENTER_ALWAYS);
                break;
            case EXTRA_SCROLL_ENTERALWAYS_ENTERALWAYSCOLLPASED: //发生向下滚动的时候优先滚动子View(ToolBar(标题栏))到设置的minHeigh高度，当ScrooView向下滚动完毕，在展开全部高度的子View
                params.setScrollFlags(AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL | AppBarLayout.LayoutParams.SCROLL_FLAG_ENTER_ALWAYS
                        | AppBarLayout.LayoutParams.SCROLL_FLAG_ENTER_ALWAYS_COLLAPSED);
                break;
            case EXTRA_SCROLL_EXITUNTILCOLLAPSED: //发生向上滚动的时候优先滚动子View(ToolBar(标题栏))到设置的minHeigh高度，当ScrooView向下滚动完毕，在展开全部高度的子View
                params.setScrollFlags(AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL | AppBarLayout.LayoutParams.SCROLL_FLAG_EXIT_UNTIL_COLLAPSED);
                break;
            case EXTRA_SCROLL_SPAN: //就是子View的一个吸附效果，子View不会存在局部显示的情况，滚动子View的部分高度，当松开手指子View要么向上全部滚出屏幕，要么向下全部滚进屏幕
                params.setScrollFlags(AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL | AppBarLayout.LayoutParams.SCROLL_FLAG_SNAP);
                break;
            default:
                break;
        }
    }
}

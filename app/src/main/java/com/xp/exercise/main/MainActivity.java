package com.xp.exercise.main;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;

import com.google.gson.Gson;
import com.xp.exercise.Constant;
import com.xp.exercise.R;
import com.xp.exercise.canvas.CanvasActivity;
import com.xp.exercise.canvas.secoo.SpeedActivity;
import com.xp.exercise.canvas.widget.CompassActivity;
import com.xp.exercise.listview.PullToChangeHeaderActivity;
import com.xp.exercise.listview.SlowScrollListViewActivity;
import com.xp.exercise.ontouch.TouchNormalActivity;
import com.xp.exercise.ontouch.TouchRecyclerActivity;
import com.xp.exercise.ontouch.TouchScrollViewActivity;
import com.xp.exercise.statsbar.FragmentStatusbarActivity;
import com.xp.exercise.statsbar.FullScreenHaveStatusActivity;
import com.xp.exercise.statsbar.StatusBarTextColor2Activity;
import com.xp.exercise.statsbar.StatusBarTextColorActivity;
import com.xp.exercise.statsbar.base.CompatStatusBarActivity;
import com.xp.exercise.toolbar.BottomSheetActivity;
import com.xp.exercise.toolbar.CollapsingToolbarActivity;
import com.xp.exercise.toolbar.CollapsingToolbarSnapActivity;
import com.xp.exercise.toolbar.CoordinatorLayoutActivity;
import com.xp.exercise.toolbar.MyBehaviorActivity;
import com.xp.exercise.toolbar.TitleImageBehaviorActivity;
import com.xp.exercise.util.AssetsUtil;
import com.xp.exercise.util.ToastUtils;
import com.xp.exercise.viewgroup.activity.CustomViewGroupActivity;
import com.xp.exercise.viewgroup.activity.ScrollerTestActivity;
import com.xp.exercise.viewgroup.activity.VisibleActivity;
import com.xp.exercise.viewpager.IndicatorViewPagerActivity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends CompatStatusBarActivity {

    @BindView(R.id.main_elv)
    ExpandableListView mainElv;
    private ArrayList<MainListEntity.LevelOneEntity> datas;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        datas = initData();
        MainMenuAdapter adapter = new MainMenuAdapter(this, mainElv, datas);
        mainElv.setAdapter(adapter);

        mainElv.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                parentLevelOneCategory(datas.get(groupPosition), childPosition);
                return false;
            }
        });
    }

    //一级分类判断
    private void parentLevelOneCategory(MainListEntity.LevelOneEntity entity, int childPosition) {
        String tag = entity.getTag();
        MainListEntity.LevelTwoEntity twoEntity = entity.getChilds().get(childPosition);
        switch (tag) {
            case Constant.PARENT_ITEM_CUSTOM_DRAW:
                //自定义View-onDraw()细分
                childOnDrawCategory(twoEntity);
                break;
            case Constant.PARENT_ITEM_TOOLBAR:
                childToolbarCategory(twoEntity);
                break;
            case Constant.PARENT_ITEM_STATUSBAR:
                childStatusbarCategory(twoEntity);
                break;
            case Constant.PARENT_ITEM_TOUCH:
                childTouchCategory(twoEntity);
                break;
            case Constant.PARENT_ITEM_LISTVIEW:
                childListViewCategory(twoEntity);
                break;
            case Constant.PARENT_ITEM_ANIMATOR:
                childAnimatorCategory(twoEntity);
                break;
            case Constant.PARENT_ITEM_VIEWGROUP:
                childViewgroupCategory(twoEntity);
                break;
            case Constant.PARENT_ITEM_VIEWPAGER:
                childViewpagerCategory(twoEntity);
                break;
            default:
                break;
        }

    }

    //自定义View之onDraw细分
    private void childOnDrawCategory(MainListEntity.LevelTwoEntity entity) {
        String tag = entity.getTag();
        switch (tag) {
            case Constant.CUSTOM_VIEW_DRAW_SECOO:
                startActivity(new Intent(this, SpeedActivity.class));
                break;
            case Constant.CUSTOM_VIEW_DRAW_COMPASS:
                startActivity(new Intent(this, CompassActivity.class));
                break;
            default:
                Intent intent = new Intent(this, CanvasActivity.class);
                intent.putExtra(Constant.EXTRA_CUSTOM_VIEW_TAG, tag);
                startActivity(intent);
                break;
        }

    }

    //Toolbar配合CoordinatorLayout二级分类
    private void childToolbarCategory(MainListEntity.LevelTwoEntity entity) {
        String tag = entity.getTag();
        switch (tag) {
            case Constant.TOOLBAR_COLLAPSING_TOOLBAR:
                startActivity(new Intent(this, CollapsingToolbarActivity.class));
                break;
            case Constant.TOOLBAR_COLLAPSING_TOOLBAR_COLLAPS:
                startActivity(new Intent(this, CollapsingToolbarSnapActivity.class));
                break;
            case Constant.TOOLBAR_BEHAVIOR_ZHIHU:
                startActivity(new Intent(this, MyBehaviorActivity.class));
                break;
            case Constant.TOOLBAR_BEHAVIOR_BAIDU:
                startActivity(new Intent(this, BottomSheetActivity.class));
                break;
            case Constant.TOOLBAR_CUSTOM_BEHAVIOR_COLLECTION:
                startActivity(new Intent(this, TitleImageBehaviorActivity.class));
                break;
            default:
                Intent intent = new Intent(this, CoordinatorLayoutActivity.class);
                intent.putExtra(Constant.EXTRA_SCROLL_FLAGS, tag);
                startActivity(intent);
                break;
        }
    }

    //statusBar沉浸式状态栏 二级分类
    private void childStatusbarCategory(MainListEntity.LevelTwoEntity entity) {
        String tag = entity.getTag();
        switch (tag) {
            case Constant.STATUSBAR_FULL_HIDE:
                ToastUtils.showShortToast("参考启动页(WelcomeActivity配置)");
                break;
            case Constant.STATUSBAR_FULL_SHOW:
                startActivity(new Intent(this, FullScreenHaveStatusActivity.class));
                break;
            case Constant.STATUSBAR_SAME_TITLE_STATUS:
                ToastUtils.showShortToast("参考StatusBarBaseActivity配置");
                break;
            case Constant.STATUSBAR_CHANGE_TEXT_COLOR:
                startActivity(new Intent(this, StatusBarTextColorActivity.class));
                break;
            case Constant.STATUSBAR_CHANGE_TEXT_COLOR2:
                startActivity(new Intent(this, StatusBarTextColor2Activity.class));
                break;
            case Constant.STATUSBAR_WITH_FRAGMENT:
                startActivity(new Intent(this, FragmentStatusbarActivity.class));
                break;
            default:
                break;
        }

    }

    //touch 事件传递 二级分类
    private void childTouchCategory(MainListEntity.LevelTwoEntity entity) {
        String tag = entity.getTag();
        switch (tag) {
            case Constant.TOUCH_NORMAL:
                startActivity(new Intent(this, TouchNormalActivity.class));
                break;
            case Constant.TOUCH_RECYCLER:
                startActivity(new Intent(this, TouchRecyclerActivity.class));
                break;
            case Constant.TOUCH_SCROLLVIEW:
                startActivity(new Intent(this, TouchScrollViewActivity.class));
                break;
            default:
                break;
        }

    }

    //ListView 效果研究
    private void childListViewCategory(MainListEntity.LevelTwoEntity entity) {
        String tag = entity.getTag();
        switch (tag) {
            case Constant.LISTVIEW_PULL_HEADER:
                startActivity(new Intent(this, PullToChangeHeaderActivity.class));
                break;
            case Constant.LISTVIEW_ITEM_SLOW:
                startActivity(new Intent(this, SlowScrollListViewActivity.class));
                break;
            default:
                break;
        }

    }

    //Animator 动画效果研究
    private void childAnimatorCategory(MainListEntity.LevelTwoEntity entity) {
        String tag = entity.getTag();
        switch (tag) {
            case Constant.ANIMATOR_SCENE:
                Intent intent = new Intent(this, VisibleActivity.class);
                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
                break;
            default:
                break;
        }

    }

    //ViewGroup 自定义效果研究
    private void childViewgroupCategory(MainListEntity.LevelTwoEntity entity) {
        String tag = entity.getTag();
        switch (tag) {
            case Constant.VIEWGROUP_TEST_SCROLL:
                startActivity(new Intent(this, ScrollerTestActivity.class));
                break;
            case Constant.VIEWGROUP_VIEW_DRAGHELPER:
                startActivity(new Intent(this, CustomViewGroupActivity.class));
                break;
            default:
                break;
        }
    }

    //ViewGroup 自定义效果研究
    private void childViewpagerCategory(MainListEntity.LevelTwoEntity entity) {
        String tag = entity.getTag();
        switch (tag) {
            case Constant.VIEWPAGER_INDICATE:
                startActivity(new Intent(this, IndicatorViewPagerActivity.class));
                break;
            default:
                break;
        }
    }


    private ArrayList<MainListEntity.LevelOneEntity> initData() {
        String json = AssetsUtil.getFromAssets(this, "main_menu_list.json");
        MainListEntity entity = new Gson().fromJson(json, MainListEntity.class);
        return entity.getDatas();
    }
}

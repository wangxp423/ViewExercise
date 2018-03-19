package com.xp.exercise.statsbar;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.xp.exercise.R;
import com.xp.exercise.statsbar.base.CompatStatusBarActivity;
import com.xp.exercise.statsbar.fragment.TitleBlueFragment;
import com.xp.exercise.statsbar.fragment.TitleImageFragment;
import com.xp.exercise.statsbar.fragment.TitleRedFragment;
import com.xp.exercise.statsbar.fragment.TitleWhiteFragment;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @类描述：Fragment切换 变化标题栏
 * @创建人：Wangxiaopan
 * @创建时间：2018/3/16 0016 11:11
 * @修改人：
 * @修改时间：2018/3/16 0016 11:11
 * @修改备注：
 */

public class FragmentStatusbarActivity extends CompatStatusBarActivity {
    private TitleBlueFragment mTitleBlueFragment;
    private TitleRedFragment mTitleRedFragment;
    private TitleWhiteFragment mTitleWhiteFragment;
    private TitleImageFragment mTitleImageFragment;
    private ArrayList<Fragment> mFragments;
    private FragmentManager mSupportFragmentManager;
    private FragmentTransaction mFragmentTransaction;
    private Fragment mLastFragment;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //本页面主要讲解在Fragment切换的时候状态栏的变化 因为前面已经讲了状态栏变化的两种适配方式
        //第一种 修改状态栏颜色 继承StatusBarBaseActivity 调用setColorStatusBar 即可
        //第二种 顶部添加View,通过修改View颜色 继承CompatStatusBarActivity 调用setViewColorStatusBar 即可

        //本来想以第一种方式来写，以为会比较简单，结果遇到了诸多问题。简单说一下问题吧。
        //第一种方式修改状态栏颜色，主要配合的是fitSystemWindow属性。他作用于布局文件中的第一个View中，如果后面的View也设置这个属性是无效的。
        //用这个属性在Fragment切换的时候有诸多弊端，下面简单介绍一下
        //1，如果fitSystemWindow设置在Fragment里面的第一个View中，在切换的时候发现只有第一个生效，后面的fragment不生效。
        //2，如果设置fitSystemWindow在Activity里面的第一个View,切换的时候我们需要设置第一个View，即标题栏的背景色，这个其实无可厚非，我们写这个的前提是标题栏在Fragment的布局文件中
        //3，如果从有标题栏切换到无标题栏，即顶端是大图的情况，不需要有fitSystemWindow的属性，即该属性需要是false。
        //综上所述，用第一种方案来适配  繁琐代码量还多。
        //如果用第一种方案进行适配，最好是标题栏在外部Activity里面，只需要控制标题栏的颜色和显示隐藏

        //我们就用第二种适配方案进行适配吧。
        setContentView(R.layout.activity_fragment_statusbar);
        ButterKnife.bind(this);
        mTitleBlueFragment = new TitleBlueFragment();
        mTitleRedFragment = new TitleRedFragment();
        mTitleWhiteFragment = new TitleWhiteFragment();
        mTitleImageFragment = new TitleImageFragment();
        mFragments = new ArrayList<>();
        mFragments.add(mTitleBlueFragment);
        mFragments.add(mTitleRedFragment);
        mFragments.add(mTitleWhiteFragment);
        mFragments.add(mTitleImageFragment);
        mSupportFragmentManager = getSupportFragmentManager();
        //默认显示第一个
        setViewColorStatusBar(false, getResources().getColor(R.color.colorPrimary));
        selectFragment(0);
    }

    @OnClick(R.id.fragment_title_blue)
    public void changeTitleBlue() {
        setStatusBarPlaceVisible(true);
        setViewColorStatusBar(false, getResources().getColor(R.color.colorPrimary));
        selectFragment(0);
    }

    @OnClick(R.id.fragment_title_red)
    public void changeTitleRed() {
        setStatusBarPlaceVisible(true);
        setViewColorStatusBar(false, getResources().getColor(R.color.colorAccent));
        selectFragment(1);
    }

    @OnClick(R.id.fragment_title_white)
    public void changeTitleWhite() {
        setStatusBarPlaceVisible(true);
        setViewColorStatusBar(true, Color.WHITE);
        selectFragment(2);
    }

    @OnClick(R.id.fragment_title_image)
    public void changeTitleImage() {
        setStatusBarPlaceVisible(false);
        setViewColorStatusBar(false, Color.WHITE);
        selectFragment(3);
    }

    private void selectFragment(int index) {
        if (mFragments != null && mFragments.size() > 0) {
            mFragmentTransaction = mSupportFragmentManager.beginTransaction();
            Fragment baseFragment = mFragments.get(index);
            if (mLastFragment != null) {
                mFragmentTransaction.hide(mLastFragment);
            }
            if (mFragmentTransaction != null) {
                if (baseFragment.isAdded()) {
                    mFragmentTransaction.show(baseFragment);
                } else {
                    mFragmentTransaction.add(R.id.fragment_statusbar_content, baseFragment);
                }
            }
            mFragmentTransaction.commitAllowingStateLoss();
            mLastFragment = baseFragment;
        }
    }
}

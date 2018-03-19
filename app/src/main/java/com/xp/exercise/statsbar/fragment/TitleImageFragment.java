package com.xp.exercise.statsbar.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xp.exercise.R;

/**
 * @类描述：状态栏切换fragment
 * @创建人：Wangxiaopan
 * @创建时间：2018/3/16 0016 14:15
 * @修改人：
 * @修改时间：2018/3/16 0016 14:15
 * @修改备注：
 */

public class TitleImageFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_fullscreen_havastatus, null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}

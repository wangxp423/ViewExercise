package com.xp.exercise.statsbar.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xp.exercise.R;

/**
 * @类描述：状态栏切换fragment
 * @创建人：Wangxiaopan
 * @创建时间：2018/3/16 0016 14:15
 * @修改人：
 * @修改时间：2018/3/16 0016 14:15
 * @修改备注：
 */

public class TitleWhiteFragment extends Fragment {
    private ViewGroup mTitleLayout;
    private TextView mTitleText, mTextContent;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_title_change, null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews(view);
    }

    private void initViews(View view) {
        mTitleLayout = (ViewGroup) view.findViewById(R.id.fragment_title_bg_layout);
        mTitleText = (TextView) view.findViewById(R.id.fragment_title_text);
        mTitleLayout.setBackgroundColor(getResources().getColor(R.color.white));
        mTitleText.setTextColor(getResources().getColor(R.color.tv_tips1));
        mTextContent = (TextView) view.findViewById(R.id.fragment_content);
    }
}

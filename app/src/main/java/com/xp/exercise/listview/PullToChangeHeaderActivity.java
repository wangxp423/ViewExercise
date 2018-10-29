package com.xp.exercise.listview;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.xp.exercise.R;
import com.xp.exercise.listview.widget.ListViewForScrollView;
import com.xp.exercise.listview.widget.ListViewForScrollView.OnMyListViewScrollListener;

import java.util.ArrayList;

/**
 * 
 * Function: 下拉ListView header变大
 *
 * @author wangxiaopan
 * @Date 2016-05-50
 */
public class PullToChangeHeaderActivity extends FragmentActivity implements OnMyListViewScrollListener,AbsListView.OnScrollListener {
    private ListViewForScrollView mListView;
    private ImageView mHeaderImage;
    private boolean mIsTop = false;
    private int mOriginHeight;
    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_pull_to_change);
        initUI();
    }
    
    private void initUI(){
        mListView = (ListViewForScrollView) findViewById(R.id.pull_to_change_listview);
        mListView.setOnScrollListener(this);
        mListView.setMyScrollListener(this);
        View header = View.inflate(this, R.layout.listview_header_image, null);
        mHeaderImage = (ImageView) header.findViewById(R.id.header_image);
        mListView.addHeaderView(header);
        ArrayList<String> mData = new ArrayList<String>();
        mData.add("String");
        mData.add("Integer");
        mData.add("long");
        mData.add("float");
        mData.add("double");
        mData.add("char");
        mData.add("boolean");
        mData.add("byte");
        mData.add("short");
        mData.add("c++");
        mData.add("java");
        mData.add("android");
        mData.add("ios");
        mData.add("php");
        mData.add("h5");
        mData.add("python");
        mData.add("boolean");
        mData.add("byte");
        mData.add("short");
        mData.add("c++");
        mData.add("java");
        mData.add("android");
        mData.add("ios");
        mData.add("php");
        mData.add("h5");
        mData.add("python");
        ArrayAdapter<String> mAdapter = new ArrayAdapter<String>(this, R.layout.item_listview_text, mData);
        mListView.setAdapter(mAdapter);
    }

    @Override
    public void onMyListViewScrollChange(int l, int t, int oldl, int oldt) {
//        Log.d("Test", "t = " + t + " oldt = " + oldt);
        if (t == 0) {
            mIsTop = true;
            mOriginHeight = mHeaderImage.getHeight();
        }
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        Log.d("Test", "firstVisibleItem = " + firstVisibleItem + "  visibleItemCount = " + visibleItemCount + "  totalItemCount = " + totalItemCount);
    }
}

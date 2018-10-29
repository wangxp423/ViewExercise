/**
 * 
 */
package com.xp.exercise.listview;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ListView;

import com.xp.exercise.R;
import com.xp.exercise.listview.adapter.SlowScrollAdapter;
import com.xp.exercise.util.UiUtil;

/**
 * Function: 一个滑动listView 内部item缓动的效果
 *
 * @author wangxiaopan
 * @Date 2016-03-22
 */
public class SlowScrollListViewActivity extends FragmentActivity implements OnScrollListener{
    private ListView mListView;
    private SlowScrollAdapter mAdapter;
    private Context mContext;
    private int mItemHeight, mImageHeight, mOffset;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = SlowScrollListViewActivity.this;
        setContentView(R.layout.activity_slow_scroll);
        initUI();
    }
    
    private void initUI(){
        final int screenWidth = UiUtil.getScreenWidth(mContext);
        mItemHeight = (int)(screenWidth * 320 / 750);
        mImageHeight = (int)(screenWidth * 500 / 750);
        mOffset = (mImageHeight - mItemHeight) / 2;
        mListView = (ListView) findViewById(R.id.slow_scroll_lv);
        int[] imgs = new int[]{R.drawable.img_001,R.drawable.img_002,R.drawable.img_003,R.drawable.img_004,R.drawable.img_005,R.drawable.img_006,R.drawable.img_007,R.drawable.img_008,R.drawable.img_009,R.drawable.img_011,R.drawable.img_012,R.drawable.img_013};
        mAdapter = new SlowScrollAdapter(mContext, imgs);
        mListView.setAdapter(mAdapter);
        mListView.setOnScrollListener(this);
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        ListView list = (ListView)view;
        final int headerCount = list.getHeaderViewsCount();
        final int first = list.getFirstVisiblePosition();
        if (headerCount == 0 || list.getCount() <= headerCount || first >= headerCount) return;
        if (scrollState != AbsListView.OnScrollListener.SCROLL_STATE_IDLE) return;
        View head = list.getChildAt(headerCount - 1);
        final int height = head.getHeight();
        final int top = head.getTop();
        if (top > -height>>1) list.setSelectionFromTop(0, 0);
        else list.setSelection(headerCount);
        
    }

    /* (non-Javadoc)
     * @see android.widget.AbsListView.OnScrollListener#onScroll(android.widget.AbsListView, int, int, int)
     */
    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        ListView list = (ListView)view;
        final int headerCount = list.getHeaderViewsCount();
        Log.v("Test", "onScroll.headerCount = " + headerCount);
        if (totalItemCount <= headerCount) return;
        Log.v("Test", "onScroll.totalItemCount = " + totalItemCount);
        Log.v("Test", "onScroll.visibleItemCount = " + visibleItemCount);
        final int upbaseline = list.getHeight() >> 1;
        Log.v("Test", "onScroll.itemHeight = " + list.getHeight());
        final int downbaseline = upbaseline + 1;
        Log.v("Test", "onScroll.upbaseline = " + upbaseline);
        Log.v("Test", "onScroll.downbaseline = " + downbaseline);
        for (int i=0;i<visibleItemCount;i++) {
            final int index = firstVisibleItem + i;
            Log.v("Test", "onScroll.index = " + index);
            if (index < headerCount) continue;
            ViewGroup child = (ViewGroup)list.getChildAt(i);
            if (child == null) return;
            View image = child.findViewById(R.id.icon_category_list_item);
            final int top = child.getTop();
            Log.v("Test", "onScroll.top = " + top);
            if (top < upbaseline) {
                int scrollY = mOffset + (upbaseline - top) * mOffset / mItemHeight / 2;
                final int max = mImageHeight - mItemHeight;
                if (scrollY > max) scrollY = max;
                image.scrollTo(0, scrollY);
                Log.v("Test", "onScroll.top<  scrollY = " + scrollY);
            } else if (top > downbaseline) {
                int scrollY = mOffset + (downbaseline - top) * mOffset / mItemHeight;
                if (scrollY < 0) scrollY = 0;
                image.scrollTo(0, scrollY);
                Log.v("Test", "onScroll.top>  scrollY = " + scrollY);
            } else {
                image.scrollTo(0, mOffset);
                Log.v("Test", "onScroll.top else");
            }
        }
        
    }

}

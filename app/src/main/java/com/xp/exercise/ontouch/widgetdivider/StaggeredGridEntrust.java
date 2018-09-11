package com.xp.exercise.ontouch.widgetdivider;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

/**
 * 作者：请叫我百米冲刺 on 2016/12/21 下午12:45
 * 邮箱：mail@hezhilin.cc
 */

public class StaggeredGridEntrust extends SpacesItemDecorationEntrust {


    public StaggeredGridEntrust(int leftRight, int topBottom, int mColor) {
        super(leftRight, topBottom, mColor);

    }

    @Override
    void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        StaggeredGridLayoutManager layoutManager = (StaggeredGridLayoutManager) parent.getLayoutManager();
        if (mDivider == null || layoutManager.getChildCount() == 0) {
            return;
        }
        int left;
        int right;
        int top;
        int bottom;

        final int spanCount = layoutManager.getSpanCount();
        final int childCount = parent.getChildCount();

        //换个思路，每个item都画边和右边，当然第一排的不需要上边，最右边的不需要右边
        if (layoutManager.getOrientation() == StaggeredGridLayoutManager.VERTICAL) {
            for (int i = 0; i < childCount; i++) {
                final View child = parent.getChildAt(i);
                final StaggeredGridLayoutManager.LayoutParams params = (StaggeredGridLayoutManager.LayoutParams) child.getLayoutParams();
                //得到它在总数里面的位置
                final int position = parent.getChildAdapterPosition(child);
                //它在每列的位置
                final int spanPosition = params.getSpanIndex();
                //将带有颜色的分割线处于中间位置
                final float centerLeft = (layoutManager.getLeftDecorationWidth(child) + 1 - leftRight) / 2;
                final float centerTop = (layoutManager.getBottomDecorationHeight(child) + 1 - topBottom) / 2;
                //画上边的
                if (position > spanCount - 1) {
                    left = child.getLeft() + params.leftMargin;
                    if (spanPosition > 0) {
                        left -= centerLeft;
                    }
                    right = child.getRight() + params.rightMargin;
                    if (spanPosition + 1 != spanCount) {//最右边的不需要那一丢丢
                        right += centerLeft;
                    }
                    top = (int) (child.getTop() + params.topMargin - centerTop - topBottom);
                    bottom = top + topBottom;
                    mDivider.setBounds(left, top, right, bottom);
                    mDivider.draw(c);
                }

                //画右边的
                if ((spanPosition + 1) % spanCount != 0) {
                    left = (int) (child.getRight() + params.rightMargin + centerLeft);
                    right = left + leftRight;
                    top = child.getTop() + params.topMargin;
                    //第一排的不需要上面那一丢丢
                    if (position > spanCount - 1) {//换个思路，都给top了
                        top -= centerTop + centerTop + topBottom;
                    }
                    bottom = child.getBottom() + params.bottomMargin;
                    mDivider.setBounds(left, top, right, bottom);
                    mDivider.draw(c);
                }
            }
        } else {
            for (int i = 0; i < childCount; i++) {
                final View child = parent.getChildAt(i);
                final StaggeredGridLayoutManager.LayoutParams params = (StaggeredGridLayoutManager.LayoutParams) child.getLayoutParams();
                //得到它在总数里面的位置
                final int position = parent.getChildAdapterPosition(child);
                //它在每列的位置
                final int spanPosition = params.getSpanIndex();
                //将带有颜色的分割线处于中间位置
                final float centerLeft = (layoutManager.getRightDecorationWidth(child) + 1 - leftRight) / 2;
                final float centerTop = (layoutManager.getTopDecorationHeight(child) + 1 - topBottom) / 2;
                //画左边
                if (position > spanCount - 1) {
                    left = (int) (child.getLeft() + params.leftMargin - centerLeft - leftRight);
                    right = left + leftRight;
                    top = (int) (child.getTop() + params.topMargin - centerTop);
                    if (spanPosition == 0) {
                        top += centerTop;
                    }
                    bottom = child.getBottom() + params.bottomMargin;
                    if (spanPosition + 1 != spanCount) {
                        bottom += centerTop;
                    }
                    mDivider.setBounds(left, top, right, bottom);
                    mDivider.draw(c);
                }
                //画上面的
                if (spanPosition > 0) {
                    left = child.getLeft() + params.leftMargin;
                    if (position > spanCount - 1) {//换个思路，都给left了
                        left -= centerLeft + centerLeft + leftRight;
                    }
                    right = child.getRight() + params.rightMargin;
                    top = (int) (child.getTop() + params.bottomMargin - centerTop - topBottom);
                    bottom = top + topBottom;
                    mDivider.setBounds(left, top, right, bottom);
                    mDivider.draw(c);
                }
            }

        }
    }

    @Override
    void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        StaggeredGridLayoutManager layoutManager = (StaggeredGridLayoutManager) parent.getLayoutManager();
        final StaggeredGridLayoutManager.LayoutParams lp = (StaggeredGridLayoutManager.LayoutParams) view.getLayoutParams();
        final int childPosition = parent.getChildAdapterPosition(view);
        final int spanCount = layoutManager.getSpanCount();
        //这边我们换下思路，每一item都需要bottom，同时第一排的需要top
        if (layoutManager.getOrientation() == StaggeredGridLayoutManager.VERTICAL) {
            if (childPosition < spanCount) {//第一排的需要上面
                outRect.top = topBottom;
            }
            if (lp.getSpanIndex() == spanCount - 1) {//最边上的需要右边
                outRect.right = leftRight;
            }
            outRect.bottom = topBottom;
            outRect.left = leftRight;
        } else {
            //这边我们换下思路，每一item都需要right，同时第一排的需要left
            if (childPosition < spanCount) {//第一排的需要left
                outRect.left = leftRight;
            }
            if (lp.getSpanIndex() == spanCount - 1) {//最边上的需要bottom
                outRect.bottom = topBottom;
            }
            outRect.right = leftRight;
            outRect.top = topBottom;
        }
    }
}

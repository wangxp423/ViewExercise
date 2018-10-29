package com.xp.exercise.viewpager;

import android.os.Bundle;

import com.xp.exercise.canvas.widget.BezierRoundIndicatorView;
import com.xp.exercise.statsbar.base.CompatStatusBarActivity;

/**
 * @类描述：应用常量类
 * @创建人：Wangxiaopan
 * @创建时间：2018/10/10 0010 17:40
 * @修改人：
 * @修改时间：2018/10/10 0010 17:40
 * @修改备注：
 */
public class IndicatorViewPagerActivity extends CompatStatusBarActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BezierRoundIndicatorView view = new BezierRoundIndicatorView(this);
        setContentView(view);
    }
}

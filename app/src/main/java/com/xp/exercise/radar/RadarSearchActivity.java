package com.xp.exercise.radar;

import com.xp.exercise.R;
import com.xp.exercise.radar.RadarSearchView;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

/**
 * 
 * Function:仿探探雷达搜索
 *
 * @author wangxiaopan
 * @Date 2016-06-01
 */
public class RadarSearchActivity extends FragmentActivity {
    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
//        RadarSearchView view = new RadarSearchView(this);
//        setContentView(view);
        setContentView(R.layout.activity_radar_search);
    }
}

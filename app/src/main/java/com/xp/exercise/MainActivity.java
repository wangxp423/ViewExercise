package com.xp.exercise;
import com.xp.exercise.activity.CanvasActivity;
import com.xp.exercise.activity.PullToChangeHeaderActivity;
import com.xp.exercise.activity.RadarSearchActivity;
import com.xp.exercise.activity.ScrollerTestActivity;
import com.xp.exercise.activity.SlowScrollListViewActivity;
import com.xp.exercise.activity.SpeedActivity;
import com.xp.exercise.activity.CircleWaveActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
public class MainActivity extends Activity implements OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.main_canvas).setOnClickListener(this);
        findViewById(R.id.main_scroller).setOnClickListener(this);
        findViewById(R.id.main_yuyin).setOnClickListener(this);
        findViewById(R.id.main_listview_header).setOnClickListener(this);
        findViewById(R.id.main_radar_search).setOnClickListener(this);
        findViewById(R.id.main_radar_wave).setOnClickListener(this);
        findViewById(R.id.main_slow_scroll).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
        case R.id.main_canvas:
            startActivity(new Intent(this, CanvasActivity.class));
            break;
        case R.id.main_scroller:
            startActivity(new Intent(this, ScrollerTestActivity.class));
            break;
        case R.id.main_yuyin:
            startActivity(new Intent(this, SpeedActivity.class));
            break;
        case R.id.main_listview_header:
            startActivity(new Intent(this, PullToChangeHeaderActivity.class));
            break;
        case R.id.main_radar_search:
            startActivity(new Intent(this, RadarSearchActivity.class));
            break;
        case R.id.main_radar_wave:
            startActivity(new Intent(this, CircleWaveActivity.class));
            break;
        case R.id.main_slow_scroll:
        	startActivity(new Intent(this, SlowScrollListViewActivity.class));
        	break;
        }
    }
}

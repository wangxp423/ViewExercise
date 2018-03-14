package com.xp.exercise;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.xp.exercise.activity.PullToChangeHeaderActivity;
import com.xp.exercise.activity.ScrollerTestActivity;
import com.xp.exercise.activity.SlowScrollListViewActivity;
import com.xp.exercise.activity.VisibleActivity;
import com.xp.exercise.canvas.CanvasActivity;
import com.xp.exercise.canvas.CircleWaveActivity;
import com.xp.exercise.canvas.CompassActivity;
import com.xp.exercise.canvas.SpeedActivity;
import com.xp.exercise.radar.RadarSearchActivity;
import com.xp.exercise.statsbar.MainStatusBarActivity;
import com.xp.exercise.toolbar.MainToolbarActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements OnClickListener {

    @BindView(R.id.main_canvas)
    TextView mainCanvas;
    @BindView(R.id.main_compass)
    TextView mainCompass;
    @BindView(R.id.main_oclock)
    TextView mainOclock;
    @BindView(R.id.main_scroller)
    TextView mainScroller;
    @BindView(R.id.main_listview_header)
    TextView mainListviewHeader;
    @BindView(R.id.main_visible)
    TextView mainVisible;
    @BindView(R.id.main_yuyin)
    TextView mainYuyin;
    @BindView(R.id.main_radar_search)
    TextView mainRadarSearch;
    @BindView(R.id.main_radar_wave)
    TextView mainRadarWave;
    @BindView(R.id.main_slow_scroll)
    TextView mainSlowScroll;
    @BindView(R.id.main_toolbar)
    TextView mainToolbar;
    @BindView(R.id.main_statusbar)
    TextView mainStatusbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Window window = getWindow();
        ButterKnife.bind(this);
        mainCanvas.setOnClickListener(this);
        mainCompass.setOnClickListener(this);
        mainOclock.setOnClickListener(this);
        mainScroller.setOnClickListener(this);
        mainListviewHeader.setOnClickListener(this);
        mainVisible.setOnClickListener(this);
        mainYuyin.setOnClickListener(this);
        mainRadarSearch.setOnClickListener(this);
        mainRadarWave.setOnClickListener(this);
        mainSlowScroll.setOnClickListener(this);
        mainToolbar.setOnClickListener(this);
        mainStatusbar.setOnClickListener(this);
        setRippleBg();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.main_canvas:
                startActivity(new Intent(this, CanvasActivity.class));
                break;
            case R.id.main_compass:
                Intent compassIntent = new Intent(this, CompassActivity.class);
                compassIntent.putExtra(CompassActivity.EXTRA_TYPE, CompassActivity.EXTRA_TYPE_COMPASS);
                startActivity(compassIntent);
                break;
            case R.id.main_oclock:
                Intent oclockIntent = new Intent(this, CompassActivity.class);
                oclockIntent.putExtra(CompassActivity.EXTRA_TYPE, CompassActivity.EXTRA_TYPE_OCLOCK);
                startActivity(oclockIntent);
                break;
            case R.id.main_scroller:
                startActivity(new Intent(this, ScrollerTestActivity.class));
                break;
            case R.id.main_listview_header:
                startActivity(new Intent(this, PullToChangeHeaderActivity.class));
                break;
            case R.id.main_visible:
                Intent intent = new Intent(this, VisibleActivity.class);
                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
                break;
            case R.id.main_yuyin:
                startActivity(new Intent(this, SpeedActivity.class));
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
            case R.id.main_toolbar:
                startActivity(new Intent(this, MainToolbarActivity.class));
                break;
            case R.id.main_statusbar:
                startActivity(new Intent(this, MainStatusBarActivity.class));
                break;
            default:
                break;
        }
    }

    private void setRippleBg() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mainCanvas.setBackground(getResources().getDrawable(R.drawable.btn_ripple_blue));
            mainCompass.setBackground(getResources().getDrawable(R.drawable.btn_ripple_blue));
            mainOclock.setBackground(getResources().getDrawable(R.drawable.btn_ripple_blue));
            mainScroller.setBackground(getResources().getDrawable(R.drawable.btn_ripple_blue));
            mainListviewHeader.setBackground(getResources().getDrawable(R.drawable.btn_ripple_blue));
            mainVisible.setBackground(getResources().getDrawable(R.drawable.btn_ripple_blue));
            mainYuyin.setBackground(getResources().getDrawable(R.drawable.btn_ripple_blue));
            mainRadarSearch.setBackground(getResources().getDrawable(R.drawable.btn_ripple_blue));
            mainRadarWave.setBackground(getResources().getDrawable(R.drawable.btn_ripple_blue));
            mainSlowScroll.setBackground(getResources().getDrawable(R.drawable.btn_ripple_blue));
            mainToolbar.setBackground(getResources().getDrawable(R.drawable.btn_ripple_blue));
            mainStatusbar.setBackground(getResources().getDrawable(R.drawable.btn_ripple_blue));
        }
    }
}

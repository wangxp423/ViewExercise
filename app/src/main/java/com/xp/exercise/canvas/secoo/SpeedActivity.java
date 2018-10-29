package com.xp.exercise.canvas.secoo;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;

import com.xp.exercise.R;

import java.util.Random;

/**
 * 
 * Function: 寺库 语音搜索效果研究
 *
 * @author wangxiaopan
 * @Date 2016-05-13
 */
public class SpeedActivity extends FragmentActivity implements OnClickListener{
    
    private LineCircleTransitionView mLctView;
    private SoundWaveView mSwView;
    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_speed);
        initUI();
    }
    
    private void initUI(){
        findViewById(R.id.speed_start_circle).setOnClickListener(this);
        findViewById(R.id.speed_start_wave).setOnClickListener(this);
        mLctView = (LineCircleTransitionView) findViewById(R.id.speed_lct_view);
        mSwView = (SoundWaveView) findViewById(R.id.speed_sw_view);
        mSwView.reset();
    }
    
    private void startWave(){
        int[] waves = new int[]{0,1,2,3,4,5,6,7,8,9};
        Random random = new Random();
//        System.out.println("random = " + random.nextFloat());
        int volume = random.nextInt(10);
        mSwView.setSoundVolume(waves[volume]);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
        case R.id.speed_start_circle:
            mLctView.transition();
            break;
        case R.id.speed_start_wave:
            startWave();
            break;

        }
        
    }
}

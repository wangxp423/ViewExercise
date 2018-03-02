package com.xp.exercise.activity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.xp.exercise.view.CompassView;
import com.xp.exercise.view.OclockView;

/**
 * @类描述：指南针、时钟
 * @创建人：Wangxiaopan
 * @创建时间：2018/2/26 0026 14:55
 * @修改人：
 * @修改时间：2018/2/26 0026 14:55
 * @修改备注：
 */

public class CompassActivity extends AppCompatActivity {
    public static String EXTRA_TYPE = "extra_type";
    public static String EXTRA_TYPE_COMPASS = "extra_type_compass";
    public static String EXTRA_TYPE_OCLOCK = "extra_type_oclock";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String type = getIntent().getStringExtra(EXTRA_TYPE);
        if (type.equals(EXTRA_TYPE_COMPASS)) {
            //指南针
            setCompassView();
        } else if (type.equals(EXTRA_TYPE_OCLOCK)) {
            //时钟
            setOclockView();
        } else {
            //指南针
            setCompassView();
        }

    }

    private CompassView mCompassView;
    private SensorManager mSensorManager;
    private SensorEventListener mSensorEventListener;
    private float val;

    //指南针View
    private void setCompassView() {
        mCompassView = new CompassView(this);
        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        mSensorEventListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                val = event.values[0];
                mCompassView.setVal(val);
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };
        mSensorManager.registerListener(mSensorEventListener, mSensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION),
                SensorManager.SENSOR_DELAY_GAME);

        setContentView(mCompassView);
    }

    //时钟View
    private void setOclockView() {
        setContentView(new OclockView(this));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (null != mSensorManager) {
            mSensorManager.unregisterListener(mSensorEventListener);
        }
    }
}

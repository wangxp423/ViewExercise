package com.xp.exercise.viewdraghelper.lesson1;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;

import com.xp.exercise.R;
import com.xp.exercise.util.ToastUtils;

/**
 * Created by jacob-wj on 2015/4/14.
 */
public class LessonOneActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson_one);
        findViewById(R.id.dragview).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.showLongToast("点击了");
            }
        });
    }


}

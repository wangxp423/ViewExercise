package com.xp.exercise.viewdraghelper.lesson4;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.xp.exercise.R;


/**
 * Created by jacob-wj on 2015/4/14.
 */
public class LessonFourActivity extends FragmentActivity implements View.OnClickListener {

    private Button mButtonHidden;
    private Button mButtonQueen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson_four);

        mButtonHidden = (Button) findViewById(R.id.button_hidden);
        mButtonQueen = (Button) findViewById(R.id.button_queen);

        mButtonHidden.setOnClickListener(this);
        mButtonQueen.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        Button button = (Button) v;
        Log.e("OnClick:", button.getText().toString());

    }
}

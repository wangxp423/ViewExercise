package com.xp.exercise.activity;

import com.xp.exercise.R;
import com.xp.exercise.scroller.MyLinearLayout;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Scroller;

public class ScrollerTestActivity extends FragmentActivity implements OnClickListener {

    private MyLinearLayout mLayout1, mLayout2;
    private Scroller mScroller;
    private Button mButton,mLayoutBtn1,mLayoutBtn2;

    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_scroller);
        mButton = (Button) findViewById(R.id.scroller_btn);
        mButton.setOnClickListener(this);
        mScroller = new Scroller(this);
        mLayout1 = (MyLinearLayout) findViewById(R.id.scroller_layout1);
        mLayout2 = (MyLinearLayout) findViewById(R.id.scroller_layout2);
        mLayoutBtn1 = (Button) findViewById(R.id.scroller_layout_btn1);
        mLayoutBtn1.setOnClickListener(this);
        mLayoutBtn2 = (Button) findViewById(R.id.scroller_layout_btn2);
        mLayoutBtn2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
        case R.id.scroller_btn:
            // mButton.scrollTo(-50, -50); //执行一次
            mButton.scrollBy(-50, 0); // 执行多次
            break;

        case R.id.scroller_layout_btn1:
            mLayout1.startScroller(-600,0,1000);
            break;
        case R.id.scroller_layout_btn2:
            mLayout2.startScroller(-800,0,1500);
            break;
        }

    }

}

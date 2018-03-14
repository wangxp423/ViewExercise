package com.xp.exercise;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

/**
 * @类描述：欢迎页
 * @创建人：Wangxiaopan
 * @创建时间：2018/3/13 0013 15:08
 * @修改人：
 * @修改时间：2018/3/13 0013 15:08
 * @修改备注：
 */

public class WelcomActivity extends AppCompatActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        setFullScreen();
        TextView textView = (TextView) findViewById(R.id.welcom_to_main);
        textView.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
        textView.getPaint().setAntiAlias(true);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(WelcomActivity.this,MainActivity.class));
            }
        });
    }

    private void setFullScreen(){
        //方式一  有actionBar
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //方式二 状态栏可拉下来 然后显示并停留
//        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);
        //方式三 style.xml中配置
        //<style name="fullScreen" parent="Theme.AppCompat.DayNight.NoActionBar">
        //        <item name="android:windowFullscreen">true</item>
        //</style>
    }
}

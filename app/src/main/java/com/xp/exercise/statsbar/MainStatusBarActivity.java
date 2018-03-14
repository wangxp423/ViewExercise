package com.xp.exercise.statsbar;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.xp.exercise.BaseActivity;
import com.xp.exercise.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @类描述：状态栏研究 首页
 * @创建人：Wangxiaopan
 * @创建时间：2018/3/13 0013 15:48
 * @修改人：
 * @修改时间：2018/3/13 0013 15:48
 * @修改备注：
 */

public class MainStatusBarActivity extends BaseActivity implements View.OnClickListener{
    @BindView(R.id.statusbar_full_hide)
    Button statusbarFullHide;
    @BindView(R.id.statusbar_full_show)
    Button statusbarFullShow;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_statusbar);
        setStatusBar();
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);
        statusbarFullHide.setOnClickListener(this);
        statusbarFullShow.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.statusbar_full_hide:
                Toast.makeText(this,"参考启动页(WelcomeActivity配置)",Toast.LENGTH_LONG).show();
                break;
            case R.id.statusbar_full_show:
                startActivity(new Intent(MainStatusBarActivity.this,FullScreenHaveStatusActivity.class));
                break;
        }
    }

    private void setStatusBar(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = getWindow();
            // Translucent status bar
            window.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

//            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//            ViewGroup contentView = (ViewGroup) window.getDecorView().findViewById(Window.ID_ANDROID_CONTENT);
//            contentView.getChildAt(0).setFitsSystemWindows(false);
        }
    }
}

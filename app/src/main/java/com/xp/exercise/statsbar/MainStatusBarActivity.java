package com.xp.exercise.statsbar;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.xp.exercise.R;
import com.xp.exercise.statsbar.base.StatusBarBaseActivity;

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

public class MainStatusBarActivity extends StatusBarBaseActivity implements View.OnClickListener {
    @BindView(R.id.statusbar_full_hide)
    Button statusbarFullHide;
    @BindView(R.id.statusbar_full_show)
    Button statusbarFullShow;
    @BindView(R.id.statusbar_same_title_status)
    Button statusbarSameTitleStatus;
    @BindView(R.id.statusbar_change_text_color)
    Button statusbarChangeTextColor;
    @BindView(R.id.statusbar_change_text_color2)
    Button statusbarChangeTextColor2;
    @BindView(R.id.statusbar_with_fragment)
    Button statusbarWithFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_statusbar);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);
        statusbarFullHide.setOnClickListener(this);
        statusbarFullShow.setOnClickListener(this);
        statusbarSameTitleStatus.setOnClickListener(this);
        statusbarChangeTextColor.setOnClickListener(this);
        statusbarChangeTextColor2.setOnClickListener(this);
        statusbarWithFragment.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.statusbar_full_hide:
                Toast.makeText(this, "参考启动页(WelcomeActivity配置)", Toast.LENGTH_LONG).show();
                break;
            case R.id.statusbar_full_show:
                startActivity(new Intent(MainStatusBarActivity.this, FullScreenHaveStatusActivity.class));
                break;
            case R.id.statusbar_same_title_status:
                Toast.makeText(this, "参考当前页BaseActivity配置", Toast.LENGTH_LONG).show();
                break;
            case R.id.statusbar_change_text_color:
                startActivity(new Intent(MainStatusBarActivity.this, StatusBarTextColorActivity.class));
                break;
            case R.id.statusbar_change_text_color2:
                startActivity(new Intent(MainStatusBarActivity.this, StatusBarTextColor2Activity.class));
                break;
            case R.id.statusbar_with_fragment:
                startActivity(new Intent(MainStatusBarActivity.this, FragmentStatusbarActivity.class));
                break;
        }
    }
}

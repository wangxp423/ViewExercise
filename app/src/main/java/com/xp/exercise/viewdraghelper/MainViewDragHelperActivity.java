package com.xp.exercise.viewdraghelper;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;

import com.xp.exercise.R;
import com.xp.exercise.viewdraghelper.lesson1.LessonOneActivity;
import com.xp.exercise.viewdraghelper.lesson2.LessonTwoActivity;
import com.xp.exercise.viewdraghelper.lesson3.LessonThreeActivity;
import com.xp.exercise.viewdraghelper.lesson4.LessonFourActivity;
import com.xp.exercise.viewdraghelper.lesson5.LessonFiveActivity;

/**
 * @类描述：ViewDragHelper研究
 * @创建人：Wangxiaopan
 * @创建时间：2018/10/10 0010 17:29
 * @修改人：
 * @修改时间：2018/10/10 0010 17:29
 * @修改备注： 原创地址 https://github.com/wangjia55/ViewDragHelper
 * 因此项目过老，下载以后运行失败，懒得改。因此直接拷贝到本项目中
 * 后续有时间继续研究修改
 */
public class MainViewDragHelperActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_draghelper_main);
    }

    public void lessonOne(View view) {
        Intent intent = new Intent(this, LessonOneActivity.class);
        startActivity(intent);
    }

    public void lessonTwo(View view) {
        Intent intent = new Intent(this, LessonTwoActivity.class);
        startActivity(intent);
    }

    public void lessonThree(View view) {
        Intent intent = new Intent(this, LessonThreeActivity.class);
        startActivity(intent);
    }

    public void lessonFour(View view) {
        Intent intent = new Intent(this, LessonFourActivity.class);
        startActivity(intent);
    }

    public void lessonFive(View view) {
        Intent intent = new Intent(this, LessonFiveActivity.class);
        startActivity(intent);
    }

}

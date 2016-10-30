package com.xp.exercise.radar;

import com.xp.exercise.R;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;

public class RadarSearchView extends FrameLayout implements OnClickListener {

    private ImageView mHeadImage;
    private ImageView mRadarImage;
    private CircleRippleView mCircleRippleView;

    public RadarSearchView(Context context) {
        super(context);
        initUI(context);
    }

    public RadarSearchView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initUI(context);
    }

    public RadarSearchView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initUI(context);
    }

    private void initUI(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.view_radar_search, this, true);
        mRadarImage = (ImageView) findViewById(R.id.radar_image);
        Animation mAnimation = AnimationUtils.loadAnimation(context, R.anim.radar_search_animation);
        mRadarImage.startAnimation(mAnimation);
        
        mCircleRippleView = (CircleRippleView) findViewById(R.id.radar_bg_image);

        mHeadImage = (ImageView) findViewById(R.id.radar_head_image);
        mHeadImage.setOnClickListener(this);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // TODO Auto-generated method stub
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        // TODO Auto-generated method stub
        super.onLayout(changed, left, top, right, bottom);
        // Log.d("Test", "changed = " + changed);
        // Log.d("Test", "left = " + left);
        // Log.d("Test", "top = " + top);
        // Log.d("Test", "right = " + right);
        // Log.d("Test", "bottom = " + bottom);
        // int count = getChildCount();
        // View view = getChildAt(0);
        // Log.d("Test", "view.count = " + count);
        // Log.d("Test", "view.width = " + view.getWidth());
        // Log.d("Test", "view.height = " + view.getHeight());
        if (changed) {
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
        case R.id.radar_head_image:
            mCircleRippleView.startRipple();
//            ScaleAnimation animation =new ScaleAnimation(1f, 1.5f, 1f, 1.5f,
//                    Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
//            animation.setDuration(1500);
//            mHeadImage.startAnimation(animation);
            Animation mAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.radar_image_scale);
            mHeadImage.startAnimation(mAnimation);
            break;
        }
    }

}

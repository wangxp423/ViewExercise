package com.xp.exercise.canvas;

import android.os.Bundle;
import android.view.View;

import com.xp.exercise.Constant;
import com.xp.exercise.R;
import com.xp.exercise.canvas.widget.BezierRoundIndicatorView;
import com.xp.exercise.canvas.widget.CanvasView;
import com.xp.exercise.canvas.widget.CircleWaveView;
import com.xp.exercise.canvas.widget.FlowWaveView;
import com.xp.exercise.canvas.widget.OclockView;
import com.xp.exercise.canvas.widget.PaintView;
import com.xp.exercise.canvas.widget.PathCubic;
import com.xp.exercise.canvas.widget.PathView;
import com.xp.exercise.statsbar.base.CompatStatusBarActivity;

/**
 * @类描述：自定义View
 * @创建人：Wangxiaopan
 * @创建时间：2018/10/10 0010 17:29
 * @修改人：
 * @修改时间：2018/10/10 0010 17:29
 * @修改备注：
 */
public class CanvasActivity extends CompatStatusBarActivity {
    private String tag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tag = getIntent().getStringExtra(Constant.EXTRA_CUSTOM_VIEW_TAG);
        View view = null;
        switch (tag) {
            case Constant.CUSTOM_VIEW_DRAW_CANVAS:
                view = new CanvasView(this);
                break;
            case Constant.CUSTOM_VIEW_DRAW_PAINT:
                view = new PaintView(this);
                break;
            case Constant.CUSTOM_VIEW_DRAW_PATH:
                view = new PathView(this);
                break;
            case Constant.CUSTOM_VIEW_DRAW_BEZIER:
                view = new PathCubic(this);
                break;
            case Constant.CUSTOM_VIEW_DRAW_DRAG_BEZIER:
                view = new BezierRoundIndicatorView(this);
                break;
            case Constant.CUSTOM_VIEW_DRAW_COLLECTION:
                view = new FlowWaveView(this);
                break;
            case Constant.CUSTOM_VIEW_DRAW_RADAR_SEARCH:
                view = new CircleWaveView(this);
                break;
            case Constant.CUSTOM_VIEW_DRAW_TANTAN_SEARCH:
                view = getLayoutInflater().inflate(R.layout.activity_radar_search, null);
                break;
            case Constant.CUSTOM_VIEW_DRAW_CLOCK:
                view = new OclockView(this);
                break;

        }
        setContentView(view);
    }
}

package com.xp.exercise.viewdraghelper.lesson5;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.xp.exercise.R;


/**
 * Created by jacob-wj on 2015/4/14.
 */
public class LessonFiveActivity extends FragmentActivity implements View.OnClickListener {
    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson_five);
        setupListView();

    }

    private void setupListView() {
        mListView = (ListView) findViewById(R.id.listView);
        SampleAdapter adapter = new SampleAdapter(this);
        for (int i = 0; i < 20; i++) {
            adapter.add(new SampleItem("Love Android"));
        }
        mListView.setAdapter(adapter);
    }


    private class SampleItem {
        public String tag;

        public SampleItem(String tag) {
            this.tag = tag;
        }
    }

    public class SampleAdapter extends ArrayAdapter<SampleItem> {

        public SampleAdapter(Context context) {
            super(context, 0);
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.layout_lesson5_item, null);
            }
            TextView title = (TextView) convertView.findViewById(R.id.text_view_name);
            title.setText(getItem(position).tag);

            return convertView;
        }

    }

    @Override
    public void onClick(View v) {

    }
}

package com.xp.exercise.main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.xp.exercise.R;

import java.util.ArrayList;

/**
 * @类描述：首页 菜单adapter
 * @创建人：Wangxiaopan
 * @创建时间：2018/10/25 0025 16:20
 * @修改人：
 * @修改时间：2018/10/25 0025 16:20
 * @修改备注：
 */
public class MainMenuAdapter extends BaseExpandableListAdapter {
    private Context mContext;
    private LayoutInflater mInflater;
    private ExpandableListView elvList;
    private ArrayList<MainListEntity.LevelOneEntity> data;

    public MainMenuAdapter(Context context, ExpandableListView elvList, ArrayList<MainListEntity.LevelOneEntity> data) {
        this.mContext = context;
        this.mInflater = LayoutInflater.from(context);
        this.elvList = elvList;
        this.data = data;
    }

    @Override
    public int getGroupCount() {
        return data.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return data.get(groupPosition).getChilds().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return data.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return data.get(groupPosition).getChilds().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        GroupViewHolder holder;
        if (null == convertView) {
            convertView = mInflater.inflate(R.layout.item_main_elv_parent, parent, false);
            holder = new GroupViewHolder();
            holder.parentTitle = (TextView) convertView.findViewById(R.id.main_elv_parent_title);
            convertView.setTag(holder);
        } else {
            holder = (GroupViewHolder) convertView.getTag();
        }
        holder.parentTitle.setText(data.get(groupPosition).getTitle());
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildViewHolder holder;
        if (null == convertView) {
            convertView = mInflater.inflate(R.layout.item_main_elv_child, parent, false);
            holder = new ChildViewHolder();
            holder.childTitle = (TextView) convertView.findViewById(R.id.main_elv_child_title);
            convertView.setTag(holder);
        } else {
            holder = (ChildViewHolder) convertView.getTag();
        }
        holder.childTitle.setText(data.get(groupPosition).getChilds().get(childPosition).getTitle());
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        //如果子条目需要响应click事件，必须返回true
        return true;
    }

    static class GroupViewHolder {
        TextView parentTitle;
    }

    static class ChildViewHolder {
        TextView childTitle;
    }
}

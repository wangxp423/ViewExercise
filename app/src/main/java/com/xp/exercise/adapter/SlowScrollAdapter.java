/**
 * 
 */
package com.xp.exercise.adapter;

import com.xp.exercise.R;
import com.xp.exercise.util.UiUtil;

import android.content.Context;
import android.transition.TransitionSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ImageView.ScaleType;

/**
 * Function:listView滑动item缓动适配器
 *
 * @author wangxiaopan
 * @Date 2016-03-22
 */
public class SlowScrollAdapter extends BaseAdapter{
    private Context mContext;
    private int[] mImgs;
    private int mParentHeight,imgHeight;
    private LayoutInflater mLayoutInflater;
    
    public SlowScrollAdapter(Context context,int[] imgs) {
        this.mContext = context;
        this.mImgs = imgs;
        mLayoutInflater = LayoutInflater.from(context);
        final float scale = 320f / 750;
        final int screenWidth = UiUtil.getScreenWidth(context);
        mParentHeight = (int)(screenWidth * scale);
        imgHeight = (int)(screenWidth*500/750);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return mImgs.length;
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return mImgs[position];
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (null == convertView) {
            convertView = mLayoutInflater.inflate(R.layout.item_img, parent, false);
            
            holder = new ViewHolder();
            holder.iconParent = convertView.findViewById(R.id.icon_parent_category_list_item);
            ViewGroup.LayoutParams params = holder.iconParent.getLayoutParams();
            params.height = mParentHeight;
            holder.iconParent.setLayoutParams(params);
            
            holder.icon = (ImageView)convertView.findViewById(R.id.icon_category_list_item);
            params = holder.icon.getLayoutParams();
            params.height = imgHeight;
            holder.icon.setLayoutParams(params);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.icon.setImageResource(mImgs[position]);
        return convertView;
    }
    
    static class ViewHolder{
        ImageView icon;
        TextView name;
        View iconParent;
    }

}

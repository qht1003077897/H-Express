package com.qht.blog2.BaseAdapter.BaseListView;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by QHT on 2017-04-12.
 */
public class BaseListAdapter<T,H extends BaseListAdapter.ViewHolder> extends BaseAdapter{
private List<T> mData;
private ViewCreator<T,H>  mViewCreator;

public BaseListAdapter(ViewCreator<T,H>  mViewCreator){
this(new ArrayList<T>(),mViewCreator);
}

public BaseListAdapter( List<T> data,ViewCreator<T,H>  creator){

    mData = data == null ? new ArrayList<T>() : data;
    mViewCreator = creator;
}


@Override
public int getCount() {
    return  null==mData?0 : mData.size();
}

@Override
public T getItem(int i) {
    return mData.get(i);
}

@Override
public long getItemId(int i) {
    return i;
}

@Override
public View getView(int i, View convertView, ViewGroup viewGroup) {
        H holder;
    if(null==convertView){
        holder=mViewCreator.createHolder(i,viewGroup);
        convertView=holder.itemView;
    }else{
        holder=(H)convertView.getTag();
    }
    mViewCreator.bindData(i,holder,getItem(i));
    return convertView;
}
    //避免有时候notifyDataSetChanged调用不成功
    public void update(List<T> data) {
        mData.clear();
        addData(data);
    }

    public void addData(List<T> data) {
        if (data != null) {
            mData.addAll(data);
        }
        notifyDataSetChanged();
    }

public static abstract class ViewHolder {
    public final View itemView;

    public ViewHolder(View itemView) {
        this.itemView = itemView;
        itemView.setTag(this);
    }
}
}

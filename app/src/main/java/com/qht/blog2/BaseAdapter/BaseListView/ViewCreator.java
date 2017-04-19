package com.qht.blog2.BaseAdapter.BaseListView;

import android.view.ViewGroup;

/**
 * Created by QHT on 2017-04-12.
 */
public interface ViewCreator<T, H extends BaseListAdapter.ViewHolder> {

    H createHolder(int position, ViewGroup parent);

    /**
     * 设置列表里的视图内容
     *
     * @param position 在列表中的位置
     * @param holder   该位置对应的视图
     */
    void bindData(int position, H holder, T data);
}
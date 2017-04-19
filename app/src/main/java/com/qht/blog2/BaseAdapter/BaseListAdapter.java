package com.qht.blog2.BaseAdapter;

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
        mData = data == null ? new ArrayList<T>() : mData;
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

/*
使用实例
public class SampleFragment extends Fragment implements ViewCreator<User,SampleFragment.UserViewHolder> {

    private BaseListAdapter<User, UserViewHolder> mAdapter = new BaseListAdapter<User, UserViewHolder>(this);

    @Override
    public UserViewHolder createHolder(int position, ViewGroup parent) {
        return new UserViewHolder(LayoutInflater.from(getActivity()).inflate(R.layout.item_user, parent, false));
    }

    @Override
    public void bindData(int position, UserViewHolder holder, User user) {
        holder.name.setText(user.name);
        holder.email.setText(user.email);
    }

    static class UserViewHolder extends BaseListAdapter.ViewHolder {
        public final TextView name;
        public final TextView email;

        public UserViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name);
            email = (TextView) itemView.findViewById(R.id.email);
        }
    }
}*/

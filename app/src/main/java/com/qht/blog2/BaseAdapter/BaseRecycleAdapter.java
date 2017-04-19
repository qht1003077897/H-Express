package com.qht.blog2.BaseAdapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by QHT on 2017-04-12.
 */
    public class BaseRecycleAdapter extends RecyclerView.Adapter<BaseRecycleAdapter.MyViewHolder>
    {

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
        {
            MyViewHolder holder=null;
// = new MyViewHolder(LayoutInflater.from(
//                    HomeActivity.this).inflate(R.layout.item_home, parent,
//                    false));
            return holder;
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position)
        {
//            holder.tv.setText(mDatas.get(position));
        }

        @Override
        public int getItemCount()
        {
//            return mDatas.size();
            return 0;
        }


        class MyViewHolder extends RecyclerView.ViewHolder
        {
            TextView tv;
            public MyViewHolder(View view)
            {
                super(view);
//                tv = (TextView) view.findViewById(R.id.id_num);
            }
        }
}

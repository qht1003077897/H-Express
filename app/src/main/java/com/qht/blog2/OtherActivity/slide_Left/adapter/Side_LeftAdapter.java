package com.qht.blog2.OtherActivity.slide_Left.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.qht.blog2.BaseAdapter.BaseListView.BaseListAdapter;
import com.qht.blog2.BaseAdapter.BaseListView.BaseListHolder;
import com.qht.blog2.BaseAdapter.BaseListView.ViewCreator;
import com.qht.blog2.BaseBean.SlideLeftBean;
import com.qht.blog2.R;

import java.util.List;

/**
 * Created by QHT on 2017-04-25.
 */
public class Side_LeftAdapter extends BaseListAdapter<SlideLeftBean,Side_LeftAdapter.SlideLeftHolder> {

    public Side_LeftAdapter(List data,ViewCreator mViewCreator) {
        super(data,mViewCreator);
    }


    public  static class SlideLeftHolder extends BaseListHolder {
        public  ImageView iv;
        public  TextView  text;

        public SlideLeftHolder(View itemView) {
            super(itemView);
            iv = (ImageView) itemView.findViewById(R.id.item_img);
            text = (TextView) itemView.findViewById(R.id.item_tv);
        }
    }
}

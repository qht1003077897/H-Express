package com.qht.blog2.OtherActivity.slide_Left.data;

import com.qht.blog2.BaseBean.SlideLeftBean;
import com.qht.blog2.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by QHT on 2017-04-25.
 */
public class Left_itemdata {

    public static List<SlideLeftBean> getItemBeans(){
        List<SlideLeftBean> itemBeans=new ArrayList<>();
        itemBeans.add(new SlideLeftBean(R.drawable.sidebar_purse,"意见反馈",false));
        itemBeans.add(new SlideLeftBean(R.drawable.sidebar_decoration,"分享",false));
        itemBeans.add(new SlideLeftBean(R.drawable.sidebar_favorit,"关于",false));
        itemBeans.add(new SlideLeftBean(R.drawable.sidebar_file,"退出",false));
        return  itemBeans;
    }
}

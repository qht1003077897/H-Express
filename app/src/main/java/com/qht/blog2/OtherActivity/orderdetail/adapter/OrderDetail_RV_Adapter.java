package com.qht.blog2.OtherActivity.orderdetail.adapter;

import android.content.Context;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.qht.blog2.BaseBean.OrderInfoDetailBean;
import com.qht.blog2.OtherActivity.orderdetail.data.OrderSite;
import com.qht.blog2.R;
import com.qht.blog2.View.TimeLineMarkerView;

import java.util.List;

/**
 * Created by QHT on 2017-04-12.
 * 订单详情页面
 */
public class OrderDetail_RV_Adapter extends BaseQuickAdapter<OrderInfoDetailBean,BaseViewHolder> {

    private Context context;
    private TimeLineMarkerView timelineView;
    private String state;


    public OrderDetail_RV_Adapter(List<OrderInfoDetailBean> list,String state,Context context) {
        super(R.layout.activity_orderdetail_rv_item, list);
        this.context=context;
        this.state=state;
    }

    @Override
    protected void convert(BaseViewHolder helper, OrderInfoDetailBean item) {
        if(timelineView==null ) {
            timelineView = (TimeLineMarkerView) (helper.getView(R.id.tv_rv_activity_orderdetail_timelineview));
        }

        if(helper.getItemViewType()==OrderSite.BEGIN){
            timelineView.setBeginLine(null);
            helper.setTextColor(R.id.tv_rv_activity_orderdetail_time,context.getResources().getColor(R.color.red));
            helper.setTextColor(R.id.tv_rv_activity_orderdetail_content,context.getResources().getColor(R.color.red));
            timelineView.setMarkerDrawable(context.getResources().getDrawable(R.drawable.timeline_bg_red));
        }
            helper.setText(R.id.tv_rv_activity_orderdetail_content, item.getContext());
            //分两行显示
            helper.setText(R.id.tv_rv_activity_orderdetail_time,
                item.getTime().toString().substring(0,10)+"\n"+
                item.getTime().toString().substring(10));
    }

            @Override
            public int getItemViewType(int position) {
                if(position==0 && state.equals("3")){
                    return OrderSite.BEGIN;
                }else if(position==getItemCount()-1){
                    return OrderSite.END;
                }
                return  super.getItemViewType(position);
            }

}


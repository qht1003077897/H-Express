package com.qht.blog2.OtherFragment.order.orderSigned.adapter;

import android.content.Context;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.qht.blog2.BaseBean.OrderInfoLitePal;
import com.qht.blog2.OtherActivity.orderdetail.data.OrderState;
import com.qht.blog2.R;

import java.util.List;

/**
 * Created by QHT on 2017-04-18.
 */
public class OrderSigned_RV_Adapter extends BaseQuickAdapter<OrderInfoLitePal> {

    private Context context;

    public OrderSigned_RV_Adapter(List<OrderInfoLitePal> list, Context context) {
        super(R.layout.fragment_order_signed_rv_item,list);
        this.context=context;
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, OrderInfoLitePal orderInfoLitePal) {
        baseViewHolder.setText(R.id.tv_fragment_order_signed_com,orderInfoLitePal.getCom());
        baseViewHolder.setText(R.id.tv_fragment_order_signed_num,orderInfoLitePal.getNu());
        baseViewHolder.setText(R.id.tv_fragment_order_signed_state, OrderState.caseState(orderInfoLitePal.getState()));
        baseViewHolder.setText(R.id.tv_fragment_order_signed_time,orderInfoLitePal.getTime());
    }
}

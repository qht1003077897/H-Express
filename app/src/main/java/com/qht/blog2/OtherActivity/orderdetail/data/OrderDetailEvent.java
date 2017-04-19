package com.qht.blog2.OtherActivity.orderdetail.data;

import com.qht.blog2.BaseBean.OrderInfoBean;
import com.qht.blog2.BaseEventBus.Event;

/**
 * Created by QHT on 2017-04-10.
 */
public class OrderDetailEvent extends Event {

    public OrderInfoBean Data;

    public OrderDetailEvent(OrderInfoBean Data, Object source) {
        super(source);
        this.Data=Data;
    }
}

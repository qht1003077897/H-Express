package com.qht.blog2.OtherFragment.order.event;

import com.qht.blog2.BaseEventBus.Event;

/**
 * Created by QHT on 2017-04-10.
 */
public class OrderEvent extends Event {
    public int position;
    public String from; // 本条消息从哪个类发送出去的
    public  boolean needclose; // 是否需要关闭右移动画
    public  boolean needselect; // 是否需要选择checkbox

    public OrderEvent(boolean needclose,boolean needselect, String from, int position, Object source) {
        super(source);
        this.needclose=needclose;
        this.needselect=needselect;
        this.from=from;
        this.position=position;
    }
}

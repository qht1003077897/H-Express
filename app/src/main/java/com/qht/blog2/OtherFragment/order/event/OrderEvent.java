package com.qht.blog2.OtherFragment.order.event;

import com.qht.blog2.BaseEventBus.Event;

/**
 * Created by QHT on 2017-04-10.
 */
public class OrderEvent extends Event {
    public int position;
    public String from;
    public  boolean needclose;
    public  boolean needselect;

    public OrderEvent(boolean needclose,boolean needselect, String from, int position, Object source) {
        super(source);
        this.needclose=needclose;
        this.needselect=needselect;
        this.from=from;
        this.position=position;
    }
}

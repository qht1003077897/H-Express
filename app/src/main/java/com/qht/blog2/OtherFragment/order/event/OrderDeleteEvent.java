package com.qht.blog2.OtherFragment.order.event;

import com.qht.blog2.BaseEventBus.Event;

/**
 * Created by QHT on 2017-04-20.
 */
public class OrderDeleteEvent extends Event{

    public int position;
    public String from;
    public  boolean open;

    public OrderDeleteEvent(boolean open, String from, int position, Object source) {
        super(source);
        this.open=open;
        this.from=from;
        this.position=position;
    }
}

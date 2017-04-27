package com.qht.blog2.BaseEventBus;

/**
 * Created by QHT on 2017-04-10.
 */
public class Event {
    private Object mSource;

    public Event(Object source) {
        if(null!=source){
            this.mSource = source;
        }

    }
}

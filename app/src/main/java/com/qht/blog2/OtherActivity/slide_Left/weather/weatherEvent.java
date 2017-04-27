package com.qht.blog2.OtherActivity.slide_Left.weather;

import com.qht.blog2.BaseEventBus.Event;

/**
 * Created by QHT on 2017-04-27.
 */
public class weatherEvent extends Event{

    public String city;
    public String tem;

    public weatherEvent(String city,String tem,Object source) {
        super(source);
        this.city=city;
        this.tem=tem;
    }
}

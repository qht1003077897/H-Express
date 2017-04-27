package com.qht.blog2.OtherActivity.slide_Left.data;

import com.qht.blog2.BaseEventBus.Event;

/**
 * Created by QHT on 2017-04-26.
 */
public class QQLogin_PersonInfoEvent extends Event{

    public String name;
    public String sex;
    public String city;
    public String imageurl;


    public QQLogin_PersonInfoEvent(String name, String sex, String city,String imageurl,Object source) {
        super(source);
        this.city=city;
        this.name=name;
        this.sex=sex;
        this.imageurl=imageurl;
    }
}

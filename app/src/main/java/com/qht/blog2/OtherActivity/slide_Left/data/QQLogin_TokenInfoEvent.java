package com.qht.blog2.OtherActivity.slide_Left.data;

import com.qht.blog2.BaseEventBus.Event;

/**
 * Created by QHT on 2017-04-26.
 */
public class QQLogin_TokenInfoEvent extends Event {

    String openid;
    String access_token;
    String expires_in;


    public QQLogin_TokenInfoEvent(String openid, String access_token, String expires_in,Object source) {
        super(source);
        this.openid=openid;
        this.access_token=access_token;
        this.expires_in=expires_in;
    }
}

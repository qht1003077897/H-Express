package com.qht.blog2.OtherActivity.setting.data;

import com.qht.blog2.BaseEventBus.Event;

/**
 * Created by QHT on 2017-04-26.
 */
public class CacheEvent extends Event{

    public boolean infinish;
    public CacheEvent(boolean infinish,Object source) {
        super(source);
        this.infinish=infinish;
    }
}

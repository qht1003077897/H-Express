package com.qht.blog2.OtherActivity.qrcodeScan.data;

import com.qht.blog2.BaseEventBus.Event;

/**
 * Created by QHT on 2017-04-27.
 */
public class QrCodeEvent extends Event {

    public String result;

    public QrCodeEvent(String result, Object source) {
        super(source);
        this.result = result;
    }
}

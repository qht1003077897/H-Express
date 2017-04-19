package com.qht.blog2.BaseEventBus;

import android.os.Handler;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by QHT on 2017-04-10.
 */
public class EventBusUtil {
    private static final EventBus sEventBus = EventBus.builder()
            .strictMethodVerification(true)
            .throwSubscriberException(true)
            .build();

    private EventBusUtil() {
    }

    public static void register(Object subscriber) {
        if(sEventBus.isRegistered(subscriber)){
         return;
        }
        sEventBus.register(subscriber);
    }

    public static void unregister(Object subscriber) {
            sEventBus.unregister(subscriber);
    }


    public static void postSticky(Object event) {
        sEventBus.postSticky(event);
    }

    public static void postSync(Object event) {
        sEventBus.post(event);
    }

    public static void postAsync(final Object event) {
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                sEventBus.post(event);
            }
        });
    }
}
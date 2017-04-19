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
<<<<<<< HEAD
        if(sEventBus.isRegistered(subscriber)){
         return;
        }
=======
>>>>>>> 1a47797765543426d898db97039713e92955eb2f
        sEventBus.register(subscriber);
    }

    public static void unregister(Object subscriber) {
<<<<<<< HEAD
            sEventBus.unregister(subscriber);
    }


//    public static void registerSticky(Object subscriber)
//    {
//        sEventBus.registerSticky(subscriber);
//    }
//
//    public static void unregisterSticky(Object subscriber) {
//        sEventBus.unregisterSticky(subscriber);
//    }


    public static void postSticky(Object event) {
        sEventBus.postSticky(event);
=======
        sEventBus.unregister(subscriber);
>>>>>>> 1a47797765543426d898db97039713e92955eb2f
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
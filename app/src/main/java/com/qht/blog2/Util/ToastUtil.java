package com.qht.blog2.Util;

import android.widget.Toast;

import com.qht.blog2.Application.MyApplication;

/**
 * Created by QHT on 2017-02-27.
 */
public class ToastUtil {

    private static Toast toast;
    /**
     * 自定义Toast
     *
     * @param message
     */
    public static void showToastShort(CharSequence message) {
        if (toast == null) {
            toast = Toast.makeText(MyApplication.getInstance(),
                    message,
                    Toast.LENGTH_SHORT);
        } else {
            toast.setText(message);
        }
        toast.show();
    }

      public static void  showToastLong(CharSequence message) {
        if (toast == null) {
            toast = Toast.makeText(MyApplication.getInstance(),
                    message,
                    Toast.LENGTH_LONG);
        } else {
            toast.setText(message);
        }
        toast.show();
    }
}

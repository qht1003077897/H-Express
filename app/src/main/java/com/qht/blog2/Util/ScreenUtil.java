package com.qht.blog2.Util;

import android.app.Activity;
import android.util.DisplayMetrics;

/**
 * Created by QHT on 2017-02-27.
 */
public  class ScreenUtil {

    /**
     * 获取屏幕尺寸*/
    private static void getDispaly(Activity context) {//此处参数只能是Activity
        // TODO Auto-generated method stub
        DisplayMetrics displayMetrics = new DisplayMetrics();
        context.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int displayWidth = displayMetrics.widthPixels;
        int displayHeight = displayMetrics.heightPixels;
    }

}

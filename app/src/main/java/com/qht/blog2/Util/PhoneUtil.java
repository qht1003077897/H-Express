package com.qht.blog2.Util;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

/**
 * Created by QHT on 2017-02-27.
 */

public  class PhoneUtil {


        /**
         * 获取屏幕尺寸
         */
        public static void getDispaly(Activity context) {//此处参数只能是Activity
        // TODO Auto-generated method stub
        DisplayMetrics displayMetrics = new DisplayMetrics();
        context.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int displayWidth = displayMetrics.widthPixels;
        int displayHeight = displayMetrics.heightPixels;
        }

        /**
         * 强制隐藏键盘
         */
        public static void hideInputWindow(Context context,View view) {
         InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
         imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

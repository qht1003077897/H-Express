package com.qht.blog2.Application;

import com.amitshekhar.DebugDB;
import com.qht.blog2.Net.OK_LoggingInterceptor;
import com.qht.blog2.Util.LogUtil;
import com.qht.blog2.Util.SharePreferenceUtil;
import com.zhy.http.okhttp.OkHttpUtils;

import org.litepal.LitePalApplication;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * Created by QHT on 2017-02-27.
 */

public class MyApplication  extends LitePalApplication {

    protected static MyApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance =this;
        LogUtil.e(DebugDB.getAddressLog());
        SharePreferenceUtil.initSharePreferenceUtil(getApplicationContext());
        OkHttpClient okHttpClient = new OkHttpClient.Builder()//init OkHttp3.0
                .connectTimeout(8000L, TimeUnit.MILLISECONDS)
                .readTimeout(8000L, TimeUnit.MILLISECONDS)
                .retryOnConnectionFailure(true)
                .addInterceptor(new OK_LoggingInterceptor())
                //其他配置
                .build();

        OkHttpUtils.initClient(okHttpClient);

    }

    public static MyApplication getInstance() {
        return instance;
    }
}

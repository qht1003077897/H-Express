package com.qht.blog2.Net;

import android.content.Context;

import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.builder.GetBuilder;
import com.zhy.http.okhttp.builder.PostStringBuilder;
import com.zhy.http.okhttp.callback.Callback;
import java.io.IOException;
import java.util.HashMap;
import okhttp3.MediaType;
import okhttp3.Response;

/**
 * Created by QHT on 2017-04-05.
 *
 * GET,PUT等网络操作类
 */
public class Ok_Request {

    public static MediaType JSON = MediaType.parse("application/json;charset=utf-8");


    /**
     * GET方式
     * 异步get请求
     * @param url
     */

    public static   void getAsyncData(Context mContext, HashMap<String,String> map,final String url, Callback callback) {
       GetBuilder builder= OkHttpUtils
                .get()
                .params(map);
        if(null==mContext){
             builder.url(url)
                    .build()
                    .execute(callback);
        }else{
                     builder
                    .tag(mContext)
                    .url(url)
                    .build()
                    .execute(callback);
        }

    }

    /**
     * POST  JSON方式
     * @param <T>
     * @param  url
     * */
    public static <T> void  postAsyncData(Context mContext,HashMap<String,String> map,final String url,T t,Callback callback) {

        PostStringBuilder builder=OkHttpUtils
                .postString()
                .url(url);
        if(null==mContext){
                    builder
                    .mediaType(JSON)
                    .content(new Gson().toJson(t))
                    .build()
                    .execute(callback);
        }else{
                    builder
                    .tag(mContext)
                    .mediaType(JSON)
                    .content(new Gson().toJson(t))
                    .build()
                    .execute(callback);
        }
    }
    /**
     * 同步get请求
     * */
    public  static String  getSyncData(Context mContext,HashMap<String,String> map,final String url) {
        String results ="" ;
        Response response;
        try {
            response = OkHttpUtils
                    .get()
                    .params(map)
                    .tag(mContext)
                    .url(url)
                    .build().execute();
            results = response.body().string();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return results;
    }
}

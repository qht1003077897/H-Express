package com.qht.blog2.OtherActivity.slide_Left.weather;

import com.qht.blog2.BaseEventBus.EventBusUtil;
import com.qht.blog2.Net.Ok_Request;
import com.qht.blog2.Util.UrlUtil;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.Call;

/**
 * Created by QHT on 2017-04-27.
 */
public class Weather {


    public static void getweather(){


        Ok_Request.getAsyncData(null, null, UrlUtil.WEATHERADD, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
            }

            @Override
            public void onResponse(String response, int id) {
                JSONObject jsonObject = null;
                try {
                    jsonObject = new JSONObject(response);
                    JSONObject object1 = jsonObject.getJSONObject("result");
                    String city = object1.getString("citynm");
                    String temperature = object1.getString("temp_curr");
                    EventBusUtil.postSync(new weatherEvent(city,temperature,null));
                } catch (JSONException e) {
                    e.printStackTrace();
                }



            }
        });
    }
}

package com.qht.blog2.Net;

import com.qht.blog2.BaseBean.OrderInfoBean;
import com.qht.blog2.Util.GsonUtils;
import com.zhy.http.okhttp.callback.Callback;
import okhttp3.Response;

/**
 * Created by QHT on 2017-04-05.
 */
public abstract  class MyStringCallBack  extends Callback<OrderInfoBean>{
    /**
     * Thread Pool Thread
     *
     * @param response
     * @param id
     */
    @Override
    public OrderInfoBean parseNetworkResponse(Response response, int id) throws Exception {
        String temp=response.body().string();
        OrderInfoBean baseData = GsonUtils.jsonToModel(temp, OrderInfoBean.class);
        return baseData;
    }

}

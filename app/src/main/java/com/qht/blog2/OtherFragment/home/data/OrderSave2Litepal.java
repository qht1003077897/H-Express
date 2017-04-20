package com.qht.blog2.OtherFragment.home.data;

import android.content.ContentValues;

import com.qht.blog2.BaseBean.OrderInfoBean;
import com.qht.blog2.BaseBean.OrderInfoLitePal;
import com.qht.blog2.Util.LogUtil;
import com.qht.blog2.Util.TimeUtil;

import org.litepal.crud.DataSupport;
import org.litepal.exceptions.DataSupportException;

import java.util.List;

/**
 * Created by QHT on 2017-04-18.
 * 查询成功的数据都加入到数据库中(只保存nu,state,com)
 * 每次查询先去数据库查找查询过没有，若查询过，则删除上次查询记录，更新状态
 */
public class OrderSave2Litepal {


    public static void savequery(OrderInfoBean response) {

        if(response.getMessage().equals("ok")){
            List<OrderInfoLitePal> List = DataSupport
                    .where("nu = ? ", response.getNu()).find(OrderInfoLitePal.class);
            for (OrderInfoLitePal bean: List) {
                if(bean.getNu().equals(response.getNu())){
                    //每查询一次，及时更新订单状态和查看时间
                    ContentValues values = new ContentValues();
                    values.put("state", response.getState());
                    values.put("time", TimeUtil.getNowTime());
                    DataSupport.update(OrderInfoLitePal.class, values, 1);
                }
            }

            if(List.size()<=0){
                OrderInfoLitePal bean=new OrderInfoLitePal();
                bean.setCom(response.getCom());
                bean.setNu(response.getNu());
                bean.setState(response.getState());
                bean.setTime(TimeUtil.getNowTime());
                try {
                    bean.saveThrows();
                }catch (DataSupportException E){
                    LogUtil.e(E.getMessage());
                }
            }
        }
    }
}

package com.qht.blog2.Util;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by QHT on 2017-04-17.
 * 用来处理Gson转型泛型的时候报错LinkTreeMap不能转换为list<E>
 */
public class  Gson_ErrorLinkTreeMap_Helper<E> {

    public static <E> List<E> duleTree2List(List<E> list,Class<E> clazz){
        List<E> lists = new ArrayList<E>();
        if(null!=list && list.size()>0){
        String liststr = GsonUtils.ListTojson(list);
        lists = GsonUtils.jsonToList(liststr, clazz);
        }
        return lists;
    }
}

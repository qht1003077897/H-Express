package com.qht.blog2.OtherFragment.home.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by QHT on 2017-04-13.
 */
public class CompanySingleton {

    private static CompanySingleton Instance=new CompanySingleton();

    public  HashMap<String,String> Companymap=new HashMap<String,String>();
    public  List<String> Companylist=new ArrayList<String>();

    private CompanySingleton(){
        getCampaniles();
    }

    public static CompanySingleton getInstance(){

        return Instance;
    }


    private  List<String> getCampaniles(){
        Companymap.put("申通","shentong");
        Companymap.put("EMS","ems");
        Companymap.put("顺丰","shunfeng");
        Companymap.put("圆通","yuantong");
        Companymap.put("中通","zhongtong");
        Companymap.put("韵达","yunda");
        Companymap.put("天天","tiantian");
        Companymap.put("汇通","huitongkuaidi");
        Companymap.put("全峰","quanfengkuaidi");
        Companymap.put("德邦","debangwuliu");
        Companymap.put("宅急送","zhaijisong");
        Companymap.put("百世汇通","huitongkuaidi");

        Iterator it=Companymap.entrySet().iterator();
        while(it.hasNext()){
            Map.Entry entry=(Map.Entry)it.next();
            String key= (String) entry.getKey();
            Companylist.add(key);
        }
        return Companylist;
    }
}

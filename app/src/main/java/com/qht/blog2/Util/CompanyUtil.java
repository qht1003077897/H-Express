package com.qht.blog2.Util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by QHT on 2017-04-13.
 */
public class CompanyUtil {

    private static CompanyUtil Instance =new CompanyUtil();

    public  HashMap<String,String> Companymap=new HashMap<String,String>();
    public  List<String> Companylist=new ArrayList<String>();

    private CompanyUtil(){
        getCampaniles();
    }

    public static CompanyUtil getInstance(){

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

    /**
     * 由品牌名得到头像拼音  供头像使用
     */
    public static String getCampanilesPinyin(String hanzi) {
        String pinyin=null;

        switch (hanzi) {
            case "申通":
                pinyin= "shentong";
                break;

            case "EMS":
                pinyin= "ems";
                break;

            case "顺丰":
                pinyin= "shunfeng";
                break;

            case "圆通":
                pinyin= "yuantong";
                break;

            case "中通":
                pinyin= "zhongtong";
                break;

            case "韵达":
                pinyin= "yunda";
                break;

            case "天天":
                pinyin= "tiantian";
                break;

            case "汇通":
                pinyin= "huitongkuaidi";
                break;

            case "全峰":
                pinyin= "quanfengkuaidi";
                break;

            case "德邦":
                pinyin= "debangwuliu";
                break;

            case "宅急送":
                pinyin= "zhaijisong";
                break;

            case "百世汇通":
                pinyin= "huitongkuaidi";
                break;
        }
        return pinyin;
    }


    /**
     * 由品牌名得到时效时间
     */
    public static String CaseSx(String brand,HashMap<String,String> map) {
        String sx="";

        switch (brand) {
            case "申通":
                sx= map.get("申通快递");
                break;

            case "EMS":
                sx= map.get("EMS");
                break;

            case "顺丰":
                sx= map.get("顺丰速运");
                break;

            case "圆通":
                sx= map.get("圆通速递");
                break;

            case "中通":
                sx= map.get("中通速递");
                break;

            case "韵达":
                sx= map.get("韵达快运");
                break;

            case "天天":
                sx= map.get("天天快递");
                break;
            case "汇通":
                sx= map.get("汇通快运");
                break;
            case "宅急送":
                sx= map.get("宅急送");
                break;
            default:
                sx= map.get("宅急送");
                break;
        }
        return sx;
    }
}

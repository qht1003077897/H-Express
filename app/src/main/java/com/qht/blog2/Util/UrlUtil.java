package com.qht.blog2.Util;

/**
 * Created by QHT on 2017-04-05.
 */
public class UrlUtil {

    /** 从快递100 查询快递进度 */
    public static String GetKuaiDi="http://www.kuaidi100.com/query";

    /** 从快递100 获取各快递公司头像   https://cdn.kuaidi100.com/images/all/56/yunda.png */
    public static String GetBASEKuaiDiCOMIMAGE="https://cdn.kuaidi100.com/images/all/56/";

    /** k780  天气接口 */
    public static final String WEATHERADD = "http://api.k780.com:88/?app=weather.today&weaid="
            + "101110101"
            + "&appkey=15384&sign=e776696f34c04ebada95e0bdf14cbc7c&format=json";
}

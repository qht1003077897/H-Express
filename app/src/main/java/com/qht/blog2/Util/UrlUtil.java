package com.qht.blog2.Util;

/**
 * Created by QHT on 2017-04-05.
 */
public class UrlUtil {

    /**
     *  从快递100 查询快递进度
     */
    public static String GetKuaiDi="http://www.kuaidi100.com/query";


    /** 从快递100 获取各快递公司头像
     *  https://cdn.kuaidi100.com/images/all/56/yunda.png
     */
    public static String GetBASEKuaiDiCOMIMAGE="https://cdn.kuaidi100.com/images/all/56/";

    /** 快递助手  基础URL
     *
     *  完整地址（带详情查询：虹桥）:http://www.kuaidihelp.com/wangdian-2--xq-cy-虹桥-2/
     *  完整地址（不带详情查询）:http://www.kuaidihelp.com/wangdian-2--xq-cy--2/
     *  完整地址（默认第一页）: http://www.kuaidihelp.com/wangdian-16--xq-cy--/
     */
    public static String baseUrl="http://www.kuaidihelp.com";

    /** 快递助手 页码url,通过爬取页面获得 /wangdian-8072--xq-cy--1/
     *  /wangdian-8072--xq-cy--2/ 等等
     */
    public static String pageUrl="/wangdian-8072--xq-cy--/";

    /**
     *  时效查询 baseurl ,通过爬取页面获得
     */
    public static String SxQueryUrl="http://www.kuaidihelp.com/sxchaxun/getshixiao?com=";
    public static String testURL="http://www.kuaidihelp.com/sxchaxun/getshixiao?com=";
    /**
     * k780  天气接口
     */
    public static final String WEATHERADD = "http://api.k780.com:88/?app=weather.today&weaid="
            + "101110101"
            + "&appkey=15384&sign=e776696f34c04ebada95e0bdf14cbc7c&format=json";


}

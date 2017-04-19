package com.qht.blog2.OtherActivity.orderdetail.data;

/**
 * Created by QHT on 2017-04-17.
 */
public class OrderState {

    public static  class ItemType {
        public final static int NORMAL = 0;
        public final static int START = 4;
        public final static int END = 8;
        public final static int ATOM = 16;
    }



    public static String caseState(String state) {
      String statu=null;

        switch (state) {
            case "0":
                statu= "正在途中";
            break;

            case "1":
                statu= "揽件中";
            break;

            case "2":
                statu= "问题订单";
            break;

            case "3":
                statu= "已签收";
            break;

            case "4":
                statu= "已退签";
            break;

            case "5":
                statu= "已派件";
            break;

            case "6":
                statu= "已退回";
            break;
        }
        return statu;
    }
}

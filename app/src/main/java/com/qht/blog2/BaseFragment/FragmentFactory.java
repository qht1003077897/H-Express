package com.qht.blog2.BaseFragment;

import android.support.v4.app.Fragment;

import com.qht.blog2.OtherFragment.order.orderAll.UI.FragmentOrder_ALL;
import com.qht.blog2.OtherFragment.order.orderSigned.UI.FragmentOrder_Signed;
import com.qht.blog2.OtherFragment.order.orderUnSign.UI.FragmentOrder_UnSign;
import com.qht.blog2.OtherFragment.send.sendOrder.UI.FragmentSend_Company;
import com.qht.blog2.OtherFragment.send.sendNetSite.UI.FragmentSend_NearBy;
import com.qht.blog2.OtherFragment.send.sendCustomerSer.UI.FragmentSend_Reserve;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by QHT on 2017-04-11.
 */
public class FragmentFactory {

    private List<Fragment> fragments=new ArrayList<Fragment>();

    /**
     * 添加或者显示fragment
     *
     * @param position
     */
    public static Fragment ShowOrderFragment(int position) {

         Fragment  currentFragment=null;
        switch (position){
         case 0:
             currentFragment=new FragmentOrder_ALL();
             break;
         case 1:
             currentFragment=new FragmentOrder_Signed();
             break;
         case 2:
             currentFragment=new FragmentOrder_UnSign();
             break;
         default:
             break;
     }
        return currentFragment;
    }

    public static Fragment ShowSendFragment(int position) {

        Fragment  currentFragment=null;
        switch (position){
            case 0:
                currentFragment=new FragmentSend_NearBy();
                break;
            case 1:
                currentFragment=new FragmentSend_Company();
                break;
            case 2:
                currentFragment=new FragmentSend_Reserve();
                break;
            default:
                break;
        }
        return currentFragment;
    }
}

package com.qht.blog2.OtherFragment.send.sendOrder.UI;

import android.app.Activity;
import android.content.Context;

import com.qht.blog2.BaseFragment.BaseFragment;
import com.qht.blog2.R;


public class FragmentSend_Company extends BaseFragment {

    private Context mActivity;

    @Override
    public int getContentViewId() {
        return R.layout.fragment_send__company;
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.mActivity=activity;
    }
}

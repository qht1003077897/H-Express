package com.qht.blog2.OtherFragment.order.orderAll.UI;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qht.blog2.BaseFragment.BaseFragment;
import com.qht.blog2.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentOrder_ALL extends BaseFragment {

    @BindView(R.id.rv_orderall)
    RecyclerView rvOrderall;

    /**
     * 设置根布局资源id
     *
     * @return
     */
    @Override
    public int getContentViewId() {
        return R.layout.fragment_order_all;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

}

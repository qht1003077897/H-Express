package com.qht.blog2.OtherFragment.order.orderSigned.UI;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qht.blog2.BaseBean.OrderInfoLitePal;
import com.qht.blog2.BaseFragment.BaseFragment;
import com.qht.blog2.OtherFragment.order.orderSigned.adapter.OrderSigned_RV_Adapter;
import com.qht.blog2.R;

import org.litepal.crud.DataSupport;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentOrder_Signed extends BaseFragment {


    @BindView(R.id.rv_fragment_order_signed)
    RecyclerView rvFragmentOrderSigned;

    private Activity mActivity;

    /**
     * 设置根布局资源id
     *
     * @return
     */
    @Override
    public int getContentViewId() {
        return R.layout.fragment_order_signed;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        initData();
        return rootView;
    }

    private void initData() {
        //状态为3 则已签收
        List<OrderInfoLitePal> list = DataSupport
                .where("state like ? ", "%" + "3" + "%" ).find(OrderInfoLitePal.class);
        rvFragmentOrderSigned.setLayoutManager(new LinearLayoutManager(mActivity));
        OrderSigned_RV_Adapter  madapter = new OrderSigned_RV_Adapter(list, mActivity);
        rvFragmentOrderSigned.setAdapter(madapter);
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.mActivity=activity;
    }
}

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
import com.qht.blog2.BaseEventBus.EventBusUtil;
import com.qht.blog2.BaseFragment.BaseFragment;
import com.qht.blog2.OtherFragment.order.FragmentSecond;
import com.qht.blog2.OtherFragment.order.event.OrderSignedEvent;
import com.qht.blog2.OtherFragment.order.orderSigned.adapter.OrderSigned_RV_Adapter;
import com.qht.blog2.R;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
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
    private OrderSigned_RV_Adapter  madapter;
    private List<OrderInfoLitePal> list;
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
         list = DataSupport
                .where("state = ? ",  "3" ).find(OrderInfoLitePal.class);
        rvFragmentOrderSigned.setLayoutManager(new LinearLayoutManager(mActivity));
        madapter = new OrderSigned_RV_Adapter(list, mActivity);
        rvFragmentOrderSigned.setAdapter(madapter);

    }

    /**
     * 接收消息函数在主线程,当切换到已签收页面时才执行右移动画
     * From: FragmentSecond.onPageSelected()
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(OrderSignedEvent response) {
        // from :MainActivity.onClick()  编辑动作
        if(FragmentSecond.viewPagePosition==1 && response.from.equals("MainActivity")){
            if(list.size()>0){
                if(response.needclose){
                    madapter.slideClose();
                    notifydata(false);
                    }else{
                    madapter.slideOpen();
                }
            }
        }
        // from :FragmentSecond.onPageSelected()
        //response.position==1 如果lastViewPageIndex为本页面，则关闭动画，并且全部字段置为未选择，相当于初始化
        else if(response.position==1 && response.from.equals("FragmentSecond")){
            InitStatus(true,false);
        }
            // from :MainActivity.onClick()
            //response.position==1 意味着在本页面，全部字段置为true
        else if(FragmentSecond.viewPagePosition==1 && response.from.equals("MainActivity_Allselect")){
            InitStatus(response.needclose,response.needselect);
        }
        // from :MainActivity.reViewStatus()
        //来自MainActivity的初始化请求(因为底部fragmnet切换)
        else if(response.from.equals("MainActivityInit")){
            InitStatus(true,false);
        }
    }
        /**
        *页面切换情况下返回初始化状态
        */
        public void  InitStatus(boolean isneedclose,boolean needselect){
            if(list.size()>0){
                if(isneedclose){
                    madapter.slideClose();
                }
                notifydata(needselect);
            }
        }
        public void notifydata(boolean needselect){
            for (OrderInfoLitePal order: list) {
                order.setIsselect(needselect);
            }
            madapter.notifyDataSetChanged();
        }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.mActivity=activity;
    }
    @Override
    public void onStart() {
        super.onStart();
        EventBusUtil.register(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBusUtil.unregister(this);
    }
}

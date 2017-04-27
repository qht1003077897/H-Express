package com.qht.blog2.OtherFragment.order.orderUnSign.UI;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.baoyz.widget.PullRefreshLayout;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.qht.blog2.BaseBean.OrderInfoBean;
import com.qht.blog2.BaseBean.OrderInfoLitePal;
import com.qht.blog2.BaseEventBus.EventBusUtil;
import com.qht.blog2.BaseFragment.BaseFragment;
import com.qht.blog2.Net.MyStringCallBack;
import com.qht.blog2.Net.Ok_Request;
import com.qht.blog2.OtherActivity.orderdetail.UI.OrderDetailActivity;
import com.qht.blog2.OtherActivity.orderdetail.data.OrderDetailEvent;
import com.qht.blog2.OtherFragment.home.data.OrderSave2Litepal;
import com.qht.blog2.OtherFragment.order.FragmentSecond;
import com.qht.blog2.OtherFragment.order.event.OrderEvent;
import com.qht.blog2.OtherFragment.order.orderUnSign.adapter.OrderUnSign_RV_Adapter;
import com.qht.blog2.OtherFragment.order.orderUnSign.data.OrderUnSignEvent;
import com.qht.blog2.R;
import com.qht.blog2.Util.DialogUtil;
import com.qht.blog2.Util.LogUtil;
import com.qht.blog2.Util.TextUtil;
import com.qht.blog2.Util.ToastUtil;
import com.qht.blog2.Util.UrlUtil;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Request;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentOrder_UnSign extends BaseFragment {


    @BindView(R.id.rv_orderunsign)
    RecyclerView      rvOrderunsign;
    @BindView(R.id.btn_orderunsign_delete)
    Button            btnOrderunsignDelete;
    @BindView(R.id.ll_orderunsign)
    LinearLayout      llOrderunsign;
    @BindView(R.id.swipeRefreshLayout_orderunsign)
    PullRefreshLayout swipeRefreshLayoutOrderunsign;

    private Activity               mActivity;
    private OrderUnSign_RV_Adapter madapter;
    private List<OrderInfoLitePal> list= new ArrayList<OrderInfoLitePal>();;

    /**
     * 设置根布局资源id
     *
     * @return
     */
    @Override
    public int getContentViewId() {
        return R.layout.fragment_order_unsign;
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
        //获取未签收条目
        QueryData();
        rvOrderunsign.setLayoutManager(new LinearLayoutManager(mActivity));
        madapter = new OrderUnSign_RV_Adapter(list, mActivity);

        swipeRefreshLayoutOrderunsign.setOnRefreshListener(new PullRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayoutOrderunsign.post(new Runnable() {
                    @Override
                    public void run() {
                        QueryData();
                        notifydata();
                        swipeRefreshLayoutOrderunsign.setRefreshing(false);
                    }
                });
            }
        });
        madapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                LogUtil.e("onItemChildClick");
                OrderInfoLitePal bean= (OrderInfoLitePal)adapter.getData().get(position);
                RequestNet(bean.getNu(),bean.getCom());
            }
        });
        rvOrderunsign.setAdapter(madapter);
    }

    private void RequestNet(String nu,String com){
        if(TextUtil.isEmpty(nu) || TextUtil.isEmpty(com)){
            return;
        }
        HashMap<String, String> map = new HashMap<>();
        map.put("type", com);//参数
        map.put("postid", nu);
        Ok_Request.getAsyncData(getActivity(), map, UrlUtil.GetKuaiDi, new MyStringCallBack() {
            /**
             * UI Thread
             */
            @Override
            public void onBefore(Request request, int id) {
                DialogUtil.showProgressDialog(getActivity(), true);
            }

            @Override
            public void onAfter(int id) {
                DialogUtil.hideProgressDialog();
            }

            @Override
            public void onError(Call call, Exception e, int id) {
                ToastUtil.showToastLong(e.getMessage());
            }

            @Override
            public void onResponse(OrderInfoBean response, int id) {
                if (response != null) {
                    EventBusUtil.postSticky(new OrderDetailEvent(response,mActivity));
                    OrderSave2Litepal.savequery(response);
                    gotoActivity(mActivity, OrderDetailActivity.class);
                }
            }
        });
    }

    /**
     * From: OrderUnSign_RV_Adapter
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventOrder_UnSign(OrderUnSignEvent response) {
        // from :OrderUnSign_RV_Adapter  checkbox 被选择，改变islect字段
        if (response.checked) {
            llOrderunsign.setVisibility(View.VISIBLE);
        }
        list.get(response.position).setIsselect(response.checked);

    }

    /**
     * 接收消息函数在主线程,当切换到未签收页面时才执行右移动画
     * From: FragmentSecond.onPageSelected()
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(OrderEvent response) {
        // from :MainActivity.onClick()  编辑动作
        if (FragmentSecond.viewPagePosition == 2 && response.from.equals("MainActivity")) {
            if (list.size() > 0) {
                if (response.needclose) {
                    madapter.slideClose();
                    for (OrderInfoLitePal order : list) {
                        order.setIsselect(false);
                    }
                    notifydata();
                    llOrderunsign.setVisibility(View.GONE);
                } else {
                    madapter.slideOpen();
                }
            }
        }
        // from :FragmentSecond.onPageSelected()
        //response.position==2 如果lastViewPageIndex为本页面，则关闭动画，并且全部字段置为未选择，相当于初始化
        else if (response.position == 2 && response.from.equals("FragmentSecond")) {
            InitStatus(true, false);
            llOrderunsign.setVisibility(View.GONE);
        }
        // from :MainActivity.onClick()
        //response.position==2 意味着在本页面，全部字段置为true
        else if (FragmentSecond.viewPagePosition == 2 && response.from.equals("MainActivity_Allselect")) {
            InitStatus(response.needclose, response.needselect);
            llOrderunsign.setVisibility(View.VISIBLE);
        }
        // from :MainActivity.reViewStatus()
        //来自MainActivity的初始化请求(因为底部fragmnet切换)
        else if (response.from.equals("MainActivityInit")) {
            InitStatus(true, false);
            llOrderunsign.setVisibility(View.GONE);
        }
    }

    /**
     * 页面切换情况下返回初始化状态
     */
    public void InitStatus(boolean isneedclose, boolean needselect) {
        if (list.size() > 0) {
            if (isneedclose) {
                madapter.slideClose();
            }
            for (OrderInfoLitePal order : list) {
                order.setIsselect(needselect);
            }
            notifydata();
        }
    }

    public void notifydata() {
        madapter.notifyDataSetChanged();
    }

    @OnClick(R.id.btn_orderunsign_delete)
    public void onClick() {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).isselect()) {
                int id = list.get(i).getId();
                list.remove(i);
                madapter.notifyItemRemoved(i);
                DataSupport.delete(OrderInfoLitePal.class, id);
            }
        }
    }

    public void QueryData() {
        list.clear();
        List<OrderInfoLitePal> lists=new ArrayList<OrderInfoLitePal>();
        lists = DataSupport
                .where("state != ? ", "3").find(OrderInfoLitePal.class);
        list.addAll(lists);
    }

//    /**
//     * 在viewpage切换的情况下重新从数据库读取数据
//     */
//    @Override
//    public void setUserVisibleHint(boolean isVisibleToUser) {
//        super.setUserVisibleHint(isVisibleToUser);
//        if (isVisibleToUser) {
//            QueryData();
//        }
//
//    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.mActivity = activity;
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

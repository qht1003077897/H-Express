package com.qht.blog2.OtherActivity.orderdetail.UI;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.nightonke.boommenu.BoomButtons.OnBMClickListener;
import com.nightonke.boommenu.BoomButtons.TextInsideCircleButton;
import com.nightonke.boommenu.BoomMenuButton;
import com.qht.blog2.BaseActivity.ToolBarActivity;
import com.qht.blog2.BaseBean.OrderInfoDetailBean;
import com.qht.blog2.BaseEventBus.EventBusUtil;
import com.qht.blog2.OtherActivity.orderdetail.adapter.OrderDetail_RV_Adapter;
import com.qht.blog2.OtherActivity.orderdetail.data.OrderDetailEvent;
import com.qht.blog2.OtherActivity.orderdetail.data.OrderState;
import com.qht.blog2.R;
import com.qht.blog2.Util.DialogUtil;
import com.qht.blog2.Util.Gson_ErrorLinkTreeMap_Helper;
import com.qht.blog2.Util.TimeUtil;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OrderDetailActivity extends ToolBarActivity {

    @BindView(R.id.Iv_activity_orderdetail_head)
    ImageView      IvActivityOrderdetailHead;
    @BindView(R.id.tv_activity_orderdetail_head_status)
    TextView       tvActivityOrderdetailHeadStatus;
    @BindView(R.id.tv_activity_orderdetail_head_remark)
    TextView       tvActivityOrderdetailHeadRemark;
    @BindView(R.id.tv_activity_orderdetail_head_num)
    TextView       tvActivityOrderdetailHeadNum;
    @BindView(R.id.tv_activity_orderdetail_time)
    TextView       tvActivityOrderdetailTime;
    @BindView(R.id.rv_activity_orderdetail)
    RecyclerView   rv_activity_orderdetail;
    @BindView(R.id.bmb)
    BoomMenuButton bmb;

    private String                 state;
    private OrderDetail_RV_Adapter madapter;
    private List datas = new ArrayList<OrderInfoDetailBean>();


    @Override
    public int getContentViewId() {
        return R.layout.activity_order_detail;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        getToolbarTitle().setText("订单详情");
        initMenu();
    }

    private void initMenu() {
        for (int i = 0; i < bmb.getPiecePlaceEnum().pieceNumber(); i++) {
            TextInsideCircleButton.Builder builder = new TextInsideCircleButton.Builder()
                    .listener(new OnBMClickListener() {
                        @Override
                        public void onBoomButtonClick(int index) {
                         switch (index){
                             case 0:
                              case 1:
                             case 2:
                                 showEditDialog();
                            }
                        }
                    })
                    .normalImageRes(getDrawable().get(i))
                    .normalText(getText().get(i))
                    .typeface(Typeface.DEFAULT_BOLD)
                    .textSize(12)
                    .rotateImage(false)
                    .rotateText(false);
                 bmb.addBuilder(builder);
        }
    }
    private  List<Integer> getDrawable(){
        List<Integer> draw=new ArrayList<Integer>();
        draw.add(R.mipmap.activity_orderdetail_order_unsign);
        draw.add(R.mipmap.activity_orderdetail_order_sign);
        draw.add(R.mipmap.activity_orderdetail_order_remark);
        return draw;
    }
    private  List<String> getText(){
        List<String> TEXT=new ArrayList<String>();
        TEXT.add("未签收");
        TEXT.add("已签收");
        TEXT.add("备注");
        return TEXT;
    }

    private void showEditDialog() {
        DialogUtil.showAlertDialogEdit(OrderDetailActivity.this, "编辑备注", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        },null);
    }

    /**
     * 接收消息函数在主线程，且为粘性事件
     */
    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onEvent(OrderDetailEvent response) {
        datas = Gson_ErrorLinkTreeMap_Helper.duleTree2List(response.Data.getData(), OrderInfoDetailBean.class);
        setHeadView(response);
        setRecycleView(response);
    }

    /**
     * 设置头部
     */
    @SuppressLint("SetTextI18n")
    private void setHeadView(OrderDetailEvent response) {
        if (null != datas && datas.size() > 0) {
            tvActivityOrderdetailHeadStatus.setText(OrderState.caseState(response.Data.getState()));
            tvActivityOrderdetailHeadNum.setText("快递单号：" + response.Data.getNu());
            int i = datas.size() - 1;//开始时间为最后一条信息
            OrderInfoDetailBean bean = (OrderInfoDetailBean) datas.get(i);
            String startTime = bean.getTime();
            int j = 0;//结束时间为第0条信息
            OrderInfoDetailBean bean2 = (OrderInfoDetailBean) datas.get(j);
            String endTime = bean2.getTime();
            tvActivityOrderdetailTime.setText("耗时：" + TimeUtil.getTimeDifferenceDay(startTime, endTime));
        }
    }

    /**
     * 设置列表
     */
    private void setRecycleView(OrderDetailEvent response) {
        if (null != datas && datas.size() > 0) {
            state = response.Data.getState();
            rv_activity_orderdetail.setLayoutManager(new LinearLayoutManager(this));
            madapter = new OrderDetail_RV_Adapter(datas, state, OrderDetailActivity.this);
            rv_activity_orderdetail.setAdapter(madapter);
        }
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

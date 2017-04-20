package com.qht.blog2.OtherFragment.order.orderAll.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.qht.blog2.BaseAdapter.BaseSlideRecycleView.ISlideHelper;
import com.qht.blog2.BaseAdapter.BaseSlideRecycleView.holder.SlideViewHolder;
import com.qht.blog2.BaseBean.OrderInfoLitePal;
import com.qht.blog2.OtherActivity.orderdetail.data.OrderState;
import com.qht.blog2.R;

import java.util.List;

/**
 * Created by QHT on 2017-04-18.
 *
 * 参考:  https://github.com/ruzhan123/ViewHolder-Slide-Helper
 * 自己结合 BaseQuickAdapter
 */
public class OrderAll_RV_Adapter extends BaseQuickAdapter<OrderInfoLitePal> {

    private ISlideHelper mISlideHelper = new ISlideHelper();
    private Context context;

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        OneSlideViewHolder oneSlideViewHolder = new OneSlideViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_order_signed_rv_item, parent, false));
        //add holder
        mISlideHelper.add(oneSlideViewHolder);
        return oneSlideViewHolder;
    }

    public OrderAll_RV_Adapter(List<OrderInfoLitePal> list, Context context) {
        super(list);
        this.context=context;
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, OrderInfoLitePal orderInfoLitePal) {
        ((OneSlideViewHolder) baseViewHolder).bind();
        baseViewHolder.setText(R.id.tv_fragment_order_signed_com,orderInfoLitePal.getCom());
        baseViewHolder.setText(R.id.tv_fragment_order_signed_num,orderInfoLitePal.getNu());
        baseViewHolder.setText(R.id.tv_fragment_order_signed_state, OrderState.caseState(orderInfoLitePal.getState()));
        baseViewHolder.setText(R.id.tv_fragment_order_signed_time,orderInfoLitePal.getTime());
        baseViewHolder.setChecked(R.id.cb_fragment_order_signed_select,orderInfoLitePal.isselect());
    }
    public void slideOpen() {
        mISlideHelper.slideOpen();
    }

    public void slideClose() {
        mISlideHelper.slideClose();
    }

    public class OneSlideViewHolder extends SlideViewHolder {

        private View     mContentRl;
        public OneSlideViewHolder(View itemView) {
            super(itemView);
            mContentRl = itemView.findViewById(R.id.rl_fragment_order_signed);
        }

        public void bind() {
            //slide offset
            setOffset(50);
            //slide must call,param is slide view
            onBindSlide(mContentRl);
        }

        @Override
        public void doAnimationSet(int offset, float fraction) {
             mContentRl.scrollTo(offset, 0);
        }

        @Override
        public void onBindSlideClose(int state) {

        }

        @Override
        public void doAnimationSetOpen(int state) {

        }


    }
}

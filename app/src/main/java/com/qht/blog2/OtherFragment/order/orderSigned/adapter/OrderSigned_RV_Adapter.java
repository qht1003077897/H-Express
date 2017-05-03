package com.qht.blog2.OtherFragment.order.orderSigned.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.qht.blog2.BaseAdapter.BaseSlideRecycleView.ISlideHelper;
import com.qht.blog2.BaseAdapter.BaseSlideRecycleView.holder.SlideViewHolder;
import com.qht.blog2.BaseBean.OrderInfoLitePal;
import com.qht.blog2.BaseEventBus.EventBusUtil;
import com.qht.blog2.OtherActivity.orderdetail.data.OrderState;
import com.qht.blog2.OtherFragment.order.orderSigned.data.OrderSignedEvent;
import com.qht.blog2.R;
import com.qht.blog2.Util.UrlUtil;

import java.util.List;

import jp.wasabeef.glide.transformations.CropCircleTransformation;

/**
 * Created by QHT on 2017-04-18.
 *
 * 参考:  https://github.com/ruzhan123/ViewHolder-Slide-Helper
 * 自己结合 BaseQuickAdapter
 */
public class OrderSigned_RV_Adapter extends BaseQuickAdapter<OrderInfoLitePal,OrderSigned_RV_Adapter.OneSlideViewHolder> {

    private ISlideHelper mISlideHelper = new ISlideHelper();
    private Context context;

    @Override
    public OneSlideViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        OneSlideViewHolder oneSlideViewHolder = new OneSlideViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_order_signed_rv_item, parent, false));
        //add holder
        mISlideHelper.add(oneSlideViewHolder);
        oneSlideViewHolder.setAdapter(this);
        return oneSlideViewHolder;
    }

    @Override
    protected void convert(final OneSlideViewHolder baseViewHolder, OrderInfoLitePal orderInfoLitePal) {
        ((OneSlideViewHolder) baseViewHolder).bind();
        baseViewHolder.setText(R.id.tv_fragment_order_signed_com,orderInfoLitePal.getCom());
        baseViewHolder.setText(R.id.tv_fragment_order_signed_num,orderInfoLitePal.getNu());
        baseViewHolder.setText(R.id.tv_fragment_order_signed_state, OrderState.caseState(orderInfoLitePal.getState()));
        baseViewHolder.setText(R.id.tv_fragment_order_signed_time,orderInfoLitePal.getTime());
        baseViewHolder.setChecked(R.id.cb_fragment_order_signed_select,orderInfoLitePal.isselect());
        baseViewHolder.addOnClickListener(R.id.rl_fragment_order_signed);
        Glide.with(context)
                .load(UrlUtil.GetBASEKuaiDiCOMIMAGE+orderInfoLitePal.getCom()+".png")
                .bitmapTransform(new CropCircleTransformation(context))
                .into(baseViewHolder.image);
        baseViewHolder.setOnCheckedChangeListener(R.id.cb_fragment_order_signed_select, new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                // To:FragmentOrder_Signed 改变list的isselect字段
                EventBusUtil.postSync(new OrderSignedEvent(baseViewHolder.getAdapterPosition(),b,this));
            }
        });
    }

    public OrderSigned_RV_Adapter(List<OrderInfoLitePal> list, Context context) {
        super(list);
        this.context=context;
    }

    public void slideOpen() {
        mISlideHelper.slideOpen();
    }

    public void slideClose() {
        mISlideHelper.slideClose();
    }

    public class OneSlideViewHolder extends SlideViewHolder {

        private View     mContentRl;
        private ImageView     image;

        public OneSlideViewHolder(View itemView) {
            super(itemView);
            mContentRl = itemView.findViewById(R.id.rl_fragment_order_signed);
            image = (ImageView)itemView.findViewById(R.id.iv_fragment_order_signed_image);
        }
        @Override
        protected BaseViewHolder setAdapter(BaseQuickAdapter adapter) {
            return super.setAdapter(adapter);
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

package com.qht.blog2.OtherFragment.send.sendNetSite.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.qht.blog2.BaseBean.OrderNetSiteBean;
import com.qht.blog2.R;
import com.qht.blog2.Util.CompanyUtil;
import com.qht.blog2.Util.UrlUtil;

import java.util.List;

import jp.wasabeef.glide.transformations.CropCircleTransformation;

/**
 * Created by QHT on 2017-05-02.
 *
 */

public class Send_Nearby_Adapter extends BaseQuickAdapter<OrderNetSiteBean,BaseViewHolder> {

    private Context context;

    public Send_Nearby_Adapter(List<OrderNetSiteBean> list, Context context) {
        super(R.layout.fragment_send_nearby_rv_item,list);
        this.context=context;
    }

    @Override
    protected void convert(final BaseViewHolder baseViewHolder, OrderNetSiteBean netsite) {
        baseViewHolder.setText(R.id.tv_fragment_send_nearby_title,netsite.getTitle());
        baseViewHolder.setText(R.id.tv_fragment_send_nearby_brand,netsite.getBrand());
        baseViewHolder.setText(R.id.tv_fragment_send_nearby_phone, netsite.getPhone());
        baseViewHolder.setText(R.id.tv_fragment_send_nearby_area,netsite.getArea());
        baseViewHolder.setText(R.id.tv_fragment_send_nearby_time,netsite.getAtime());
        baseViewHolder.addOnClickListener(R.id.iv_fragment_send_nearby_arrow);
        Glide.with(context)
                .load(UrlUtil.GetBASEKuaiDiCOMIMAGE+ CompanyUtil.getCampanilesPinyin(netsite.getBrand().substring(6))+".png")
                .bitmapTransform(new CropCircleTransformation(context))
                .placeholder(R.mipmap.zanwu)
                .crossFade()
                .into((ImageView)(baseViewHolder.getView(R.id.iv_fragment_send_nearby_image)));
    }

}

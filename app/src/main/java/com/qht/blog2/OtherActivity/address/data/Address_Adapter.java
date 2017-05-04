package com.qht.blog2.OtherActivity.address.data;

import android.content.Context;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.qht.blog2.BaseBean.AddressBean;
import com.qht.blog2.R;

import java.util.List;

/**
 * Created by QHT on 2017-05-02.
 *
 */

public class Address_Adapter extends BaseQuickAdapter<AddressBean,BaseViewHolder> {

    private Context context;

    public Address_Adapter(List<AddressBean> list, Context context) {
        super(R.layout.activity_address_rv_item,list);
        this.context=context;
    }

    @Override
    protected void convert(final BaseViewHolder baseViewHolder, AddressBean bean) {
        baseViewHolder.setText(R.id.tv_activity_address_name,bean.getName());
        baseViewHolder.setText(R.id.tv_activity_address_addr,bean.getCity()+bean.getAddress());
        baseViewHolder.addOnClickListener(R.id.iv_activity_address_detail);
    }
}

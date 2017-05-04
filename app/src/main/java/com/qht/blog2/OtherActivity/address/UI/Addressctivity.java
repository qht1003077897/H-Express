package com.qht.blog2.OtherActivity.address.UI;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.qht.blog2.BaseActivity.ToolBarActivity;
import com.qht.blog2.BaseBean.AddressBean;
import com.qht.blog2.OtherActivity.address.data.Address_Adapter;
import com.qht.blog2.R;
import com.qht.blog2.View.EmptyViewLayout;

import org.litepal.crud.DataSupport;

import java.util.List;

import butterknife.BindView;

public class Addressctivity extends ToolBarActivity {

    @BindView(R.id.rv_activity_address)
    RecyclerView    rvActivityAddress;
    @BindView(R.id.emptyView)
    EmptyViewLayout emptyView;

    private Address_Adapter   madapter;
    private List<AddressBean> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getToolbarTitle().setText("常用地址");
        initView();
    }
    @Override
    public int getContentViewId() {
        return R.layout.activity_addressctivity;
    }

    private void initView() {
        emptyView.bindView(rvActivityAddress);
        //获取所有保存的条目
        list=QueryData();
        if(list.size()<=0){
            emptyView.failure();
        }
        rvActivityAddress.setLayoutManager(new LinearLayoutManager(Addressctivity.this));
        madapter = new Address_Adapter(list, Addressctivity.this);
        rvActivityAddress.setAdapter(madapter);

    }

    private List<AddressBean> QueryData() {
        List<AddressBean> list2;
        list2 = DataSupport.findAll(AddressBean.class);
        return list2;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        menu.findItem(R.id.action_item1).setVisible(false);
        menu.findItem(R.id.action_item3).setVisible(false);
        menu.findItem(R.id.action_item4).setVisible(true);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_item4:
                startActivity(new Intent(this,AddAdrActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        list.clear();
        list.addAll(QueryData());
        madapter.notifyDataSetChanged();
    }
}


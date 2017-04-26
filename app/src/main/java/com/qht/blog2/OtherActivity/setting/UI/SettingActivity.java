package com.qht.blog2.OtherActivity.setting.UI;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qht.blog2.BaseActivity.ToolBarActivity;
import com.qht.blog2.BaseBean.OrderInfoLitePal;
import com.qht.blog2.BaseEventBus.EventBusUtil;
import com.qht.blog2.OtherActivity.setting.data.CacheEvent;
import com.qht.blog2.R;
import com.qht.blog2.Util.GlideUtil;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.litepal.crud.DataSupport;

import butterknife.BindView;
import butterknife.OnClick;

public class SettingActivity extends ToolBarActivity {

    @BindView(R.id.ll_clear_imagecache)
    LinearLayout llClearImagecache;
    @BindView(R.id.ll_clear_ordercache)
    LinearLayout llClearOrdercache;
    @BindView(R.id.tv_imagecachesize)
    TextView     tvImagecachesize;
    @BindView(R.id.tv_ordercachesize)
    TextView     tvOrdercachesize;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        inITview();
    }

    private void inITview() {
        getToolbarTitle().setText("设置");
        tvImagecachesize.setText(GlideUtil.getInstance().getCacheSize(SettingActivity.this));
        tvOrdercachesize.setText(DataSupport.count(OrderInfoLitePal.class)+"条");
    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_setting;
    }

    /**
     * From: GlideUtil
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventCache(CacheEvent response) {
        if (response.infinish) {
            String size=GlideUtil.getInstance().getCacheSize(SettingActivity.this);
            tvImagecachesize.setText(size);
        }
    }

    @OnClick({R.id.ll_clear_imagecache, R.id.ll_clear_ordercache})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_clear_imagecache:
                GlideUtil.getInstance().clearImageAllCache(SettingActivity.this);
                break;
            case R.id.ll_clear_ordercache:
                DataSupport.deleteAll(OrderInfoLitePal.class);
                tvOrdercachesize.setText(DataSupport.count(OrderInfoLitePal.class)+"条");
                break;
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

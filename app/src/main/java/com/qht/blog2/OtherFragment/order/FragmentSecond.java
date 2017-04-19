package com.qht.blog2.OtherFragment.order;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qht.blog2.BaseAdapter.BasePageAdapter;
import com.qht.blog2.BaseFragment.BaseFragment;
import com.qht.blog2.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import shanyao.tabpagerindictor.TabPageIndicator;

public class FragmentSecond extends BaseFragment {


    @BindView(R.id.indicator)
    TabPageIndicator indicator;
    @BindView(R.id.viewPager)
    ViewPager viewPager;

    @Override
    public int getContentViewId() {
        return R.layout.fragment_second;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);

        BasePageAdapter adapter = new BasePageAdapter(getFragmentManager());
        viewPager.setAdapter(adapter);// 设置adapter
        indicator.setViewPager(viewPager);// 绑定indicator
        setTabPagerIndicator();
        return rootView;
    }

    /**
     * 仿今日头条顶部导航
     * 通过一些set方法，设置控件的属性
     * http://blog.csdn.net/shan_yao/article/details/51753869
     */
    private void setTabPagerIndicator() {
        indicator.setIndicatorMode(TabPageIndicator.IndicatorMode.MODE_WEIGHT_NOEXPAND_SAME);// 设置模式，一定要先设置模式
        indicator.setDividerColor(Color.parseColor("#00bbcf"));// 设置分割线的颜色
        indicator.setDividerPadding(10);//设置
        indicator.setIndicatorColor(Color.parseColor("#43A44b"));// 设置底部导航线的颜色
        indicator.setTextColorSelected(Color.parseColor("#43A44b"));// 设置tab标题选中的颜色
        indicator.setTextColor(Color.parseColor("#797979"));// 设置tab标题未被选中的颜色
        indicator.setTextSize(16);// 设置字体大小
    }
    @OnClick(R.id.indicator)
    public void onClick() {
    }
}

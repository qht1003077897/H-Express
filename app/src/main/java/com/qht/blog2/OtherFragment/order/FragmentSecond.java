package com.qht.blog2.OtherFragment.order;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qht.blog2.BaseAdapter.BaseViewPage.BasePageAdapter;
import com.qht.blog2.BaseEventBus.EventBusUtil;
import com.qht.blog2.BaseFragment.BaseFragment;
import com.qht.blog2.OtherFragment.order.event.OrderEvent;
import com.qht.blog2.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import shanyao.tabpagerindictor.TabPageIndicator;

public class FragmentSecond extends BaseFragment {


    @BindView(R.id.indicator)
    TabPageIndicator indicator;
    @BindView(R.id.viewPager)
    ViewPager viewPager;

    private Context mActivity;
    /**
     *保存viewpage当前位置，供子fragment判断当前是否是本fragment然后onEvent接收事件处理item动画
     */
    public static int viewPagePosition;

    /**
     *保存viewpage上次位置，为了切换下一个页面时将"编辑"和item右移还原到正常状态
     */
    private int lastViewPageIndex;
    private String TAG= "FragmentSecond";

    @Override
    public int getContentViewId() {
        return R.layout.fragment_second;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        initViewPage();
        return rootView;
    }

    private void initViewPage() {
        BasePageAdapter adapter = new BasePageAdapter(getFragmentManager(),R.array.FragmentTwo_viewpage_titles,TAG);
        viewPager.setAdapter(adapter);// 设置adapter
        indicator.setViewPager(viewPager);// 绑定indicator
        setTabPagerIndicator();
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                lastViewPageIndex=viewPagePosition;
                viewPagePosition=position;
                // To:FragmentOrder_Signed.onEvent()  切换，则恢复动画
                EventBusUtil.postSync(new OrderEvent(true,false,"FragmentSecond",lastViewPageIndex,this));
                // To:MainActivity.onEvent() 切换，则还原编辑字样
                EventBusUtil.postSync(new OrderEvent(true,false,"FragmentSecond",-1,this));
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
    /**
     * 仿今日头条顶部导航
     * 通过一些set方法，设置控件的属性
     * http://blog.csdn.net/shan_yao/article/details/51753869
     */
    private void setTabPagerIndicator() {
        indicator.setIndicatorMode(TabPageIndicator.IndicatorMode.MODE_WEIGHT_NOEXPAND_SAME);// 设置模式，一定要先设置模式
        indicator.setDividerColor(getResources().getColor(R.color.colorPrimary));// 设置分割线的颜色
        indicator.setDividerPadding(10);//设置
        indicator.setIndicatorColor(getResources().getColor(android.R.color.holo_orange_light));// 设置底部导航线的颜色/和底部分割线保持一致(橘黄色)
        indicator.setTextColorSelected(getResources().getColor(R.color.colorPrimary));// 设置tab标题选中的颜色
        indicator.setTextColor(getResources().getColor(R.color.line_4));// 设置tab标题未被选中的颜色
        indicator.setTextSizedp(15);// 设置字体大小
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.mActivity=activity;
    }
}

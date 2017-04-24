package com.qht.blog2.OtherActivity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.qht.blog2.BaseActivity.ToolBarActivity;
import com.qht.blog2.BaseEventBus.EventBusUtil;
import com.qht.blog2.OtherFragment.home.UI.FragmentFrist;
import com.qht.blog2.OtherFragment.me.UI.FragmentFour;
import com.qht.blog2.OtherFragment.notice.UI.FragmentThird;
import com.qht.blog2.OtherFragment.order.FragmentSecond;
import com.qht.blog2.OtherFragment.order.event.OrderEvent;
import com.qht.blog2.R;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.OnClick;


public class MainActivity extends ToolBarActivity {


    @BindView(R.id.ll_bottom_iv_one)
    ImageView llBottomIvOne;
    @BindView(R.id.ll_bottom_tv_one)
    TextView  llBottomTvOne;

    @BindView(R.id.ll_bottom_iv_two)
    ImageView llBottomIvTwo;
    @BindView(R.id.ll_bottom_tv_two)
    TextView  llbottomTvTwo;

    @BindView(R.id.ll_bottom_iv_three)
    ImageView llBottomIvThree;
    @BindView(R.id.ll_bottom_tv_three)
    TextView  llBottomTvThree;

    @BindView(R.id.ll_bottom_iv_four)
    ImageView      llBottomIvFour;
    @BindView(R.id.ll_bottom_tv_four)
    TextView       llBottomTvFour;
    @BindView(R.id.ll_bottom_rl_one)
    RelativeLayout llBottomRlOne;
    @BindView(R.id.ll_bottom_rl_two)
    RelativeLayout llBottomRlTwo;
    @BindView(R.id.ll_bottom_rl_three)
    RelativeLayout llBottomRlThree;
    @BindView(R.id.ll_bottom_rl_four)
    RelativeLayout llBottomRlFour;
    @BindView(R.id.toolbar_subtitle)
    TextView       toolbarSubtitle;
    @BindView(R.id.ll_bottom_tab)
    LinearLayout   llBottomTab;
    @BindView(R.id.content_layout)
    LinearLayout   contentLayout;
    @BindView(R.id.line)
    View           line;
    @BindView(R.id.toolbar_sub2title)
    TextView       toolbarSub2title;


    // 底部标签切换的Fragment
    private Fragment oneFragment, twoFragment, threeFragment, fourFragment, currentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initTab();
        initBadge();
    }

    private void initBadge() {
//        QBadgeView bagdeview2 = new QBadgeView(this);
//        QBadgeView bagdeview3 = new QBadgeView(this);
//        bagdeview2.bindTarget(llBottomRlTwo).setBadgeNumber(2)
//                .setBadgeGravity(Gravity.TOP | Gravity.END)
//                .setGravityOffset(40, 0, true);
//        bagdeview3.bindTarget(llBottomRlThree).setBadgeNumber(3)
//                .setBadgeGravity(Gravity.TOP | Gravity.END)
//                .setGravityOffset(40, 0, true);
    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_main;
    }

    /**
     * 初始化底部标签
     */
    private void initTab() {
        if (oneFragment == null) {
            oneFragment = new FragmentFrist();
        }
        getToolbarTitle().setText("查询");
        if (!oneFragment.isAdded()) {
            // 提交事务
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.content_layout, oneFragment,"1").commit();

            // 记录当前Fragment
            currentFragment = oneFragment;
            // 设置图片文本的变化
            llBottomIvOne.setImageResource(R.mipmap.bottom_home_click);
            llBottomTvOne.setTextColor(getResources()
                    .getColor(R.color.bottom_click));
            llBottomIvThree.setImageResource(R.mipmap.bottom_notice_normal);
            llBottomTvThree.setTextColor(getResources().getColor(
                    R.color.bottom_normal));
            llBottomIvTwo.setImageResource(R.mipmap.bottom_bill_normal);
            llbottomTvTwo.setTextColor(getResources().getColor(
                    R.color.bottom_normal));

            llBottomIvFour.setImageResource(R.mipmap.bottom_me_normal);
            llBottomTvFour.setTextColor(getResources().getColor(
                    R.color.bottom_normal));

        }
    }

    /**
     * 接收消息函数在主线程
     * From: FragmentSecond.onPageSelected()
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(OrderEvent response) {
        if (response.from.equals("FragmentSecond") && response.position == -1) {
            getSubTitle().setText("编辑");
            getSub2Title().setVisibility(View.GONE);
        }
    }


    @OnClick({R.id.ll_bottom_rl_one, R.id.ll_bottom_rl_two, R.id.ll_bottom_rl_three, R.id.ll_bottom_rl_four,
             R.id.toolbar_subtitle,R.id.toolbar_sub2title})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_bottom_rl_one:
                getSubTitle().setVisibility(View.GONE);
                getSub2Title().setVisibility(View.GONE);
                getToolbarTitle().setText("查询");
                reViewStatus("1");
                clickTab1Layout();
                break;
            case R.id.ll_bottom_rl_two:
                getSubTitle().setVisibility(View.VISIBLE);
                getToolbarTitle().setText("订单状态");
                getSubTitle().setText("编辑");
                reViewStatus("2");
                clickTab2Layout();
                break;
            case R.id.ll_bottom_rl_three:
                getSubTitle().setVisibility(View.GONE);
                getSub2Title().setVisibility(View.GONE);
                getToolbarTitle().setText("消息");
                reViewStatus("3");
                clickTab3Layout();
                break;
            case R.id.ll_bottom_rl_four:
                getSubTitle().setVisibility(View.GONE);
                getSub2Title().setVisibility(View.GONE);
                getToolbarTitle().setText("我的");
                reViewStatus("4");
                clickTab4Layout();
                break;
            case R.id.toolbar_subtitle:
                if ("编辑".equals(getSubTitle().getText().toString())) {
                    getSubTitle().setText("取消");
                    getSub2Title().setVisibility(View.VISIBLE);
                    getSub2Title().setText("全选");
                    // To:FragmentOrder_Signed.onEvent(),参数 -1 无意义
                    EventBusUtil.postSync(new OrderEvent(false,false, "MainActivity", -1, this));
                } else if ("取消".equals(getSubTitle().getText().toString())) {
                    getSubTitle().setText("编辑");
                    getSub2Title().setVisibility(View.GONE);
                    // To:FragmentOrder_Signed.onEvent()
                    EventBusUtil.postSync(new OrderEvent(true,false,"MainActivity", -1, this));
                }
                break;
            case R.id.toolbar_sub2title:
                if ("全选".equals(getSub2Title().getText().toString())) {
                    getSub2Title().setText("取消全选");
                    // To:FragmentOrder_Signed.onEvent(),参数-1无意义
                    EventBusUtil.postSync(new OrderEvent(false,true,"MainActivity_Allselect", -1, this));
                } else if ("取消全选".equals(getSub2Title().getText().toString())) {
                    getSub2Title().setText("全选");
                    // To:FragmentOrder_Signed.onEvent()
                    EventBusUtil.postSync(new OrderEvent(false,false, "MainActivity_Allselect", -1, this));
                }

                break;
        }
    }

    /**
     * 切换底部fragment时，订单页面(fragment)返回初始化状态
     */
    private void reViewStatus(String s) {
        if(!currentFragment.getTag().equals(s)){
            // To:FragmentOrder_Signed.onEvent()
            EventBusUtil.postSync(new OrderEvent(true, false,"MainActivityInit", -1, this));
        }
    }

    /**
     * 点击第一个tab
     */
    private void clickTab1Layout() {
        if (oneFragment == null) {
            oneFragment = new FragmentFrist();
        }
        addOrShowFragment(getSupportFragmentManager().beginTransaction(), oneFragment,"1");

        llBottomIvOne.setImageResource(R.mipmap.bottom_home_click);
        llBottomTvOne.setTextColor(getResources()
                .getColor(R.color.bottom_click));
        llBottomIvThree.setImageResource(R.mipmap.bottom_notice_normal);
        llBottomTvThree.setTextColor(getResources().getColor(
                R.color.bottom_normal));
        llBottomIvTwo.setImageResource(R.mipmap.bottom_bill_normal);
        llbottomTvTwo.setTextColor(getResources().getColor(
                R.color.bottom_normal));
        llBottomIvFour.setImageResource(R.mipmap.bottom_me_normal);
        llBottomTvFour.setTextColor(getResources().getColor(
                R.color.bottom_normal));
    }

    /**
     * 点击第二个tab
     */
    private void clickTab2Layout() {
        if (twoFragment == null) {
            twoFragment = new FragmentSecond();
        }
        addOrShowFragment(getSupportFragmentManager().beginTransaction(), twoFragment,"2");

        llBottomIvOne.setImageResource(R.mipmap.bottom_home_normal);
        llBottomTvOne.setTextColor(getResources()
                .getColor(R.color.bottom_normal));
        llBottomIvThree.setImageResource(R.mipmap.bottom_notice_normal);
        llBottomTvThree.setTextColor(getResources().getColor(
                R.color.bottom_normal));
        llBottomIvTwo.setImageResource(R.mipmap.bottom_bill_click);
        llbottomTvTwo.setTextColor(getResources().getColor(
                R.color.bottom_click));
        llBottomIvFour.setImageResource(R.mipmap.bottom_me_normal);
        llBottomTvFour.setTextColor(getResources().getColor(
                R.color.bottom_normal));

    }

    /**
     * 点击第三个tab
     */
    private void clickTab3Layout() {
        if (threeFragment == null) {
            threeFragment = new FragmentThird();
        }
        addOrShowFragment(getSupportFragmentManager().beginTransaction(), threeFragment,"3");

        llBottomIvOne.setImageResource(R.mipmap.bottom_home_normal);
        llBottomTvOne.setTextColor(getResources()
                .getColor(R.color.bottom_normal));
        llBottomIvThree.setImageResource(R.mipmap.bottom_notice_click);
        llBottomTvThree.setTextColor(getResources().getColor(
                R.color.bottom_click));
        llBottomIvTwo.setImageResource(R.mipmap.bottom_bill_normal);
        llbottomTvTwo.setTextColor(getResources().getColor(
                R.color.bottom_normal));
        llBottomIvFour.setImageResource(R.mipmap.bottom_me_normal);
        llBottomTvFour.setTextColor(getResources().getColor(
                R.color.bottom_normal));
    }

    /**
     * 点击第四个tab
     */
    private void clickTab4Layout() {
        if (fourFragment == null) {
            fourFragment = new FragmentFour();
        }

        addOrShowFragment(getSupportFragmentManager().beginTransaction(), fourFragment,"4");

        llBottomIvOne.setImageResource(R.mipmap.bottom_home_normal);
        llBottomTvOne.setTextColor(getResources()
                .getColor(R.color.bottom_normal));
        llBottomIvThree.setImageResource(R.mipmap.bottom_notice_normal);
        llBottomTvThree.setTextColor(getResources().getColor(
                R.color.bottom_normal));
        llBottomIvTwo.setImageResource(R.mipmap.bottom_bill_normal);
        llbottomTvTwo.setTextColor(getResources().getColor(
                R.color.bottom_normal));
        llBottomIvFour.setImageResource(R.mipmap.bottom_me_click);
        llBottomTvFour.setTextColor(getResources().getColor(
                R.color.bottom_click));
    }

    /**
     * 添加或者显示碎片
     *
     * @param transaction
     * @param fragment
     */
    private void addOrShowFragment(FragmentTransaction transaction,
                                   Fragment fragment,String  tag) {
        if (currentFragment == fragment)
            return;
        if (!fragment.isAdded()) { // 如果当前fragment未被添加，则添加到Fragment管理器中
            transaction.hide(currentFragment)
                    .add(R.id.content_layout, fragment,tag).commit();
        } else {
            transaction.hide(currentFragment).show(fragment).commit();
        }
        currentFragment = fragment;
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


package com.qht.blog2.OtherActivity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.qht.blog2.BaseActivity.ToolBarActivity;
import com.qht.blog2.OtherFragment.home.UI.FragmentFrist;
import com.qht.blog2.OtherFragment.me.UI.FragmentFour;
import com.qht.blog2.OtherFragment.notice.UI.FragmentThird;
import com.qht.blog2.OtherFragment.order.FragmentSecond;
import com.qht.blog2.R;

import butterknife.BindView;
import butterknife.OnClick;
import q.rorbin.badgeview.QBadgeView;


public class MainActivity extends ToolBarActivity {


    @BindView(R.id.ll_bottom_iv_one)
    ImageView llBottomIvOne;
    @BindView(R.id.ll_bottom_tv_one)
    TextView llBottomTvOne;

    @BindView(R.id.ll_bottom_iv_two)
    ImageView llBottomIvTwo;
    @BindView(R.id.ll_bottom_tv_two)
    TextView llbottomTvTwo;

    @BindView(R.id.ll_bottom_iv_three)
    ImageView llBottomIvThree;
    @BindView(R.id.ll_bottom_tv_three)
    TextView llBottomTvThree;

    @BindView(R.id.ll_bottom_iv_four)
    ImageView llBottomIvFour;
    @BindView(R.id.ll_bottom_tv_four)
    TextView llBottomTvFour;
    @BindView(R.id.ll_bottom_rl_one)
    RelativeLayout llBottomRlOne;
    @BindView(R.id.ll_bottom_rl_two)
    RelativeLayout llBottomRlTwo;
    @BindView(R.id.ll_bottom_rl_three)
    RelativeLayout llBottomRlThree;
    @BindView(R.id.ll_bottom_rl_four)
    RelativeLayout llBottomRlFour;


    // 底部标签切换的Fragment
    private Fragment oneFragment, twoFragment, threeFragment, fourFragment, currentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getToolbarTitle().setText("主界面");
        initTab();
        initBadge();
    }

    private void initBadge() {
        QBadgeView bagdeview2= new QBadgeView(this);
        QBadgeView bagdeview3= new QBadgeView(this);
        bagdeview2.bindTarget(llBottomRlTwo).setBadgeNumber(2)
                .setBadgeGravity(Gravity.TOP | Gravity.END)
                .setGravityOffset(40,0,true);
        bagdeview3.bindTarget(llBottomRlThree).setBadgeNumber(3)
                .setBadgeGravity(Gravity.TOP | Gravity.END)
                .setGravityOffset(40,0,true);
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

        if (!oneFragment.isAdded()) {
            // 提交事务
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.content_layout, oneFragment).commit();

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

    @OnClick({R.id.ll_bottom_rl_one, R.id.ll_bottom_rl_two, R.id.ll_bottom_rl_three, R.id.ll_bottom_rl_four})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_bottom_rl_one:
                clickTab1Layout();
                break;
            case R.id.ll_bottom_rl_two:
                clickTab2Layout();
                break;
            case R.id.ll_bottom_rl_three:
                clickTab3Layout();
                break;
            case R.id.ll_bottom_rl_four:
                clickTab4Layout();
                break;
        }
    }

    /**
     * 点击第一个tab
     */
    private void clickTab1Layout() {
        if (oneFragment == null) {
            oneFragment = new FragmentFrist();
        }
        addOrShowFragment(getSupportFragmentManager().beginTransaction(), oneFragment);

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
        addOrShowFragment(getSupportFragmentManager().beginTransaction(), twoFragment);

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
     * 点击第三个tab
     */
    private void clickTab3Layout() {
        if (threeFragment == null) {
            threeFragment = new FragmentThird();
        }
        addOrShowFragment(getSupportFragmentManager().beginTransaction(), threeFragment);

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
     * 点击第四个tab
     */
    private void clickTab4Layout() {
        if (fourFragment == null) {
            fourFragment = new FragmentFour();
        }
        addOrShowFragment(getSupportFragmentManager().beginTransaction(), fourFragment);

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
                                   Fragment fragment) {
        if (currentFragment == fragment)
            return;
        if (!fragment.isAdded()) { // 如果当前fragment未被添加，则添加到Fragment管理器中
            transaction.hide(currentFragment)
                    .add(R.id.content_layout, fragment).commit();
        } else {
            transaction.hide(currentFragment).show(fragment).commit();
        }
        currentFragment = fragment;
    }
}


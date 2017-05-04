package com.qht.blog2.OtherActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.qht.blog2.BaseActivity.ToolBarActivity;
import com.qht.blog2.BaseAdapter.BaseListView.ViewCreator;
import com.qht.blog2.BaseBean.SlideLeftBean;
import com.qht.blog2.BaseEventBus.EventBusUtil;
import com.qht.blog2.OtherActivity.about.AboutActivity;
import com.qht.blog2.OtherActivity.setting.UI.SettingActivity;
import com.qht.blog2.OtherActivity.slide_Left.adapter.Side_LeftAdapter;
import com.qht.blog2.OtherActivity.slide_Left.data.Left_itemdata;
import com.qht.blog2.OtherActivity.slide_Left.data.QQLogin_PersonInfoEvent;
import com.qht.blog2.OtherActivity.slide_Left.qqlogin.QQLogin;
import com.qht.blog2.OtherActivity.slide_Left.weather.Weather;
import com.qht.blog2.OtherActivity.slide_Left.weather.weatherEvent;
import com.qht.blog2.OtherFragment.home.UI.FragmentFrist;
import com.qht.blog2.OtherFragment.order.FragmentSecond;
import com.qht.blog2.OtherFragment.order.event.OrderEvent;
import com.qht.blog2.OtherFragment.send.FragmentThird;
import com.qht.blog2.R;
import com.qht.blog2.Util.DialogUtil;
import com.qht.blog2.Util.LogUtil;
import com.qht.blog2.Util.ResourceUtil;
import com.qht.blog2.Util.SharePreferenceUtil;
import com.qht.blog2.Util.TextUtil;
import com.qht.blog2.View.CustomRelativeLayout;
import com.qht.blog2.View.DragLayout;
import com.tencent.connect.UserInfo;
import com.tencent.connect.common.Constants;
import com.tencent.tauth.Tencent;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.OnClick;
import jp.wasabeef.glide.transformations.CropCircleTransformation;


public class MainActivity extends ToolBarActivity implements ViewCreator<SlideLeftBean, Side_LeftAdapter.SlideLeftHolder> {


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

    @BindView(R.id.ll_bottom_rl_one)
    RelativeLayout llBottomRlOne;
    @BindView(R.id.ll_bottom_rl_two)
    RelativeLayout llBottomRlTwo;
    @BindView(R.id.ll_bottom_rl_three)
    RelativeLayout llBottomRlThree;
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
    @BindView(R.id.iv_bottom)
    ImageView      ivBottom;
    @BindView(R.id.lv)
    ListView       lv;
    @BindView(R.id.dl)
    DragLayout     dl;
    @BindView(R.id.left_name)
    TextView       leftName;
    @BindView(R.id.ll_left_setting)
    LinearLayout   llLeftSetting;
    @BindView(R.id.ll_left_night)
    LinearLayout   llLeftNight;
    @BindView(R.id.tv_left_weather)
    TextView       tvLeftWeather;
    @BindView(R.id.tv_left_city)
    TextView       tvLeftCity;
    @BindView(R.id.tv_left_night)
    TextView       tvleftnight;
    @BindView(R.id.crl)
    CustomRelativeLayout crl;
    @BindView(R.id.rl_left_bg)
    RelativeLayout rlleftbg;


    // 底部标签切换的Fragment
    private Fragment oneFragment, twoFragment, threeFragment, fourFragment, currentFragment;
    private QQLogin login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initQlogin();
        initTab();
        Weather.getweather();
    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_main;
    }


    private void initView() {

        if(enableNightMode){
            tvleftnight.setText("夜间");
            rlleftbg.setBackgroundResource(R.drawable.sidebar_bg_night);
        }else{
            tvleftnight.setText("白天");
            rlleftbg.setBackgroundResource(R.drawable.sidebar_bg);
        }
        getToolbar().setNavigationIcon(getResources().getDrawable(R.mipmap.home_menu_48));
        getToolbar().setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dl.open();
            }
        });
        Side_LeftAdapter mAdapter = new Side_LeftAdapter(Left_itemdata.getItemBeans(), this);
        lv.setAdapter(mAdapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0:
                        //意见反馈
                        DialogUtil.showAlertDialogText(MainActivity.this, "意见反馈", ResourceUtil.getString(R.string.suggestback));
                        break;
                    case 1:
                        //分享
                        login.share();
                        break;
                    case 2:
                        //关于
                        startActivity(new Intent(MainActivity.this,AboutActivity.class));
                        break;
                    case 3:
                        //退出登录
                        login.loginout();
                        Glide.with(MainActivity.this)
                                .load(R.drawable.left_image)
                                .bitmapTransform(new CropCircleTransformation(MainActivity.this))
                                .into(ivBottom);
                        leftName.setText("点击登录");
                        leftName.setClickable(true);
                        break;

                }
            }
        });
    }

    private void initQlogin() {
        login = new QQLogin(MainActivity.this);
        String openid = SharePreferenceUtil.getStringSP("openid", "");
        String access_token = SharePreferenceUtil.getStringSP("access_token", "");
        String expires_in = SharePreferenceUtil.getStringSP("expires_in", "");
        if (!TextUtil.isEmpty(openid) && !TextUtil.isEmpty(access_token) && !TextUtil.isEmpty(expires_in)) {
            login.mTencent.setOpenId(openid);
            login.mTencent.setAccessToken(access_token, expires_in);
        }
        String image = SharePreferenceUtil.getStringSP("figureurl_qq_2", "");
        String name = SharePreferenceUtil.getStringSP("nickname", "点击登录");
        Glide.with(MainActivity.this)
                .load(image)
                .placeholder(R.drawable.left_image)
                .bitmapTransform(new CropCircleTransformation(MainActivity.this))
                .into(ivBottom);
        leftName.setText("昵称:" + name);
        if (!leftName.getText().equals("点击登录")) {
            leftName.setClickable(false);
        }
    }

    /**
     * From: FragmentSecond.onPageSelected()
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventOrderInfo(OrderEvent response) {
        if (response.from.equals("FragmentSecond") && response.position == -1) {
            getSubTitle().setText("编辑");
            getSub2Title().setVisibility(View.GONE);
        }
    }

    /**
     * From: QQLogin()
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventPersonInfo(QQLogin_PersonInfoEvent response) {
        leftName.setText("昵称:" + response.name.toString());
        leftName.setClickable(false);
        Glide.with(MainActivity.this)
                .load(response.imageurl).bitmapTransform(new CropCircleTransformation(MainActivity.this))
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                .into(ivBottom);
    }
    /**
     * From: Weather()
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventweather(weatherEvent response) {
        tvLeftWeather.setText(response.tem);
        tvLeftCity.setText(response.city);
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
                    .add(R.id.content_layout, oneFragment, "1").commit();

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


        }
    }

    /**
     * 点击第一个tab
     */
    private void clickTab1Layout() {

        if (oneFragment == null) {
            oneFragment = new FragmentFrist();
        }
        addOrShowFragment(getSupportFragmentManager().beginTransaction(), oneFragment, "1");

        llBottomIvOne.setImageResource(R.mipmap.bottom_home_click);
        llBottomTvOne.setTextColor(getResources()
                .getColor(R.color.bottom_click));
        llBottomIvThree.setImageResource(R.mipmap.bottom_notice_normal);
        llBottomTvThree.setTextColor(getResources().getColor(
                R.color.bottom_normal));
        llBottomIvTwo.setImageResource(R.mipmap.bottom_bill_normal);
        llbottomTvTwo.setTextColor(getResources().getColor(
                R.color.bottom_normal));
    }

    /**
     * 点击第二个tab
     */
    private void clickTab2Layout() {
        if (twoFragment == null) {
            twoFragment = new FragmentSecond();
        }
        addOrShowFragment(getSupportFragmentManager().beginTransaction(), twoFragment, "2");

        llBottomIvOne.setImageResource(R.mipmap.bottom_home_normal);
        llBottomTvOne.setTextColor(getResources()
                .getColor(R.color.bottom_normal));
        llBottomIvThree.setImageResource(R.mipmap.bottom_notice_normal);
        llBottomTvThree.setTextColor(getResources().getColor(
                R.color.bottom_normal));
        llBottomIvTwo.setImageResource(R.mipmap.bottom_bill_click);
        llbottomTvTwo.setTextColor(getResources().getColor(
                R.color.bottom_click));

    }

    /**
     * 点击第三个tab
     */
    private void clickTab3Layout() {
        if (threeFragment == null) {
            threeFragment = new FragmentThird();
        }
        addOrShowFragment(getSupportFragmentManager().beginTransaction(), threeFragment, "3");

        llBottomIvOne.setImageResource(R.mipmap.bottom_home_normal);
        llBottomTvOne.setTextColor(getResources()
                .getColor(R.color.bottom_normal));
        llBottomIvThree.setImageResource(R.mipmap.bottom_notice_click);
        llBottomTvThree.setTextColor(getResources().getColor(
                R.color.bottom_click));
        llBottomIvTwo.setImageResource(R.mipmap.bottom_bill_normal);
        llbottomTvTwo.setTextColor(getResources().getColor(
                R.color.bottom_normal));
    }


    /**
     * 添加或者显示碎片
     *
     * @param transaction
     * @param fragment
     */
    private void addOrShowFragment(FragmentTransaction transaction,
                                   Fragment fragment, String tag) {

        if (currentFragment == fragment)
            return;
        if (!fragment.isAdded()) { // 如果当前fragment未被添加，则添加到Fragment管理器中
            transaction.hide(currentFragment)
                    .add(R.id.content_layout, fragment, tag).commit();
        } else {
            transaction.hide(currentFragment).show(fragment).commit();
        }
        currentFragment = fragment;
    }

    /**
     * 切换底部fragment时，订单页面(fragment)返回初始化状态
     */
    private void reViewStatus(String s) {
        if (!currentFragment.getTag().equals(s)) {
            // To:FragmentOrder_Signed.onEvent()
            EventBusUtil.postSync(new OrderEvent(true, false, "MainActivityInit", -1, this));
        }
    }

    @Override
    protected boolean isShowBacking() {
        return false;
    }

    @Override
    public Side_LeftAdapter.SlideLeftHolder createHolder(int position, ViewGroup parent) {
        return new Side_LeftAdapter.SlideLeftHolder(LayoutInflater.from(MainActivity.this).inflate(R.layout.left_item_layout, parent, false));
    }

    @Override
    public void bindData(int position, Side_LeftAdapter.SlideLeftHolder holder, SlideLeftBean data) {
        holder.iv.setImageResource(data.getImg());
        holder.text.setText(data.getTitle());
    }


    @OnClick({R.id.ll_bottom_rl_one, R.id.ll_bottom_rl_two, R.id.ll_bottom_rl_three,
            R.id.toolbar_subtitle, R.id.toolbar_sub2title, R.id.left_name,R.id.ll_left_setting, R.id.ll_left_night})
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
                getToolbarTitle().setText("订单");
                getSubTitle().setText("编辑");
                reViewStatus("2");
                clickTab2Layout();
                break;
            case R.id.ll_bottom_rl_three:
                getSubTitle().setVisibility(View.GONE);
                getSub2Title().setVisibility(View.GONE);
                getToolbarTitle().setText("寄快递");
                reViewStatus("3");
                clickTab3Layout();
                break;

            case R.id.toolbar_subtitle:
                if ("编辑".equals(getSubTitle().getText().toString())) {
                    getSubTitle().setText("取消");
                    getSub2Title().setVisibility(View.VISIBLE);
                    getSub2Title().setText("全选");
                    // To:FragmentOrder_Signed.onEvent(),参数 -1 无意义
                    EventBusUtil.postSync(new OrderEvent(false, false, "MainActivity", -1, this));
                } else if ("取消".equals(getSubTitle().getText().toString())) {
                    getSubTitle().setText("编辑");
                    getSub2Title().setVisibility(View.GONE);
                    // To:FragmentOrder_Signed.onEvent()
                    EventBusUtil.postSync(new OrderEvent(true, false, "MainActivity", -1, this));
                }
                break;
            case R.id.toolbar_sub2title:
                if ("全选".equals(getSub2Title().getText().toString())) {
                    getSub2Title().setText("取消全选");
                    // To:FragmentOrder_Signed.onEvent(),参数-1无意义
                    EventBusUtil.postSync(new OrderEvent(false, true, "MainActivity_Allselect", -1, this));
                } else if ("取消全选".equals(getSub2Title().getText().toString())) {
                    getSub2Title().setText("全选");
                    // To:FragmentOrder_Signed.onEvent()
                    EventBusUtil.postSync(new OrderEvent(false, false, "MainActivity_Allselect", -1, this));
                }
                break;
            case R.id.left_name:
                //开始qq授权登录
                login.mTencent.login(this, login.SCOPE, login.loginListener);
                break;
            case R.id.ll_left_setting:
                //设置
                startActivity(new Intent(MainActivity.this, SettingActivity.class));
                break;
            case R.id.ll_left_night:
                //夜间模式
                if(tvleftnight.getText().toString().equals("夜间")){
                    SharePreferenceUtil.setBooleanSP("enableNightMode",false);
                    tvleftnight.setText("白天");
                    setEnableNightMode(false,crl);
                }else{
                    SharePreferenceUtil.setBooleanSP("enableNightMode",true);
                    tvleftnight.setText("夜间");
                    setEnableNightMode(true,crl);

                }
                break;

        }
    }
        /**
        * 如果在 MainActivity 上返回首页，则不需要Intent ，而是切换到FirstFragment
        * */
         @Override
         protected void backhome() {
             getSubTitle().setVisibility(View.GONE);
             getSub2Title().setVisibility(View.GONE);
             getToolbarTitle().setText("查询");
             reViewStatus("1");
             clickTab1Layout();
         }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        /** QQ登录 */
        if (requestCode == Constants.REQUEST_LOGIN) {
            if (resultCode == -1) {
                Tencent.onActivityResultData(requestCode, resultCode, data, login.loginListener);
                LogUtil.e(data.getDataString());
                Tencent.handleResultData(data, login.loginListener);
                UserInfo info = new UserInfo(this, login.mTencent.getQQToken());
                info.getUserInfo(login.userInfoListener);
            }
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        // 不保存onSaveInstanceState，即不执行super方法，使Activity失去fragment状态，使fragment的hide/show正常显示
        // 不然的话，调用hide/show 方法不会正常显示，不论底部怎么切换，一直停留 FirstFragment 页面。
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


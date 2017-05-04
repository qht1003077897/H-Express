package com.qht.blog2.BaseActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.qht.blog2.OtherActivity.MainActivity;
import com.qht.blog2.R;
import com.qht.blog2.Util.ToastUtil;

/**
 * ToolBarActivity.
 */
public abstract class ToolBarActivity extends statusActivity {
    private TextView mToolbarTitle;
    private TextView mToolbarSubTitle;
    private Toolbar mToolbar;
    private TextView mToolbarSub2Title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);

         /*
          toolbar.setLogo(R.mipmap.ic_launcher);
          toolbar.setTitle("Title");
          toolbar.setSubtitle("Sub Title");
          */
        mToolbarTitle = (TextView) findViewById(R.id.toolbar_title);
        mToolbarSubTitle = (TextView) findViewById(R.id.toolbar_subtitle);
        mToolbarSub2Title= (TextView) findViewById(R.id.toolbar_sub2title);
        getSubTitle().setVisibility(View.GONE);//隐藏副标题
        getSub2Title().setVisibility(View.GONE);
        if (mToolbar != null) {
            //将Toolbar显示到界面
            setSupportActionBar(mToolbar);
        }
        if (mToolbarTitle != null) {
            //getTitle()的值是activity的android:lable属性值
            mToolbarTitle.setText(getTitle());
            //设置默认的标题不显示
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }

    }
        @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_tool_bar, menu);
        return true;
    }
    /**
     如果有需要，可以将此Toolbar操作封装到ToolbarActivity
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // TODO Auto-generated method stub
        switch (item.getItemId()) {
            case R.id.action_item1:
                backhome();
                break;
            case R.id.action_item3:
                ToastUtil.showToastShort(getResources().getString(R.string.toorbar_scale));
                scan();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    protected void backhome(){
    //在MainActivity之上的activity会自动被清除
    //符合栈的后进先出原则   singtask启动模式
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    protected void scan(){

    }
    @Override
    protected void onStart() {
        super.onStart();
        /**
         * 判断是否有Toolbar,并默认显示返回按钮
         */
        if(null != getToolbar() && isShowBacking()){
            showBack();
        }
    }

    /**
     * 获取头部标题的TextView
     * @return
     */
    public TextView getToolbarTitle(){
        return mToolbarTitle;
    }
    /**
     * 获取头部标题的TextView
     * @return
     */
    public TextView getSubTitle(){
        return mToolbarSubTitle;
    }
    /**
     * 获取副头部标题的TextView
     * @return
     */
    public TextView getSub2Title(){
        return mToolbarSub2Title;
    }


    /**
     * 设置头部标题
     * @param title
     */
    public void setToolBarTitle(CharSequence title) {
        if(mToolbarTitle != null){
            mToolbarTitle.setText(title);
        }else{
            getToolbar().setTitle(title);
            setSupportActionBar(getToolbar());
        }
    }

    /**
     * this Activity of tool bar.
     * 获取头部.
     * @return support.v7.widget.Toolbar.
     */
    public Toolbar getToolbar() {
        return (Toolbar) findViewById(R.id.toolbar);
    }

    /**
     * 版本号小于21的后退按钮图片
     */
    private void showBack(){
        //setNavigationIcon必须在setSupportActionBar(toolbar);方法后面加入
        getToolbar().setNavigationIcon(R.mipmap.back);
        getToolbar().setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    /**
     * 是否显示后退按钮,默认显示,可在子类重写该方法.
     * @return
     */
    protected boolean isShowBacking(){
        return true;
    }

}
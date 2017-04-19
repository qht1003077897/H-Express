package com.qht.blog2.BaseActivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.qht.blog2.Util.LogUtil;

import butterknife.ButterKnife;

public abstract class superActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentViewId());
        ButterKnife.bind(this);
        LogUtil.e(this.getClass().getName()+"--->onCreate");
    }
    public abstract int getContentViewId();


    @Override
    protected void onDestroy() {
        super.onDestroy();
        LogUtil.e(this.getClass().getName()+"--->onDestroy");
    }
}

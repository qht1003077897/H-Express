package com.qht.blog2.BaseActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.qht.blog2.OtherActivity.day2night_switchmode.SwitchModeActivity;
import com.qht.blog2.R;
import com.qht.blog2.Util.LogUtil;
import com.qht.blog2.Util.SharePreferenceUtil;

import butterknife.ButterKnife;

public abstract class superActivity extends AppCompatActivity {

    protected  boolean enableNightMode ;
    private int width;
    private int height;
    private int statusBarHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        enableNightMode= SharePreferenceUtil.getBooleanSP("enableNightMode");
        if(!enableNightMode) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(getContentViewId());
        ButterKnife.bind(this);
        LogUtil.e(this.getClass().getName()+"--->onCreate");
    }
    public abstract int getContentViewId();

    /**
     *If enabled night mode
     * @return  true or false
     */
    public boolean isEnableNightMode() {
        return enableNightMode;
    }

    /**
     * enable night mode or not
     * @param enableNightMode   true or false
     */
    public void setEnableNightMode(boolean enableNightMode,ViewGroup rootLayout) {
        this.enableNightMode = enableNightMode;
        if(enableNightMode) {
            getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
        finish();
        Intent mode = new Intent(this, SwitchModeActivity.class);
        startActivity(mode);
        overridePendingTransition( R.anim.switchmode_activity_in,R.anim.switchmode_activity_out);// 淡出淡入动画效果
//      recreate();
    }

    /**
     * 获取view截图对应的bitmap
     * @param v
     * @return
     */
    public Bitmap loadBitmapFromView(View v) {
        Bitmap b = Bitmap.createBitmap(width, height-statusBarHeight, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(b);
        v.layout(0, 0, v.getLayoutParams().width, v.getLayoutParams().height);
        v.draw(c);
        return b;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LogUtil.e(this.getClass().getName()+"--->onDestroy");
    }
}

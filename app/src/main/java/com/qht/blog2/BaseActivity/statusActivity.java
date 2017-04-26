package com.qht.blog2.BaseActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.qht.blog2.R;

import java.lang.reflect.Field;

public abstract class statusActivity extends superActivity {


    private View statusView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //透明导航栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
        setStatusBar();
//        setstatusColor(this, getResources().getColor(R.color.colorPrimary));//和toolbar一个颜色
    }
    /**
     * 设置沉浸式状态栏
     * 这种方式不同于注释的哪种方式
     * 1、这种是拉大toolbar的高度达到沉浸式，但是这样toolbar的内容就会上移，所以需要给toolbar设置  android:paddingTop="22dp"
     * 2、注释的这种方式是给toolbar上面添加一个和toolbar颜色一样状态栏，达到占用的目的
     * 第一种方式当使用侧滑界面的时候因为侧滑界面上面没东西，所以拉大toolbar的话和下面的颜色一样，刚好达到我们的目的
     * 第二种方式当使用侧滑界面的时候会在侧滑界面顶部生成一个和toolgbar颜色一致的状态栏，导致和侧滑界面颜色不一致
     * 所以我恩采用第一种
     */

    protected void setStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            final ViewGroup linear_bar = (ViewGroup) findViewById(R.id.toolbar);
            final int statusHeight = getStatusBarHeight();
            linear_bar.post(new Runnable() {
                @Override
                public void run() {
                    int titleHeight = linear_bar.getHeight();
                    android.widget.RelativeLayout.LayoutParams params = (android.widget.RelativeLayout.LayoutParams) linear_bar.getLayoutParams();
                    params.height = statusHeight + titleHeight;
                    linear_bar.setLayoutParams(params);
                }
            });
        }
    }

    /**
     * 获取状态栏的高度
     * @return
     */
    protected int getStatusBarHeight(){
        try
        {
            Class<?> c=Class.forName("com.android.internal.R$dimen");
            Object obj=c.newInstance();
            Field field=c.getField("status_bar_height");
            int x=Integer.parseInt(field.get(obj).toString());
            return  getResources().getDimensionPixelSize(x);
        }catch(Exception e){
            e.printStackTrace();
        }
        return 0;
    }

//
//    /**
//     * 设置状态栏颜色
//     *
//     * @param activity 需要设置的activity
//     * @param color    状态栏颜色值
//     */
//    public void setstatusColor(Activity activity, int color) {
//
//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            // 设置状态栏透明
//            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//            // 生成一个状态栏大小的矩形
//
//            // 添加 statusView 到布局中
//            ViewGroup decorView = (ViewGroup) activity.getWindow().getDecorView();
//
//            if (statusView != null) {
//                decorView.removeView(statusView);
//            }
//            statusView = createStatusView(activity, color);
//            decorView.addView(statusView);
//            // 设置根布局的参数
//            ViewGroup rootView = (ViewGroup) ((ViewGroup) activity.findViewById(android.R.id.content)).getChildAt(0);
//            rootView.setFitsSystemWindows(true);
//            rootView.setClipToPadding(true);
//        }
//    }
//
//    /**
//     * 生成一个和状态栏大小相同的矩形条
//     *
//     * @param activity 需要设置的activity
//     * @param color    状态栏颜色值
//     * @return 状态栏矩形条
//     */
//    private View createStatusView(Activity activity, int color) {
//        // 获得状态栏高度
//        int resourceId = activity.getResources().getIdentifier("status_bar_height", "dimen", "android");
//        int statusBarHeight = activity.getResources().getDimensionPixelSize(resourceId);
//
//        // 绘制一个和状态栏一样高的矩形
//        View statusView = new View(activity);
//        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
//                statusBarHeight);
//        statusView.setLayoutParams(params);
//        statusView.setBackgroundColor(color);
//        return statusView;
//    }
//
//
//    /**
//     * 提供remove方法，在不需要状态栏时可以移除
//     */
//    public void removeView(Activity activity) {
//        ViewGroup decorView = (ViewGroup) activity.getWindow().getDecorView();
//        if (statusView != null) {
//            decorView.removeView(statusView);
//        }
//    }
}

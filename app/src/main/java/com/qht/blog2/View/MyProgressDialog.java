package com.qht.blog2.View;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import com.qht.blog2.R;

/**
 * 自定义过场动画，主要用户数据加载时，显示等待progress
 * Created by 程果 on 2016/3/16.
 */
public class MyProgressDialog extends AlertDialog {

    private ImageView progressImg;
    //旋转动画
    private Animation animation;

    public MyProgressDialog(Context context) {
        super(context, R.style.MyDialog);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.progressdialog_layout);

        //点击imageview外侧区域，动画不会消失
        setCanceledOnTouchOutside(false);

        progressImg = (ImageView) findViewById(R.id.refreshing_img);
        //加载动画资源
        animation = AnimationUtils.loadAnimation(getContext(), R.anim.progress_rotate);
        //动画完成后，是否保留动画最后的状态，设为true
        animation.setFillAfter(true);
    }

    /**
     * 在AlertDialog的 onStart() 生命周期里面执行开始动画
     */
    @Override
    protected void onStart() {
        super.onStart();
        if (animation != null) {
            progressImg.startAnimation(animation);
        }

    }
    /**
     * 在AlertDialog的onStop()生命周期里面执行停止动画
     */
    @Override
    protected void onStop() {
        super.onStop();

        progressImg.clearAnimation();
    }
}

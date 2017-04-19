package com.qht.blog2.View;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.qht.blog2.R;

import java.lang.reflect.Method;

@SuppressLint("NewApi")
public class EmptyViewLayout extends RelativeLayout {
    private ImageView failure;//加载失败的图片
    private View bindView;// 绑定的View，即要显示的View
    private Button loading_btn;//重试按钮
    public EmptyViewLayout(Context context) {
        super(context);
        initView(context);
    }

    public EmptyViewLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    private void initView(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.empty_view,
                null);
        failure = (ImageView) view.findViewById(R.id.loading_failure);
        loading_btn= (Button) view.findViewById(R.id.loading_btn);
        /*
         * 这一步添加param必须得要，很多人会遇到inflater添加进来的布局
         * 显示的时候只能显示包容的布局，根本不能match整个布局，然后又说，
         * 我布局文件是match的啊，然后一直找不到问题的解决方法，
         * 我感觉问题应该是出在自定义布局add这个xml的时候需要重新设置宽高吧
         * 所以，我们在自定义控件需要添加xml布局的时候，记得add这个布局的时候
         * 也设置一下param，问题就会解决了
         */
        LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(
                new LayoutParams(LayoutParams.MATCH_PARENT,
                        LayoutParams.MATCH_PARENT));
        addView(view, param);
    }

    /**
     * 总共有三种状态显示：
     * 一种是正在加载状态，
     * 一种是加载失败，
     * 一种是加载成功
     */
    public void Loading() {
        setVisibility(View.VISIBLE);
        if (bindView != null) {
            failure.setVisibility(View.GONE);
            loading_btn.setVisibility(View.GONE);
        }
    }

    public void succees() {
        setVisibility(View.GONE);
        if (bindView != null) {
            bindView.setVisibility(View.VISIBLE);
        }
    }

    public void failure() {
        setVisibility(View.VISIBLE);
        if (bindView != null) {
            failure.setVisibility(View.VISIBLE);
            bindView.setVisibility(View.GONE);
            loading_btn.setVisibility(View.VISIBLE);
        }
    }

    // 绑定加载 的view
    public void bindView(View view) {
        this.bindView = view;
    }

    /*
     * 利用反射机制，响应对方需要响应的方法
     */
    public void buttonClick(final Object base, final String method) {
        loading_btn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Method m = base.getClass().getDeclaredMethod(method);
                    m.setAccessible(true);
                    m.invoke(base, new Object());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }
}
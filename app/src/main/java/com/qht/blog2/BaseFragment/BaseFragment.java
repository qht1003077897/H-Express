package com.qht.blog2.BaseFragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qht.blog2.Util.LogUtil;

import butterknife.ButterKnife;

/**
 * Created by QHT on 2017-04-08.
 */
public abstract class BaseFragment extends Fragment
{
    /**
     * 依附的activity
     */
    protected FragmentActivity mActivity;
    /**
     * 根view
     */
    protected View mRootView;

    @Override
    @Nullable
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState)
    {
        if (getContentViewId() != 0) {
            mRootView= inflater.inflate(getContentViewId(), null);
        } else {
            mRootView= super.onCreateView(inflater, container, savedInstanceState);
        }
        ButterKnife.bind(this, mRootView);
        LogUtil.e(this.getClass().getName()+"--->onCreateView");
        return mRootView;
    }
    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);
        mActivity = getActivity();
    }

    /**
     * 设置根布局资源id
     * @return
     */
    public abstract int getContentViewId();

    /**
     * 因为暂时我们没有用到viewpage不需要懒加载
     *
     * 在这里实现Fragment数据的懒加载
     * 避免快速切换时或者fragment嵌套而执行所有fragment的数据加载
     * 只有onCreateView执行完成后并且当前页面对用户可见才会执行onLazyLoad方法
     *
     * 注意：：setUserVisibleHint方法只有在和ViewPage搭配即FragmentPagerAdapter 时才会生效
     * 而我们这种情况使用onHiddenChanged() 比较合适
     */

    public void gotoActivity(Context context, Class<?> cls) {
        Intent intent=new Intent(context,cls);
        startActivity(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LogUtil.e(this.getClass().getName()+"--->onDestroy");
    }

}

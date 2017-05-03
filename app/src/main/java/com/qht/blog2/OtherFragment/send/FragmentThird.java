package com.qht.blog2.OtherFragment.send;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qht.blog2.BaseAdapter.BaseViewPage.BasePageAdapter;
import com.qht.blog2.BaseFragment.BaseFragment;
import com.qht.blog2.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentThird extends BaseFragment {


    @BindView(R.id.tabLayout)
    TabLayout         tabLayout;
    @BindView(R.id.viewpager)
    ViewPager         viewpager;

    private String TAG = "FragmentThird";

    /**
     * 设置根布局资源id
     *
     * @return
     */
    @Override
    public int getContentViewId() {
        return R.layout.fragment_third;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        initview();
        return rootView;
    }

    private void initview() {
        tabLayout.addTab(tabLayout.newTab());
        tabLayout.addTab(tabLayout.newTab());
        tabLayout.addTab(tabLayout.newTab());
        tabLayout.setupWithViewPager(viewpager);

        BasePageAdapter viewPagerAdapter = new BasePageAdapter(getFragmentManager(), R.array.FragmentThree_viewpage_titles, TAG);
        viewpager.setAdapter(viewPagerAdapter);//设置适配器
    }

}

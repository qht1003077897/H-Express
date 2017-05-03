package com.qht.blog2.BaseAdapter.BaseViewPage;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.qht.blog2.BaseFragment.FragmentFactory;
import com.qht.blog2.Util.ResourceUtil;

/**
 * Created by QHT on 2017-04-11.
 */
   public class BasePageAdapter extends FragmentPagerAdapter {
        private String[] titles;
        private String title;

        public BasePageAdapter(FragmentManager fm,int array,String title) {
            super(fm);
            this.titles = ResourceUtil.getStringArray(array);
            this.title  =title;
        }

        @Override
        public Fragment getItem(int position) {
            if(title.equals("FragmentSecond")){
                return FragmentFactory.ShowOrderFragment(position);
            }
            return FragmentFactory.ShowSendFragment(position);
        }

        @Override
        public int getCount() {
            return titles.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }

}


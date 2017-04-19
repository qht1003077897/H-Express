package com.qht.blog2.BaseAdapter.BaseViewPage;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.qht.blog2.BaseFragment.FragmentFactory;
import com.qht.blog2.R;
import com.qht.blog2.Util.ResourceUtil;

/**
 * Created by QHT on 2017-04-11.
 */
   public class BasePageAdapter extends FragmentPagerAdapter {
        String[] titles;

        public BasePageAdapter(FragmentManager fm) {
            super(fm);
            this.titles = ResourceUtil.getStringArray(R.array.FragmentTwo_viewpage_titles);
        }

        @Override
        public Fragment getItem(int position) {
            return FragmentFactory.ShowFragment(position);
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


package org.android.an.oa4android;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class TestA extends Fragment {

    public TestA() {
    }

    private HomeFragment getH() {
        HomeFragment homeFragment = (HomeFragment) getChildFragmentManager().findFragmentByTag("");
        if (homeFragment == null)
            homeFragment = HomeFragment.newInstance("", "");
        return homeFragment;
    }

    private class MyPagerAdapter extends FragmentPagerAdapter {

        private List<Fragment> mFragmentList;

        public MyPagerAdapter(FragmentManager fm, ArrayList<Fragment> fragments) {
            super(fm);
            mFragmentList = fragments;
        }

        @Override
        public Fragment getItem(int position) {

            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {

            return mFragmentList.size();
        }
    }

}

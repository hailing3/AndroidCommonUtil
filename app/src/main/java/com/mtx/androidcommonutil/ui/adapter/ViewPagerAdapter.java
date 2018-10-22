package com.mtx.androidcommonutil.ui.adapter;

import com.mtx.androidcommonutil.util.LogUtil;

import android.app.Fragment;
import android.app.FragmentManager;

import java.util.ArrayList;

/**
 * ViewPager的adapter
 */
public class ViewPagerAdapter extends MyFragmentPagerAdapter {
    private static final String TAG = "ViewPagerAdapter";

    private ArrayList<Fragment> mFragmentList;

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public void setFragmentList(ArrayList<Fragment> fragmentList) {
        LogUtil.i(TAG, "setFragmentList fragmentList = " + fragmentList);
        mFragmentList = fragmentList;
        notifyDataSetChanged();
    }

    @Override
    public Fragment getItem(int position) {
        if (mFragmentList == null || position < 0 || position >= mFragmentList.size()) return null;
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList == null ? 0 : mFragmentList.size();
    }

}

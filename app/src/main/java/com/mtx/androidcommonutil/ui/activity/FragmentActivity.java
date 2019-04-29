package com.mtx.androidcommonutil.ui.activity;

import com.mtx.androidcommonutil.R;
import com.mtx.androidcommonutil.ui.adapter.ViewPagerAdapter;
import com.mtx.androidcommonutil.ui.fragment.FragmentA;
import com.mtx.androidcommonutil.ui.fragment.FragmentB;

import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;

import java.util.ArrayList;

/**
 * 测试fragment的界面
 */
public class FragmentActivity extends BaseActivity {
    public static final String KEY_DATA = "key_data";
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        Bundle bundle = new Bundle();
        bundle.putString(KEY_DATA, "1111111111");
        replaceFragment(new FragmentA(), bundle);

//        mHandler.sendEmptyMessageDelayed(0, 2000);

        initView();
    }

    private void replaceFragment(Fragment fragment, Bundle bundle) {
        if (fragment == null) return;
        if (bundle != null) fragment.setArguments(bundle);
//        FragmentManager fm = getFragmentManager();
//        FragmentTransaction ft = fm.beginTransaction();
//        ft.replace(R.id.fragment_container, fragment);
//        ft.commit();
    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            Bundle bundle = new Bundle();
            bundle.putString(KEY_DATA, "2222222222");
            replaceFragment(new FragmentA(), bundle);
        }
    };

    private void initView() {
        mViewPager = (ViewPager) findViewById(R.id.tabs_viewpager);
        ArrayList<Fragment> mFragmentList = new ArrayList<Fragment>();
        mFragmentList.add(new FragmentA());
        mFragmentList.add(new FragmentB());
        mFragmentList.add(new FragmentA());
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getFragmentManager());
        viewPagerAdapter.setFragmentList(mFragmentList);
        mViewPager.setAdapter(viewPagerAdapter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mHandler.removeCallbacksAndMessages(null);
    }

}

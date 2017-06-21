package com.mtx.androidcommonutil.ui;

import com.mtx.androidcommonutil.R;
import com.mtx.androidcommonutil.ui.fragment.FragmentA;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

/**
 * 测试fragment的界面
 */
public class FragmentActivity extends BaseActivity {
    public static final String KEY_DATA = "key_data";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        Bundle bundle = new Bundle();
        bundle.putString(KEY_DATA, "1111111111");
        replaceFragment(new FragmentA(), bundle);

        mHandler.sendEmptyMessageDelayed(0, 2000);
    }

    private void replaceFragment(Fragment fragment, Bundle bundle) {
        if (fragment == null) return;
        if (bundle != null) fragment.setArguments(bundle);
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fragment_container, fragment);
        ft.commit();
    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            Bundle bundle = new Bundle();
            bundle.putString(KEY_DATA, "2222222222");
            replaceFragment(new FragmentA(), bundle);
        }
    };

}
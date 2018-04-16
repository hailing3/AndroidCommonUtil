package com.mtx.androidcommonutil.ui;

import com.mtx.androidcommonutil.R;
import com.mtx.androidcommonutil.util.statusbar.StatusBarUtil;

import android.os.Bundle;
import android.view.View;

public class TestActivity extends BaseActivity {
    private static final String TAG = "TestActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        initView();

    }

    private void initView() {
    }

    public void onTestButtonClick(View view) {
        StatusBarUtil.setStatusBarShowOrHidden(false, getWindow());
    }

}



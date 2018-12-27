package com.mtx.androidcommonutil.ui;

import com.mtx.androidcommonutil.R;

import android.os.Bundle;

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
}
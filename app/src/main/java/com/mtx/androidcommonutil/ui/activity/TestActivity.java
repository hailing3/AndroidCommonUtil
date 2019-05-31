package com.mtx.androidcommonutil.ui.activity;

import android.os.Bundle;
import android.os.Parcelable;

import com.mtx.androidcommonutil.R;
import com.mtx.androidcommonutil.model.PersonModel;
import com.mtx.androidcommonutil.util.LogUtil;

public class TestActivity extends BaseActivity {
    private static final String TAG = "TestActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        initData();
        initView();
    }

    private void initData() {
        Parcelable p = getIntent().getParcelableExtra("key");
        if (p != null && p instanceof PersonModel) {
            PersonModel model = (PersonModel) p;
            LogUtil.i(TAG, "model= " + model.toString());
        }
    }

    private void initView() {


    }
}
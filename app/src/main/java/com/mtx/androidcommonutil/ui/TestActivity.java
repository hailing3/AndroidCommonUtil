package com.mtx.androidcommonutil.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.mtx.androidcommonutil.R;
import com.mtx.androidcommonutil.util.LogUtil;
import com.mtx.androidcommonutil.util.StatusBarUtil;

public class TestActivity extends BaseActivity {

    private static final String TAG = "TestActivity";
    private EditText mEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        initView();
    }

    private void initView() {
        mEdit = (EditText) findViewById(R.id.edit);
    }

    public void btnClickLeft(View view) {
        StatusBarUtil.setStatusBarShowOrHidden(true, getWindow());
//        LogUtil.i(TAG, "状态栏高度：" + StatusBarUtil.getStatusBarHeight(TestActivity.this));
        LogUtil.i(TAG, "状态栏是否显示：" + StatusBarUtil.isStatusBarShow(getWindow()));

    }

    public void btnClickRight(View view) {
        StatusBarUtil.setStatusBarShowOrHidden(false, getWindow());
        LogUtil.i(TAG, "状态栏是否显示：" + StatusBarUtil.isStatusBarShow(getWindow()));
    }


}

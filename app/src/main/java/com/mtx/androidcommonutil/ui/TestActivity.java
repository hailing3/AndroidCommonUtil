package com.mtx.androidcommonutil.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.mtx.androidcommonutil.R;
import com.mtx.androidcommonutil.util.LogUtil;
import com.mtx.androidcommonutil.util.Pref;
import com.mtx.androidcommonutil.util.PreferenceUtil;

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

    public void click(View view) {
        LogUtil.d(TAG, "test");
    }

    public void saveClick(View view) {
        PreferenceUtil.setString(TestActivity.this, Pref.KEY_TEST, mEdit.getText().toString().trim());
    }

    public void readClick(View view) {
        String string = PreferenceUtil.getString(TestActivity.this, Pref.KEY_TEST, "null");
        LogUtil.d(TAG, "readClick..." + string);
    }

}

package com.mtx.androidcommonutil.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.mtx.androidcommonutil.R;
import com.mtx.androidcommonutil.util.LogUtil;
import com.mtx.androidcommonutil.util.MD5Util;
import com.mtx.androidcommonutil.util.base64.Base64;
import com.mtx.androidcommonutil.util.pref.Pref;
import com.mtx.androidcommonutil.util.pref.PreferenceUtil;

import java.io.IOException;

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

    public void saveClick(View view) throws IOException {
        String source = mEdit.getText().toString().trim();
        String encode = Base64.encode(source);
        LogUtil.i(TAG, "Base64 encode=" + encode);
        String decode = Base64.decode(encode);
        LogUtil.i(TAG, "Base64 decode=" + decode);

        String encodeMd5 = MD5Util.RSAEncrypt(source);
        LogUtil.i(TAG, "Md5 encode=" + encodeMd5);
        LogUtil.i(TAG, "Md5 decode=" + MD5Util.RSADecrypt(encodeMd5));

    }

    public void readClick(View view) {
        String string = PreferenceUtil.getString(TestActivity.this, Pref.KEY_TEST, "null");
        LogUtil.d(TAG, "readClick..." + string);
    }

}

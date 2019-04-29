package com.mtx.androidcommonutil.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.mtx.androidcommonutil.R;

/**
 * 微博分享页面
 */
public class WbActivity extends BaseActivity {
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wb);
        initView();
    }

    private void initView() {
        editText = (EditText) findViewById(R.id.edit_wb);
    }

    public void shareToWb(View view) {
        String sendMsg = editText.getText().toString().trim();

    }

}

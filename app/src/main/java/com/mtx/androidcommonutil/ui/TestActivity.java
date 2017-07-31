package com.mtx.androidcommonutil.ui;

import com.mtx.androidcommonutil.R;

import android.app.Dialog;
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

    public void showDialog(View view) {
        Dialog dialog = new Dialog(TestActivity.this);

        dialog.show();

    }

}



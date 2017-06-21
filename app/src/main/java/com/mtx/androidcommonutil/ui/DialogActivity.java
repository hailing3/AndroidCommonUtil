package com.mtx.androidcommonutil.ui;

import com.mtx.androidcommonutil.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.WindowManager;

/**
 * Dialog样式的Activity
 */
public class DialogActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.Transparent);
        setContentView(R.layout.activity_dialog);
        initView();
    }

    private void initView() {


    }
}

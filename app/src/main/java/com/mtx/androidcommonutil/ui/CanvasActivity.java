package com.mtx.androidcommonutil.ui;

import com.mtx.androidcommonutil.R;

import android.os.Bundle;

/**
 * Canvas绘制Activity
 */
public class CanvasActivity extends BaseActivity {
    private static final String TAG = "CanvasActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canvas);
        initView();
    }

    private void initView() {
    }


}



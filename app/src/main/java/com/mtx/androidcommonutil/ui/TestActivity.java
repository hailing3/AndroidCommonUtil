package com.mtx.androidcommonutil.ui;

import com.mtx.androidcommonutil.R;
import com.mtx.androidcommonutil.ui.widget.DragFrameLayout;

import android.content.Intent;
import android.os.Bundle;

public class TestActivity extends BaseActivity {
    private static final String TAG = "TestActivity";
    private DragFrameLayout mDragView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        initView();
    }

    private void initView() {
        mDragView = (DragFrameLayout) findViewById(R.id.search_view);
        mDragView.setCallback(new DragFrameLayout.Callback() {
            @Override
            public void onClick() {
                Intent intent = new Intent();
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intent.setClassName("com.miui.homefeed", "com.miui.homefeed.ui.activity.MainActivity");
                startActivity(intent);
            }
        });
    }

}



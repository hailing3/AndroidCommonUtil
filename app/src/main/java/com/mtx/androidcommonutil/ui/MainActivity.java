package com.mtx.androidcommonutil.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.mtx.androidcommonutil.R;

/**
 * 主页面
 * Created by lishaoming on 16/10/30.
 */
public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        // TEST
        startActivity(new Intent(MainActivity.this, CommonScrollActivity.class));
    }

    private void initView() {

    }

    public void goNaH5Activity(View view) {
        startActivity(new Intent(MainActivity.this, NaH5Activity.class));
    }

    public void goCommonScrollActivity(View view) {
        startActivity(new Intent(MainActivity.this, CommonScrollActivity.class));
    }

    public void goTestActivity(View view) {
        startActivity(new Intent(MainActivity.this, TestActivity.class));
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}

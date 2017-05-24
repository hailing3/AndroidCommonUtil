package com.mtx.androidcommonutil.ui;

import com.mtx.androidcommonutil.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

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

//        startActivity(new Intent(MainActivity.this, TestActivity.class));
    }

    private void initView() {

    }

    public void goNaH5Activity(View view) {
        startActivity(new Intent(MainActivity.this, NaH5Activity.class));
    }

    public void goCodeActivity(View view) {
        startActivity(new Intent(MainActivity.this, CodeActivity.class));
    }

    public void goTestActivity(View view) {
        startActivity(new Intent(MainActivity.this, TestActivity.class));
    }

}

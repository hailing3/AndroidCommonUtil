package com.mtx.androidcommonutil.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.mtx.androidcommonutil.R;
import com.mtx.androidcommonutil.model.PersonModel;
import com.mtx.androidcommonutil.util.LogUtil;

/**
 * 主页面
 * Created by lishaoming on 16/10/30.
 */
public class MainActivity extends BaseActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        startActivity(new Intent(this, ViewActivity.class));
    }

    private void initView() {

    }

    public void goTestActivity(View view) {
        startActivity(new Intent(MainActivity.this, TestActivity.class));
    }

    public void goNaH5Activity(View view) {
        startActivity(new Intent(MainActivity.this, NaH5Activity.class));
    }

    public void goCodeActivity(View view) {
        startActivity(new Intent(MainActivity.this, CodeActivity.class));
    }

    public void goHTMLActivity(View view) {
        startActivity(new Intent(MainActivity.this, TextViewActivity.class));
    }

    public void goFragmentActivity(View view) {
        startActivity(new Intent(MainActivity.this, FragmentActivity.class));
    }

    public void goDialogActivity(View view) {
        startActivity(new Intent(MainActivity.this, DialogActivity.class));
    }

    public void goWebViewActivity(View view) {
        startActivity(new Intent(MainActivity.this, WebViewActivity.class));
    }

    public void goViewActivity(View view) {
        startActivity(new Intent(MainActivity.this, ViewActivity.class));
    }

    public void goRecyclerViewActivity(View view) {
        startActivity(new Intent(MainActivity.this, RecyclerViewActivity.class));
    }

    public void goKeyboardActivity(View view) {
        startActivity(new Intent(MainActivity.this, KeyboardActivity.class));
    }

    public void goWXActivity(View view) {
        startActivity(new Intent(MainActivity.this, WxActivity.class));
    }

    public void goWBActivity(View view) {
        startActivity(new Intent(MainActivity.this, WbActivity.class));
    }

    public void goSVGActivity(View view) {
        startActivity(new Intent(MainActivity.this, SVGActivity.class));
    }

}

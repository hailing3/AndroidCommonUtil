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
        startActivity(new Intent(MainActivity.this, RecyclerViewActivity.class));
    }

    private void initView() {

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

    public void goTestActivity(View view) {
        startActivity(new Intent(MainActivity.this, TestActivity.class));
    }

    public void goWebViewActivity(View view) {
        startActivity(new Intent(MainActivity.this, WebViewActivity.class));
    }

    public void goRecyclerViewActivity(View view) {
        startActivity(new Intent(MainActivity.this, RecyclerViewActivity.class));
    }

}

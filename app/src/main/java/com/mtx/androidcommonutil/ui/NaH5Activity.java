package com.mtx.androidcommonutil.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.mtx.androidcommonutil.R;
import com.mtx.androidcommonutil.ui.widget.CommonWebView;

/**
 * Created by lishaoming on 16/10/30.
 * Android NA与H5交互样例
 */
public class NaH5Activity extends BaseActivity implements View.OnClickListener {
    private Button mBtn;
    private CommonWebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_na_h5);

        initView();
    }

    private void initView() {
        mBtn = (Button) findViewById(R.id.btn);
        mWebView = (CommonWebView) findViewById(R.id.webView);
        mBtn.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateUI();
    }

    private void updateUI() {
//        -----打开本包内main/assets目录下的index.html文件
        mWebView.loadUrl("file:///android_asset/index.html"); //放在assets的html需加上  android_asset/
//        mWebView.loadData(mUrlData, "text/html; charset=UTF-8", null);    //mUrlData字符串资源为html格式，且没有任何非法字符空格，回车，之类

//        -----打开本地sd卡内的index.html文件
        //mWebView.loadUrl("content://com.android.htmlfileprovider/sdcard/index.html");

//                -----打开指定URL的html文件
//        mWebView.loadUrl("http://www.baidu.com");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn:
                mWebView.loadUrl("javascript: window.addText('b')");
                break;
            default:
                break;
        }
    }
}

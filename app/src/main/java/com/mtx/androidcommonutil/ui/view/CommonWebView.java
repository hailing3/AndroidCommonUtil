package com.mtx.androidcommonutil.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.mtx.androidcommonutil.model.NaObject;

/**
 * 优化后的webview
 * Created by lishaoming on 16/10/29.
 */
public class CommonWebView extends WebView {

    public CommonWebView(Context context) {
        super(context);
    }

    public CommonWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CommonWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        init();
    }

    private void init() {
        initSetting();
        setWebViewClient(new MyWebViewClient());
        initWebTran();
    }

    private void initSetting() {
        getSettings().setJavaScriptEnabled(true); // 开启JavaScript支持

//        getSettings().setSupportZoom(true);    // 设置是否支持缩放，默认为true。
//        getSettings().setBuiltInZoomControls(false);    // 设置是否显示缩放工具，默认为false。
    }

    private class MyWebViewClient extends WebViewClient {
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            //  重写此方法表明点击网页里面的链接还是在当前的webview里跳转，不跳到浏览器那边
            view.loadUrl(url);
            return true;
        }
    }

    /**
     * 初始化一些与Web页面交互需要的内容
     */
    private void initWebTran() {
        // 添加一个对象, 让JS可以访问该对象的方法
        addJavascriptInterface(new NaObject(), "naObject");
    }
}

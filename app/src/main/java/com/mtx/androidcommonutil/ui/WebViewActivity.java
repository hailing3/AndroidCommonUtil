package com.mtx.androidcommonutil.ui;

import com.mtx.androidcommonutil.R;
import com.mtx.androidcommonutil.util.LogUtil;

import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;

/**
 * 测试webview的页面
 */
public class WebViewActivity extends BaseActivity {
    private static final String TAG = "WebViewActivity";
    private LinearLayout mContainer;
    private WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        initView();
        loadData();
    }

    private void initView() {
        mContainer = (LinearLayout) findViewById(R.id.container);
        mWebView = new WebView(this);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        mWebView.setLayoutParams(lp);
//        mWebView = (WebView) findViewById(R.id.webview);
//        mWebView.setWebChromeClient(new FavWebChromeClient());
        mWebView.setWebViewClient(new FavWebViewClient());

        final WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setCacheMode(WebSettings.LOAD_DEFAULT);
        webSettings.setTextZoom(82);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(false);
        webSettings.setUseWideViewPort(true);//关键点
        webSettings.setDisplayZoomControls(false);
        webSettings.setAllowFileAccess(true); // 允许访问文件
        webSettings.setBuiltInZoomControls(true); // 设置显示缩放按钮
        webSettings.setSupportZoom(true); // 支持缩放
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setRenderPriority(WebSettings.RenderPriority.HIGH);
        webSettings.setDomStorageEnabled(true);
        webSettings.setAppCacheEnabled(true);
        webSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);

        mWebView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                LogUtil.i(TAG, "webview onTouch");
                return false;
            }
        });

//        mContainer.addView(mWebView);

        View view = LayoutInflater.from(WebViewActivity.this).inflate(R.layout.layout_detail_like_test, null);
        mContainer.addView(view);

        mContainer.addView(mWebView, 0);


    }


    private void loadData() {
        // 有问题
//        mWebView.loadUrl("http://dict.youdao.com/recite/words/#!/dict/engchn?le=eng&chuansongmen=true&q=Data centers and smartphones will be the mostdamaging information and communications technologies to the environment by 2040, according to new research from Canadian reseacher Lotfi Belkhir.");
//        mWebView.loadUrl("http://dict.youdao.com/recite/words/#!/dict/engchn?le=eng&chuansongmen=true&q=Data%C2%A0centers%C2%A0and%C2%A0smartphones%C2%A0will%C2%A0be%C2%A0the%C2%A0mostdamaging%C2%A0information%C2%A0and%C2%A0communications%C2%A0technologies%C2%A0to%C2%A0the%C2%A0environment%C2%A0by%C2%A02040%2C%C2%A0according%C2%A0to%C2%A0new%C2%A0research%C2%A0from%C2%A0Canadian%C2%A0reseacher%C2%A0Lotfi%C2%A0Belkhir");

        // 正常
//        mWebView.loadUrl("http://dict.youdao.com/recite/words/#!/dict/engchn?le=eng&chuansongmen=true&q=Data centers and smartphones will be the mostdamaging information and communications technologies to the environment by 2040, according to new research from Canadian reseacher Lotfi Belkhir.");
//        mWebView.loadUrl("http://dict.youdao.com/recite/words/#!/dict/engchn?le=eng&chuansongmen=true&q=Data+centers+and+smartphones+will+be+the+mostdamaging+information+and+communications+technologies+to+the+environment+by+2040%2C+according+to+new+research+from+Canadian+reseacher+Lotfi+Belkhir");

        // 加载本地html
//        mWebView.loadUrl("file:///android_asset/test.html");

//        mWebView.loadUrl("https://mb.yidianzixun.com/article/0KooGEhR?ref=&s=mb");
//        mWebView.loadUrl("https://m.ctrip.com/webapp/you/operations/article/detail/4159139.html?Allianceid=826349&sid=1389655&ouid=yd&popup=close&twojumpwakeup=1&yidian_docid=0KooGEhR&yidian_ref=&yidian_s=mb");

        mWebView.loadUrl("http://doris.yidianzixun.com/article/0LB0vu05?appid=s3rd_op647");
    }

    private class FavWebViewClient extends WebViewClient {
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            LogUtil.i(TAG, "shouldOverrideUrlLoading..." + url);
            try {
                if (url.startsWith("http:") || url.startsWith("https:")) {
                    return false;
                }
            } catch (Exception e) {
                LogUtil.e(TAG, "Exception ", e);
            }
            return false;
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            LogUtil.i(TAG, "onPageStarted..." + url);
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            LogUtil.i(TAG, "onPageFinished...url=" + url);
        }

        @Override
        public void onReceivedSslError(WebView view, android.webkit.SslErrorHandler handler, SslError error) {
            handler.proceed();
        }

        @Override
        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
            LogUtil.i(TAG, "onReceivedError main page");
        }

    }
}



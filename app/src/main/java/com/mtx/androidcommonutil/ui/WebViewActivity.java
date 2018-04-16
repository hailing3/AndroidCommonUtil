package com.mtx.androidcommonutil.ui;

import com.mtx.androidcommonutil.R;
import com.mtx.androidcommonutil.util.LogUtil;

import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.PermissionRequest;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * 测试webview的页面
 */
public class WebViewActivity extends BaseActivity {
    private static final String TAG = "WebViewActivity";
    private WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        initView();
        loadData();
    }

    private void initView() {
        mWebView = (WebView) findViewById(R.id.webview);
        mWebView.setWebChromeClient(new FavWebChromeClient());
//        mWebView.setWebViewClient(new FavWebViewClient());

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
//        webSettings.setDatabaseEnabled(true);

        mWebView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                LogUtil.i(TAG, "webview onTouch");
                return false;
            }
        });
    }

    private void loadData() {
//        mWebView.loadUrl("https://www.duokan.com/www/sdk-h5/?ch=M7L3WD&_dk_no_wx_tip=0&page=book&source_id=16187&source=2");
        mWebView.loadUrl("https://www.baidu.com/");
//        mWebView.loadUrl("http://m.amap.com/search/mapview/keywords=安宁庄东路&cur_loc=116.46734,39.99161&src=xiaomi_portal");
//        mWebView.loadUrl("http://m.ctrip.com/webapp/train/home/checilist?trainnumber=Z38&allianceid=4901&sid=976016&Sourceid=2620&Sepopup=19&popup=close&hiderecommapp=1&autoawaken=close?source=miuibrowser");
    }

    private class FavWebChromeClient extends WebChromeClient {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            super.onProgressChanged(view, newProgress);
        }

        @Override
        public void onPermissionRequest(PermissionRequest request) {
//            super.onPermissionRequest(request);
            request.grant(request.getResources());
        }
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

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        LogUtil.i(TAG, "activity dispatchTouchEvent");
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        LogUtil.i(TAG, "activity onTouchEvent");
        return super.onTouchEvent(event);
    }
}



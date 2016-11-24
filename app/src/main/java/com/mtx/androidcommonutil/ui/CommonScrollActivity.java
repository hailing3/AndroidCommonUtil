package com.mtx.androidcommonutil.ui;

import android.os.Bundle;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.TextView;

import com.mtx.androidcommonutil.R;
import com.mtx.androidcommonutil.ui.widget.CommonWebView;
import com.mtx.androidcommonutil.util.LogUtil;
import com.mtx.androidcommonutil.util.statusbar.StatusBarUtil;

/**
 * 实现方式参考：https://github.com/githubwing/ByeBurger
 * WebView垂直滑动时，titleBar和bottomBar自动收起展开
 * Created by lishaoming on 16/10/30.
 */
public class CommonScrollActivity extends BaseActivity {
    private static final String TAG = "CommonScrollActivity";
    private CommonWebView mWebView;
    private TextView mTitle;

    private static final String url = "http://36kr.com/coop/toutiao/5057326.html?ktm_source=toutiao&tt_group_id=6356136333760921858";
//    private static final String url = "https://wap.baidu.com/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // 使在全屏与非全屏之切换时，屏幕内容不会发生抖动
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common_scroll);

        initView();
    }

    private void initView() {
        mTitle = (TextView) findViewById(R.id.title_text);
        mTitle.setY(StatusBarUtil.getStatusBarHeight(this));

        mWebView = (CommonWebView) findViewById(R.id.webView);
        mWebView.loadUrl(url);

        // 绘制title完毕后，获取height
        ViewTreeObserver vto2 = mTitle.getViewTreeObserver();
        vto2.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                mTitle.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                int height = mTitle.getHeight() + StatusBarUtil.getStatusBarHeight(CommonScrollActivity.this);
                mWebView.setY(height);
                LogUtil.i(TAG, "height=" + height);
            }
        });
    }


}

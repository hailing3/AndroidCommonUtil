package com.mtx.androidcommonutil.ui;

import com.mtx.androidcommonutil.R;

import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

/**
 * 专门测试TextView显示样式的界面
 * 参考：http://blog.csdn.net/singwhatiwanna/article/details/18363899
 * 或者使用SpannableString
 */
public class TextViewActivity extends BaseActivity {
    private TextView mTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_html);
        initView();
    }

    private void initView() {
        mTv = (TextView) findViewById(R.id.tv);
        // 超链接嵌套颜色设置
        String html = "北京市发布<font color='#00ff00'><a href='http://m.mi.com'>霾黄色预警</a></font>，<font color='#ff0000'><big><big>外出携带好</big></big></font>口罩";
        mTv.setText(Html.fromHtml(html));
    }
}

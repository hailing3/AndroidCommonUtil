package com.mtx.androidcommonutil.ui;

import com.mtx.androidcommonutil.R;
import com.mtx.androidcommonutil.util.ToastUtil;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class TestActivity extends BaseActivity {
    private static final String TAG = "TestActivity";
    private TextView mTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        initView();

    }

    private void initView() {
        mTv = (TextView) findViewById(R.id.tv);
        String string = getString(R.string.place_holder_rmb);
        mTv.setText(String.format(string, "100元", "50美元"));
    }

    public void testClick(View view) {
        ToastUtil.show(TestActivity.this, "hiuhuijo");
    }
}

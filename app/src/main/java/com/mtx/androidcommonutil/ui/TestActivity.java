package com.mtx.androidcommonutil.ui;

import com.mtx.androidcommonutil.R;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

public class TestActivity extends BaseActivity {
    private static final String TAG = "TestActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        initView();

    }

    private void initView() {
    }

    public void onTestButtonClick(View view) {
        Intent intent = new Intent();
        intent.setData(Uri.parse("ctrip://wireless/h5?guidtype=base&url=L3RpY2tldC9pbmRleC5odG1sIy9kZXN0L3QxNDIyMDIuaHRtbA==&type=5&partner_link={{DEEPLINK_BACKURL}}&needguid=1&allianceid=779598&sid=1336524&sourceid=2055"));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

}



package com.mtx.androidcommonutil.ui;

import com.mtx.androidcommonutil.R;
import com.mtx.androidcommonutil.util.MiStatUtil;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import java.lang.reflect.Constructor;
import java.util.HashMap;

public class TestActivity extends BaseActivity {
    private static final String TAG = "TestActivity";
    private LinearLayout mContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        initView();
    }

    private void initView() {
        mContainer = (LinearLayout) findViewById(R.id.ll_container);

        try {
            Log.d(TAG, "loadView start");
            Class clazz = Class.forName("com.miui.newhome.view.gestureview.NewHomeView");
            Constructor<View> con = clazz.getConstructor(Context.class, AttributeSet.class);
            View assistHolderView = con.newInstance(getApplicationContext(), null);
            FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            mContainer.addView(assistHolderView, lp);
            Log.d(TAG, "loadView SUCCESS");
        } catch (Exception e) {
            Log.e(TAG, "Exception", e);
        }
    }

    public void btnClick(View view) {
        HashMap<String, String> params = new HashMap<>();
        params.put("title", "testTitle");
        MiStatUtil.recordCountEvent(null, "testKey123", params);
    }

}



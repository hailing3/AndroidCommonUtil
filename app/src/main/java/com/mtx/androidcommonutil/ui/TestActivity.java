package com.mtx.androidcommonutil.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.SeekBar;

import com.mtx.androidcommonutil.R;
import com.mtx.androidcommonutil.util.LogUtil;
import com.mtx.androidcommonutil.util.statusbar.StatusBarUtil;

public class TestActivity extends BaseActivity implements SeekBar.OnSeekBarChangeListener {

    private static final String TAG = "TestActivity";
    private EditText mEdit;
    private SeekBar mSeekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // 使在全屏与非全屏之切换时，屏幕内容不会发生抖动
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        initView();

        StatusBarUtil.setStatusBarShowOrHidden(true, getWindow());
    }

    private void initView() {
        mEdit = (EditText) findViewById(R.id.edit);
        mSeekBar = (SeekBar) findViewById(R.id.seekBar);
        mSeekBar.setOnSeekBarChangeListener(this);

    }

    public void btnClickLeft(View view) {
        StatusBarUtil.setStatusBarShowOrHidden(true, getWindow());

//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN, WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
//        getWindow().getDecorView().setSystemUiVisibility(View.FLAG_FORCE_NOT_FULLSCREEN);

    }

    public void btnClickRight(View view) {
        StatusBarUtil.setStatusBarShowOrHidden(false, getWindow());

//        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_SHOW_FULLSCREEN);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        LogUtil.d(TAG, "onProgressChanged " + progress);
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        LogUtil.d(TAG, "onStartTrackingTouch " + seekBar.getProgress());
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        LogUtil.d(TAG, "onStopTrackingTouch " + seekBar.getProgress());
    }
}

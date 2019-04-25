package com.mtx.androidcommonutil.ui;

import android.os.Bundle;
import android.text.Html;
import android.widget.SeekBar;
import android.widget.TextView;

import com.mtx.androidcommonutil.R;
import com.mtx.androidcommonutil.ui.view.ShadeImageView;

public class ViewActivity extends BaseActivity {
    private static final String TAG = "ViewActivity";
    ShadeImageView mIv;
    SeekBar mSeekBar;
    TextView mTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        initView();
    }

    private void initView() {
        mIv = (ShadeImageView) findViewById(R.id.iv_test);

        mSeekBar = (SeekBar) findViewById(R.id.seekBar);
        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                float percent = (float) progress / 100;
                mIv.setPercent(percent);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        mSeekBar.setProgress(0);

        mTv = (TextView) findViewById(R.id.tv);
    }
}
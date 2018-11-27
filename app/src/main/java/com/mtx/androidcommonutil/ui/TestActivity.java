package com.mtx.androidcommonutil.ui;

import com.mtx.androidcommonutil.R;
import com.mtx.androidcommonutil.util.ColorUtil;
import com.mtx.androidcommonutil.util.DrawableUtil;
import com.mtx.androidcommonutil.util.LogUtil;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.SeekBar;

public class TestActivity extends BaseActivity {
    private static final String TAG = "TestActivity";
    ImageView mIv;
    SeekBar mSeekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        initView();
    }

    private void initView() {
        mIv = (ImageView) findViewById(R.id.iv);
        final Drawable drawable = mIv.getDrawable();


        mSeekBar = (SeekBar) findViewById(R.id.seekBar);
        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                float percent = (float) progress / 100;
                String color = ColorUtil.caculateColor("#FF000000", "#FFFFFFFF", percent);
                LogUtil.i(TAG, "percent = " + percent + ", color = " + color);
                mIv.setImageDrawable(DrawableUtil.tintDrawable(drawable, Color.parseColor(color)));


                Drawable backgroudDraw = mIv.getBackground();
                backgroudDraw.setAlpha((int) (percent * 255));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        mSeekBar.setProgress(0);
    }
}
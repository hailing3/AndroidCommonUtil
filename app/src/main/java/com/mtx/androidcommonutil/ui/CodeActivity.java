package com.mtx.androidcommonutil.ui;

import com.google.zxing.WriterException;

import com.mtx.androidcommonutil.R;
import com.mtx.androidcommonutil.util.EncodingHandlerUtil;
import com.mtx.androidcommonutil.util.ToastUtil;
import com.uuzuche.lib_zxing.activity.CaptureActivity;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * https://github.com/yipianfengye/android-zxingLibrary
 * 二维码
 */
public class CodeActivity extends BaseActivity {
    private static final int REQUEST_CODE = 0;
    private EditText mEdit;
    private ImageView mIv;
    private TextView mTvSessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_code);
        init();
    }

    private void init() {
        mEdit = (EditText) findViewById(R.id.edit_code);
        mIv = (ImageView) findViewById(R.id.iv_code);
        mTvSessage = (TextView) findViewById(R.id.tv_message);
    }

    /**
     * 生成二维码
     */
    public void getQrCode(View v) {
        String message = mEdit.getText().toString().trim();
        if (TextUtils.isEmpty(message)) {
            ToastUtil.show(CodeActivity.this, "input empty");
        } else {
            Bitmap mBitmap = CodeUtils.createImage(message, 400, 400, BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));
            mIv.setImageBitmap(mBitmap);
        }
    }

    /**
     * 跳转到扫描二维码界面：注意必须手动打开相机权限，否则黑屏
     */
    public void scanCode(View v) {
        Intent intent = new Intent(CodeActivity.this, CaptureActivity.class);
        startActivityForResult(intent, REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE) {
            //处理扫描结果（在界面上显示）
            if (null != data) {
                Bundle bundle = data.getExtras();
                if (bundle == null) {
                    return;
                }
                if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
                    String result = bundle.getString(CodeUtils.RESULT_STRING);
                    mTvSessage.setText(result);
                } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
                    mTvSessage.setText("解析二维码失败");
                }
            }
        }
    }

    /**
     * 生成条形码
     */
    public void getBarCode(View view) {
        String message = mEdit.getText().toString().trim();
        if (TextUtils.isEmpty(message)) {
            ToastUtil.show(CodeActivity.this, "input empty");
        } else {
            Bitmap mBitmap = null;
            try {
                mBitmap = EncodingHandlerUtil.createBarCode(message);
                mIv.setImageBitmap(mBitmap);
            } catch (WriterException e) {
                e.printStackTrace();
            }
        }

    }
}

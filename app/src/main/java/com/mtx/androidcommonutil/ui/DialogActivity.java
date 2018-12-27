package com.mtx.androidcommonutil.ui;

import com.mtx.androidcommonutil.R;
import com.mtx.androidcommonutil.ui.view.DetailDialog;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

/**
 * Dialog样式的Activity
 */
public class DialogActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);
        initView();
    }

    private void initView() {

    }

    public void showDialog(View view) {
        DetailDialog dialog = new DetailDialog(DialogActivity.this);
        dialog.show();
    }
}

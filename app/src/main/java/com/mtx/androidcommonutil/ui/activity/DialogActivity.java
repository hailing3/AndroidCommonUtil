package com.mtx.androidcommonutil.ui.activity;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import com.mtx.androidcommonutil.R;

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

    /**
     * 键盘输入框
     */
//    public void showDialog(View view) {
//        DetailDialog dialog = new DetailDialog(DialogActivity.this);
//        dialog.show();
//    }
    public void showDialog(View view) {
        Dialog mDailog = new Dialog(DialogActivity.this);

        mDailog.setContentView(R.layout.layout_item);
        mDailog.show();

        Window window = mDailog.getWindow();
        window.setWindowAnimations(R.style.main_menu_animStyle);
    }
}

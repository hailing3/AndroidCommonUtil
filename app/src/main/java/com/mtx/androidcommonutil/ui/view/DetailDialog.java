package com.mtx.androidcommonutil.ui.view;

import com.mtx.androidcommonutil.R;
import com.mtx.androidcommonutil.util.KeyboardUtil;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;


/**
 * 详情页封装的dialog
 * Created by lishaoming on 18-12-4.
 */
public class DetailDialog {
    private Context mContext;
    private AlertDialog.Builder mBuilder;
    private AlertDialog dialog;
    private View mView;
    private LayoutInflater mInflater;
    private EditText mEditText;

    public DetailDialog(Context context) {
        this.mContext = context;
        this.mInflater = LayoutInflater.from(context);

        // dialog显示出自动弹出软键盘，参考：https://blog.csdn.net/qq_35605213/article/details/80137292
        mBuilder = new AlertDialog.Builder(mContext, R.style.inputDialog);
        mView = mInflater.inflate(R.layout.layout_detail_bottombar_pop, null);
        mEditText = (EditText) mView.findViewById(R.id.edit_detail_bottom_pop);
        mBuilder.setView(mView);

        dialog = mBuilder.create();

        Window window = dialog.getWindow();
        window.setGravity(Gravity.BOTTOM);
    }

    public void show() {
        dialog.show();

        Window win = dialog.getWindow();
        win.getDecorView().setPadding(0, 0, 0, 0);
        WindowManager.LayoutParams lp = win.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        win.setAttributes(lp);

        WindowManager windowManager = win.getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        WindowManager.LayoutParams lp2 = dialog.getWindow().getAttributes();
        lp2.width = (int)(display.getWidth()); //设置宽度
        dialog.getWindow().setAttributes(lp);
//        win.setContentView(R.layout.layout_detail_bottombar_pop);

//        KeyboardUtil.showSoftInput(mEditText);

    }

}

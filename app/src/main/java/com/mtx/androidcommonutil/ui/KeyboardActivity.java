package com.mtx.androidcommonutil.ui;

import com.mtx.androidcommonutil.R;
import com.mtx.androidcommonutil.ui.listener.SoftKeyBoardListener;

import android.app.Service;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.Toast;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class KeyboardActivity extends BaseActivity {
    private static final String TAG = "KeyboardActivity";
    private Button btn_submit;
    private PopupWindow popupWindow;
    private int mKeyboardHeight = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keyboard);
        initView();
    }

    private void initView() {
        //注册软键盘的监听
        SoftKeyBoardListener.setListener(KeyboardActivity.this,
                new SoftKeyBoardListener.OnSoftKeyBoardChangeListener() {
                    @Override
                    public void keyBoardShow(int height) {
                        mKeyboardHeight = height;
                        Toast.makeText(KeyboardActivity.this,
                                "键盘显示 高度" + height, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void keyBoardHide(int height) {
                        Toast.makeText(KeyboardActivity.this,
                                "键盘隐藏 高度" + height, Toast.LENGTH_SHORT).show();
                        if (popupWindow != null) {
                            popupWindow.dismiss();
                        }
                    }
                });

    }

    /**
     * show soft input
     */
    private void popupInputMethodWindow() {
        new Thread() {
            @Override
            public void run() {
                super.run();
                InputMethodManager imm = (InputMethodManager) getSystemService(Service.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }.start();
    }

    /**
     * show comment popupwindow（弹出评论的popupWindow）
     */
    private void showPopupCommnet(final int pid, final int type) {// pe表示是评论还是举报1.代表评论。2.代表举报
        View view = LayoutInflater.from(KeyboardActivity.this).inflate(
                R.layout.comment_popupwindow, null);
        final EditText inputComment = (EditText) view
                .findViewById(R.id.comment);

        btn_submit = (Button) view.findViewById(R.id.submit_comment);
        if (type == 1) {
            btn_submit.setText("评论");
            inputComment.setHint("请输入评论");
        }
        popupWindow = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT, true);

        popupWindow.setTouchable(true);
        popupWindow.setTouchInterceptor(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
            }
        });
        popupWindow.setFocusable(true);
        // 设置点击窗口外边窗口消失
        popupWindow.setOutsideTouchable(true);
//        popupWindow.setBackgroundDrawable(getResources().getDrawable(
//                R.drawable.popuwindow_white_bg));

        // 设置弹出窗体需要软键盘
        popupWindow.setSoftInputMode(PopupWindow.INPUT_METHOD_NEEDED);
        // 再设置模式，和Activity的一样，覆盖，调整大小。
        popupWindow
                .setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        popupWindow.showAtLocation(view, Gravity.BOTTOM, 0, mKeyboardHeight + 200);
        ColorDrawable cd = new ColorDrawable(0x000000);
        popupWindow.setBackgroundDrawable(cd);
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.alpha = 0.4f;
        getWindow().setAttributes(params);
        // 设置popWindow的显示和消失动画
        popupWindow.setAnimationStyle(R.style.mypopwindow_anim_style);
        popupWindow.update();
        popupInputMethodWindow();
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {

            // 在dismiss中恢复透明度
            public void onDismiss() {
                WindowManager.LayoutParams params = getWindow().getAttributes();
                params.alpha = 1f;
                getWindow().setAttributes(params);
            }
        });
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // btn_submit.setClickable(false);
                String comment1 = inputComment.getText().toString().trim();
                if (comment1.length() <= 0) {
                    if (type == 1) {
                        Toast.makeText(KeyboardActivity.this, "评论内容不能为空",
                                Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(KeyboardActivity.this, "有非法内容",
                                Toast.LENGTH_SHORT).show();
                    }
                    return;
                }
                String comment2 = null;
                try {
                    comment2 = URLEncoder.encode(comment1, "utf-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                final String finalComment = comment2;
                popupWindow.dismiss();

                Toast.makeText(KeyboardActivity.this, finalComment,
                        Toast.LENGTH_SHORT).show();
                // 提交评论
                // submitComment(finalComment, pid);
            }
        });
    }

    public void show(View view) {
        showPopupCommnet(1, 1);
    }

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        if (popupWindow != null) {
            popupWindow.dismiss();
        }
    }

}
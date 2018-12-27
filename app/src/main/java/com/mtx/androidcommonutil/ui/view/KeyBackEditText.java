package com.mtx.androidcommonutil.ui.view;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.widget.EditText;

/**
 * back时，令输入法消失的同时，把back事件传给activity
 * Created by lishaoming on 18-12-21.
 */
public class KeyBackEditText extends EditText {
    public KeyBackEditText(Context context) {
        super(context);
    }

    public KeyBackEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public KeyBackEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchKeyEventPreIme(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
            if (getContext() != null && getContext() instanceof Activity)
                ((Activity) getContext()).onBackPressed();
            return true;
        }
        return super.dispatchKeyEventPreIme(event);
    }

}

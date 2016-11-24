package com.mtx.androidcommonutil.ui.widget.applayout.behavior;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.view.View;

import com.mtx.androidcommonutil.ui.widget.applayout.TranslateAnimateHelper;
import com.mtx.androidcommonutil.util.LogUtil;
import com.mtx.androidcommonutil.util.StatusBarUtil;

/**
 * Bye Bye Burger Navigation Bar Behavior
 * <p>
 * Created by wing on 11/5/16.
 */
public class ByeBurgerBottomBehavior extends ByeBurgerBehavior {
    private static final String TAG = "ByeBurgerBottomBehavior";
    private Context mContext;
    private TranslateAnimateHelper mAnimateHelper;
    private boolean isShowTitleBar = true;

    public ByeBurgerBottomBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
    }

    @Override
    public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, View child, View target,
                                  int dx, int dy, int[] consumed) {
        if (isFirstMove) {
            isFirstMove = false;
            mAnimateHelper = TranslateAnimateHelper.get(child, mContext);
            mAnimateHelper.setStartY(child.getY());
            mAnimateHelper.setMode(TranslateAnimateHelper.MODE_BOTTOM);
        }
        if (Math.abs(dy) > mTouchSlop) {
            if (dy < 0) {
                if (mAnimateHelper.getState() == TranslateAnimateHelper.STATE_HIDE) {
                    mAnimateHelper.show();
                    isShowTitleBar = true;
                }
            } else if (dy > 0) {
                if (mAnimateHelper.getState() == TranslateAnimateHelper.STATE_SHOW) {
                    mAnimateHelper.hide();
                    isShowTitleBar = false;
                }
            }
        }
    }

    @Override
    public void onStopNestedScroll(CoordinatorLayout coordinatorLayout, View child, View target) {
        super.onStopNestedScroll(coordinatorLayout, child, target);
        if (isShowTitleBar) {
            LogUtil.d(TAG, "显示");
        } else {
            LogUtil.d(TAG, "隐藏");
        }

        mHandler.removeCallbacksAndMessages(null);
        mHandler.sendEmptyMessageDelayed(isShowTitleBar ? 1 : 0, 300);
    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            updateStatusBar(msg.what == 1);
        }
    };

    private void updateStatusBar(boolean isShow) {
        if (mContext instanceof Activity) {
            StatusBarUtil.setStatusBarShowOrHidden(isShow, ((Activity) mContext).getWindow());
        }
    }
}

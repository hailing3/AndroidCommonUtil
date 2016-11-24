package com.mtx.androidcommonutil.ui.widget.applayout.behavior;

import android.app.Activity;
import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.view.View;
import android.view.Window;

import com.mtx.androidcommonutil.ui.widget.applayout.TranslateAnimateHelper;
import com.mtx.androidcommonutil.util.statusbar.StatusBarUtil;

/**
 * Bye Bye Burger Android Title Bar Behavior
 * Created by wing on 11/4/16.
 */
public class ByeBurgerTitleBehavior extends ByeBurgerBehavior {
    private static final String TAG = "ByeBurgerTitleBehavior";
    private Context mContext;
    private TranslateAnimateHelper mAnimateHelper;
    private boolean isShowStatusBar = true;

    public ByeBurgerTitleBehavior(Context context, AttributeSet attrs) {
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
            mAnimateHelper.setMode(TranslateAnimateHelper.MODE_TITLE);
        }
        if (Math.abs(dy) > mTouchSlop) {
            if (dy < 0) {
                if (mAnimateHelper.getState() == TranslateAnimateHelper.STATE_HIDE) {
                    mAnimateHelper.show();
                    isShowStatusBar = true;
                    updateStatusBar(true);
                }
            } else if (dy > 0) {
                if (mAnimateHelper.getState() == TranslateAnimateHelper.STATE_SHOW) {
                    mAnimateHelper.hide();
                    isShowStatusBar = false;
                    updateStatusBar(false);
                }
            }
        }
    }

    private void updateStatusBar(boolean isShow) {
        if (mContext != null && mContext instanceof Activity) {
            Window window = ((Activity) mContext).getWindow();
            StatusBarUtil.setStatusBarShowOrHidden(isShow, window);
        }
    }

}


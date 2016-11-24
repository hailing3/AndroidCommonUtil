package com.mtx.androidcommonutil.ui.widget.applayout;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.view.View;
import android.view.Window;

import com.mtx.androidcommonutil.util.LogUtil;
import com.mtx.androidcommonutil.util.statusbar.StatusBarUtil;

/**
 * A Animate Helper to control view's visibility
 * Created by wing on 11/5/16.
 */
public class TranslateAnimateHelper implements AnimateHelper {
    private static final String TAG = "TranslateAnimateHelper";
    public View mTarget;
    public float mStartY;
    public int mCurrentState = STATE_SHOW;
    public int mMode = MODE_TITLE;
    public static int MODE_TITLE = 233;
    public static int MODE_BOTTOM = 2333;
    private float mFirstY = 0;
    private float mMargin;
    private Context mContext;
    private static final int ANIM_DURATION = 300;

    private TranslateAnimateHelper(View view) {
        this(view, null);
    }

    private TranslateAnimateHelper(View view, Context context) {
        mContext = context;
        mTarget = view;
        mFirstY = mTarget.getY();
        mMargin = ((CoordinatorLayout.LayoutParams) mTarget.getLayoutParams()).topMargin
                + ((CoordinatorLayout.LayoutParams) mTarget.getLayoutParams()).bottomMargin;
    }

    public static TranslateAnimateHelper get(View target) {
        return new TranslateAnimateHelper(target);
    }

    public static TranslateAnimateHelper get(View target, Context context) {
        return new TranslateAnimateHelper(target, context);
    }

    public void show() {
        if (mMode == MODE_TITLE) {
            showTitle();
        } else if (mMode == MODE_BOTTOM) {
            showBottom();
        }
    }

    private void hideTitle() {
        LogUtil.d(TAG, "hideTitle mTarget.getY() = " + mTarget.getY() + ", mTarget.getHeight() = " + mTarget.getHeight() + ", getStatusBarVisiBleHeight() = " + getStatusBarVisiBleHeight());
        ValueAnimator va = ValueAnimator.ofFloat(mTarget.getY(), getStatusBarVisiBleHeight() - mTarget.getHeight());
        va.setDuration(ANIM_DURATION);
        va.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                mTarget.setY((Float) valueAnimator.getAnimatedValue());
            }
        });
        va.start();
        mCurrentState = STATE_HIDE;
    }

    private void showTitle() {
        LogUtil.d(TAG, "showTitle  mTarget.getY()=" + mTarget.getY() + ", getStatusBarVisiBleHeight()=" + getStatusBarVisiBleHeight());
        ValueAnimator va = ValueAnimator.ofFloat(mTarget.getY(), getStatusBarVisiBleHeight());
        va.setDuration(ANIM_DURATION);
        va.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                mTarget.setY((Float) valueAnimator.getAnimatedValue());
            }
        });
        va.start();
        mCurrentState = STATE_SHOW;
    }

    public void hide() {
        if (mMode == MODE_TITLE) {
            hideTitle();
        } else if (mMode == MODE_BOTTOM) {
            hideBottom();
        }
    }

    private void showBottom() {
//        LogUtil.d(TAG, "showBottom  mTarget.getY()=" + mTarget.getY() + ", mFirstY = " + mFirstY + ", getStatusBarVisiBleHeight()=" + getStatusBarVisiBleHeight());
        ValueAnimator va = ValueAnimator.ofFloat(mTarget.getY(), mFirstY + getStatusBarVisiBleHeight());
        va.setDuration(ANIM_DURATION);
        va.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                mTarget.setY((Float) valueAnimator.getAnimatedValue());
            }
        });

        va.start();
        mCurrentState = STATE_SHOW;
    }

    private void hideBottom() {
//        LogUtil.d(TAG, "hideBottom  mFirstY + mTarget.getHeight() + mMargin = " + (mFirstY + mTarget.getHeight() + mMargin) + ", getStatusBarVisiBleHeight() = " + getStatusBarVisiBleHeight());
        ValueAnimator va = ValueAnimator.ofFloat(mTarget.getY(), mFirstY + mTarget.getHeight() + mMargin + getStatusBarVisiBleHeight());
        va.setDuration(ANIM_DURATION);
        va.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                mTarget.setY((Float) valueAnimator.getAnimatedValue());
            }
        });

        va.start();
        mCurrentState = STATE_HIDE;
    }

    /**
     * 考虑状态栏显示与否时，view滑动的距离
     *
     * @return
     */
    private int getStatusBarVisiBleHeight() {
        int height = 0;
        if (mContext != null && mContext instanceof Activity) {
            Window window = ((Activity) mContext).getWindow();
            if (!StatusBarUtil.isStatusBarShow(window)) {
                height = StatusBarUtil.getStatusBarHeight(mContext);
            }
        }
        return height;
    }

    public void setStartY(float y) {
        mStartY = y;
    }

    public int getState() {
        return mCurrentState;
    }

    public void setMode(int mode) {
        mMode = mode;
    }

    private void setState(int state) {
        mCurrentState = state;
    }
}

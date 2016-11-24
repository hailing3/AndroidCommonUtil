package com.mtx.androidcommonutil.ui.widget.applayout.behavior;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;

/**
 * Base Behavior
 * Created by wing on 11/8/16.
 */

public class ByeBurgerBehavior extends CoordinatorLayout.Behavior<View> {

    protected final int mTouchSlop; // 检测滑动距离超过一定距离后，才移动title和bottom
    protected boolean isFirstMove = true;

    public ByeBurgerBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
//        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
        mTouchSlop = 0;
    }

    // on Scroll Started
    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, View child,
                                       View directTargetChild, View target, int nestedScrollAxes) {
        return (nestedScrollAxes & ViewCompat.SCROLL_AXIS_VERTICAL) != 0;
    }
}

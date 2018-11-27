package com.mtx.androidcommonutil.ui.view;

import com.mtx.androidcommonutil.R;
import com.mtx.androidcommonutil.util.LogUtil;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.widget.FrameLayout;
import android.widget.Scroller;

import java.lang.reflect.Field;

/**
 * 自定义拖动view，放开时自动吸顶
 * Created by lishaoming on 18-10-15.
 */
public class DragFrameLayout extends FrameLayout {
    private static final String TAG = "DragFrameLayout";
    public static final int TOUCH_THRESHOLD = 5;

    public int margin_edge;
    private Scroller scroller;
    private float downX, downY;
    private float lastX, lastY;
    private float curX, curY;
    private int lastOffset;
    private int width, height; // 屏幕尺寸
    private int viewHeight, viewWidth;
    private int statusBarHeight;
    private Callback callback;

    public DragFrameLayout(@NonNull Context context) {
        super(context);
        init(context, null);
    }

    public DragFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public DragFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        resolveAttr(context, attrs);
        scroller = new Scroller(getContext());
        DisplayMetrics displayMetrics = this.getResources().getDisplayMetrics();
        width = displayMetrics.widthPixels;
        height = displayMetrics.heightPixels;
        statusBarHeight = getStatusBarHeight();
        if (statusBarHeight == 0) {
            statusBarHeight = (int) (25 * displayMetrics.scaledDensity + 0.5f);
        }
        height -= statusBarHeight;
        // 还需要减去actionBar的高度
        margin_edge = 10;
    }

    private void resolveAttr(Context context, AttributeSet attrs) {
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.DragFrameLayout);
        margin_edge = array.getDimensionPixelSize(R.styleable.DragFrameLayout_margin_edge, 10);
        array.recycle();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        viewWidth = getWidth();
        viewHeight = getHeight();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (scroller.computeScrollOffset()) {
            return super.onTouchEvent(event);
        }
        curX = event.getRawX();
        curY = event.getRawY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                downX = lastX = event.getRawX();
                downY = lastY = event.getRawY();
                break;
            case MotionEvent.ACTION_MOVE:
                onMove();
                lastX = curX;
                lastY = curY;
                break;
            case MotionEvent.ACTION_UP:
                onScrollEdge();
                break;
        }
        return true;
    }

    private void onMove() {
        int dx = (int) (curX - lastX);
        int dy = (int) (curY - lastY);

        if (getLeft() + dx < margin_edge) {
            dx = 0;
        } else if (getLeft() + viewWidth + dx > width - margin_edge) {
            dx = 0;
        }

        if (getTop() + dy < margin_edge) {
            dy = 0;
        } else if (getTop() + viewHeight + dy > height - margin_edge) {
            dy = 0;
        }
        LogUtil.e(TAG, "onMove: getLeft=" + getLeft() + " dx=" + dx + " dy=" + dy);
        setPosition1(dx, dy);
    }

    private void setPosition1(int dx, int dy) {
        layout(getLeft() + dx, getTop() + dy, getLeft() + viewWidth + dx, getTop() + viewHeight + dy);
    }

    private void setPosition2(int dx, int dy) {
        offsetLeftAndRight(dx);
        offsetTopAndBottom(dy);
    }

    int transX = 0;
    int transY = 0;

    /**
     * 需要修正实现方式
     */
    private void setPosition3(int dx, int dy) {
        transX += dx;
        transY += dy;
        setTranslationX(transX);
        setTranslationY(transY);
    }

    private void setPosition4(int dx, int dy) {
        LayoutParams layoutParams = (LayoutParams) getLayoutParams();
        layoutParams.rightMargin = layoutParams.rightMargin - dx;
        layoutParams.topMargin = layoutParams.topMargin + dy;
        setLayoutParams(layoutParams);
    }

    // 开始执行滑动到边缘的操作
    private void onScrollEdge() {
        LogUtil.i(TAG, "onScrollEdge: getScrollX=" + getScrollX() + " getScrollY=" + getScrollY());
        // 抬起时距离较小，判定为点击事件，否则为拖动事件
        if (Math.abs(curX - downX) < TOUCH_THRESHOLD && Math.abs(curY - downY) < TOUCH_THRESHOLD) {
            if (callback != null) {
                callback.onClick();
            }
            return;
        }
        int dy;
        if (getTop() > (height - getBottom())) { // 靠下半部分
            dy = height - getBottom() - margin_edge - getStatusBarHeight() - getHeight() / 2;
        } else {
            dy = margin_edge - getTop();
        }
        lastOffset = 0;
        scroller.startScroll(getScrollX(), getScrollY(), 0, dy);
        invalidate();
    }

    // 松开时view自动滑动到边缘
    @Override
    public void computeScroll() {
        if (scroller.computeScrollOffset()) {
            LogUtil.i(TAG, "computeScroll: getTop=" + getTop() + " currY=" + scroller.getCurrY());
            int dy = scroller.getCurrY() - lastOffset;
            lastOffset = scroller.getCurrY();
            setPosition1(0, dy);
            invalidate();
        }
        super.computeScroll();
    }

    public int getStatusBarHeight() {
        if (statusBarHeight == 0) {
            try {
                Class<?> c = Class.forName("com.android.internal.R$dimen");
                Object o = c.newInstance();
                Field field = c.getField("status_bar_height");
                int x = (Integer) field.get(o);
                statusBarHeight = getResources().getDimensionPixelSize(x);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return statusBarHeight;
    }

    public void setCallback(Callback callback) {
        this.callback = callback;
    }

    public interface Callback {
        void onClick();
    }
}

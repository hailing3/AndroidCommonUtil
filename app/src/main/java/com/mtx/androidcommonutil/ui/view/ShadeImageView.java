package com.mtx.androidcommonutil.ui.view;

import com.mtx.androidcommonutil.util.ColorUtil;
import com.mtx.androidcommonutil.util.DrawableUtil;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * 自定义适用于详情页 上滑阴影渐变的back键等
 * Created by lishaoming on 18-11-28.
 */
public class ShadeImageView extends ImageView {
    private String startColor = "#FF000000", endColor = "#FFFFFFFF";
    private float percent = 0; // 百分比 0-1f
    private Drawable mDrawableCache;

    public ShadeImageView(Context context) {
        this(context, null);
    }

    public ShadeImageView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ShadeImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mDrawableCache = getDrawable();
        getBackground().setAlpha(0);
    }

    public String getStartColor() {
        return startColor;
    }

    public void setStartColor(String startColor) {
        this.startColor = startColor;
    }

    public String getEndColor() {
        return endColor;
    }

    public void setEndColor(String endColor) {
        this.endColor = endColor;
    }

    public float getPercent() {
        return percent;
    }

    public void setPercent(float percent) {
        this.percent = percent;
        String color = ColorUtil.caculateColor(startColor, endColor, percent);
        setImageDrawable(DrawableUtil.tintDrawable(mDrawableCache, Color.parseColor(color)));
        getBackground().setAlpha((int) (percent * 255));
    }
}

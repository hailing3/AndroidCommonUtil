package com.mtx.androidcommonutil.ui.view;

import android.content.Context;
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
    
    /**
     * alpha 0-1
     * src白 -> background黑
     *
     * @param percent
     */
    public void setPercent(float percent) {
        this.percent = percent;
        // 对src设置在特定范围内上色
//        String color = ColorUtil.caculateColor(startColor, endColor, percent);
//        setImageDrawable(DrawableUtil.tintDrawable(mDrawableCache, Color.parseColor(color)));
//        getBackground().setAlpha((int) (percent * 255));

        // background和src分别从0-1alpha变化
        Drawable drawable = getDrawable();
        drawable.setAlpha((int) ((1 - percent) * 255));
        setImageDrawable(drawable);
        getBackground().setAlpha((int) (percent * 255));
    }
}

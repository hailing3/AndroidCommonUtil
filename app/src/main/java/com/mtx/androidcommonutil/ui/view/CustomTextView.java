package com.mtx.androidcommonutil.ui.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import com.mtx.androidcommonutil.R;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * 自定义TextView，实现自定义onMeasure，onDraw方法
 * 点击切换随机数字
 */
@SuppressLint("AppCompatCustomView")
public class CustomTextView extends TextView {
    private String mText;
    private int mTextColor, mBgColor, mTextSize;

    private Paint mPaint;
    private Rect mRect;

    public CustomTextView(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.CustomTextView);
        mText = array.getString(R.styleable.CustomTextView_CustomText);
        mTextColor = array.getColor(R.styleable.CustomTextView_CustomTextColor, getResources().getColor(R.color.black));
        mBgColor = array.getColor(R.styleable.CustomTextView_CustomBgColor, getResources().getColor(R.color.white));
        mTextSize = array.getDimensionPixelSize(R.styleable.CustomTextView_CustomTextSize, getResources().getDimensionPixelSize(R.dimen.sp_24));
        mPaint = new Paint();
        mRect = new Rect();
        mPaint.getTextBounds(mText, 0, mText.length(), mRect);

        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                mText = randomText();
                postInvalidate();
            }
        });
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int width;
        int height;
        if (widthMode == MeasureSpec.EXACTLY) {
            width = widthSize;
        } else {
            mPaint.setTextSize(mTextSize);
            mPaint.getTextBounds(mText, 0, mText.length(), mRect);
            float textWidth = mRect.width();
            int desired = (int) (getPaddingLeft() + textWidth + getPaddingRight());
            width = desired;
        }

        if (heightMode == MeasureSpec.EXACTLY) {
            height = heightSize;
        } else {
            mPaint.setTextSize(mTextSize);
            mPaint.getTextBounds(mText, 0, mText.length(), mRect);
            float textHeight = mRect.height();
            int desired = (int) (getPaddingTop() + textHeight + getPaddingBottom());
            height = desired;
        }
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setColor(mTextColor);
        canvas.drawRect(0, 0, getWidth(), getHeight(), mPaint);

        mPaint.setColor(mBgColor);
        canvas.drawText(mText, getWidth() / 2 - mRect.width() / 2, getHeight() / 2 + mRect.height() / 2, mPaint);
    }

    private String randomText() {
        Random random = new Random();
        Set<Integer> set = new HashSet<Integer>();
        while (set.size() < 4) {
            int randomInt = random.nextInt(10);
            set.add(randomInt);
        }
        StringBuffer sb = new StringBuffer();
        for (Integer i : set) {
            sb.append("" + i);
        }

        return sb.toString();
    }

}

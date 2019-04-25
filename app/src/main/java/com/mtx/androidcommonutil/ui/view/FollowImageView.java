package com.mtx.androidcommonutil.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.mtx.androidcommonutil.R;

public class FollowImageView extends RelativeLayout {
    private ImageView mIvIcon, mIvCircleMark;

    public FollowImageView(Context context) {
        this(context, null);
    }

    public FollowImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FollowImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(context).inflate(R.layout.layout_follow_iv_icon, this, true);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mIvIcon = (ImageView) findViewById(R.id.iv_follow_authors_avatar);
        mIvCircleMark = (ImageView) findViewById(R.id.iv_follow_circle);
    }

    public void showMark(boolean showMark) {
        mIvCircleMark.setVisibility(showMark ? View.VISIBLE : INVISIBLE);
    }

    public void setImageResource(int imageResource) {
        mIvIcon.setImageResource(imageResource);
    }

    public ImageView getIvIcon() {
        return mIvIcon;
    }
}

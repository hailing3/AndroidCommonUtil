package com.mtx.androidcommonutil.ui.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Layout;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.MotionEvent;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import com.mtx.androidcommonutil.R;
import com.mtx.androidcommonutil.ui.view.ShadeImageView;
import com.mtx.androidcommonutil.util.LogUtil;
import com.mtx.androidcommonutil.util.ToastUtil;

/**
 * 自定义view的界面
 */
public class ViewActivity extends BaseActivity {
    private static final String TAG = "ViewActivity";
    ShadeImageView mIv;
    SeekBar mSeekBar;
    TextView mTv;

    final String content = "456//@咖啡瘾少女1：111//@咖啡瘾少女2：123//@咖啡瘾少女3：再次转发//@我爱咖啡瘾少女2：123//@我不爱咖啡瘾少女2：123";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        initView();
    }

    private void initView() {
        mIv = (ShadeImageView) findViewById(R.id.iv_test);

        mSeekBar = (SeekBar) findViewById(R.id.seekBar);
        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                float percent = (float) progress / 100;
                mIv.setPercent(percent);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        mSeekBar.setProgress(0);

        mTv = (TextView) findViewById(R.id.tv);

        SpannableStringBuilder builder = new SpannableStringBuilder(content);
        char[] cArray = content.toCharArray();
        int startIndex = -1;
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < cArray.length; i++) {
            char c = cArray[i];
            if (isStartOrEnd(cArray, i, PREFIX)) { // 是开始位置
                startIndex = i + PREFIX.length();
                sb = new StringBuffer();
                i = startIndex - 1;
            } else if (isStartOrEnd(cArray, i, SUFFIX)) { // 是结束位置
                String result = sb.toString();
                System.out.println(result + ' ' + startIndex + "-" + i);
                // startIndex -1 为了从@号开始
                builder.setSpan(new TextClick(result), startIndex - 1, i, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

                startIndex = -1;
                sb = new StringBuffer();
            } else if (startIndex > 0) { // 已经开始了，追加字符串
                sb.append(c);
            }
        }
        mTv.setHighlightColor(getResources().getColor(android.R.color.transparent)); // 解决点击后有背景色的问题 参考https://blog.csdn.net/ch_kexin/article/details/84811242
        // 方法1：解决textview里面点击和父view点击冲突的问题，取代下面方法  https://www.jianshu.com/p/413184996fc8
        mTv.setOnTouchListener(ClickMovementMethod.getInstance());
        // 方法2：传统的textview内部点击方法
//        mTv.setMovementMethod(LinkMovementMethod.getInstance());
        mTv.setText(builder);

    }

    private class TextClick extends ClickableSpan {
        private String name;

        public TextClick(String name) {
            this.name = name;
        }

        @Override
        public void onClick(View widget) {
            ToastUtil.show(getApplicationContext(), name);
        }

        @Override
        public void updateDrawState(TextPaint ds) {
            LogUtil.i(TAG, "updateDrawState name = " + name + ", TextPaint= " + ds);
            ds.setColor(Color.RED);
        }
    }


    private static final String PREFIX = "//@";
    private static final String SUFFIX = "："; // 注意此处是中文冒号

    /**
     * 是否是开始或结束的位置
     *
     * @param cArray      源字符串
     * @param i           开始检查的位置
     * @param preOrSufFix 进行对比的前缀或后缀字符串
     * @return
     */
    private boolean isStartOrEnd(char[] cArray, int i, String preOrSufFix) {
        if (i >= 0 && i + preOrSufFix.length() - 1 < cArray.length) {
            char[] prefixArray = preOrSufFix.toCharArray();
            for (int j = 0; j < prefixArray.length; j++) {
                if (cArray[i + j] != prefixArray[j]) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }


    /**
     * 解决LinkMovementMethod点击事件无法生效的问题，处理事件分发
     */
    public static class ClickMovementMethod implements View.OnTouchListener {
        private static ClickMovementMethod sInstance;

        public static ClickMovementMethod getInstance() {
            if (sInstance == null) {
                sInstance = new ClickMovementMethod();
            }
            return sInstance;
        }

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            boolean ret = false;
            TextView widget = (TextView) v;
            CharSequence text = widget.getText();
            Spannable spannable = Spannable.Factory.getInstance().newSpannable(text);
            int action = event.getAction();
            if (action == MotionEvent.ACTION_UP || action == MotionEvent.ACTION_DOWN) {
                int x = (int) event.getX();
                int y = (int) event.getY();
                x -= widget.getTotalPaddingLeft();
                y -= widget.getTotalPaddingTop();
                x += widget.getScrollX();
                y += widget.getScrollY();
                Layout layout = widget.getLayout();
                int line = layout.getLineForVertical(y);
                int off = layout.getOffsetForHorizontal(line, x);
                ClickableSpan[] link = spannable.getSpans(off, off, ClickableSpan.class);
                if (link.length != 0) {
                    if (action == MotionEvent.ACTION_UP) {
                        link[0].onClick(widget);
                    }
                    ret = true;
                }
            }
            return ret;
        }
    }

}
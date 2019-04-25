package com.mtx.androidcommonutil.ui;

import android.graphics.drawable.Animatable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.mtx.androidcommonutil.R;

/**
 * SVG：Scalable Vector Graphics ，可缩放的矢量图形。
 * 缩放不失真，文件相对较小，高交互性（用于地图，手动绘制UI等领域）
 * 更多SVG案例：https://mp.weixin.qq.com/s/MSL7h43mSA1UyiqcJpfi1Q
 * 手写掘金logo，世界地图和中国地图（SVG解析path路径）
 */
public class SVGActivity extends BaseActivity {
    ImageView mIvAnim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_svg);
        initView();
    }

    private void initView() {
        mIvAnim = (ImageView) findViewById(R.id.iv_anim);
        mIvAnim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) { // 5.0及以上版本，可执行svg动画切换
                ((Animatable) mIvAnim.getDrawable()).start();
//                } else {
//                    mIvAnim.setImageDrawable(
//                            ContextCompat.getDrawable(SVGActivity.this, R.drawable.ic_title_back_black_n));
//                }
            }
        });


    }

}

package com.mtx.androidcommonutil.util;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

import android.graphics.Bitmap;

import static android.graphics.Color.BLACK;

/**
 * 兼容了zxing包中的EncodingHandler，添加生成条形码方法
 * Created by lishaoming on 17-6-9.
 */

public class EncodingHandlerUtil {
    private static final int DEFAULT_BAR_CODE_WIDTH = 600;
    private static final int DEFAULT_BAR_CODE_HEIGHT = 200;

    private EncodingHandlerUtil() {
    }

    /**
     * 生成条形码
     */
    public static Bitmap createBarCode(String content, int width, int height) throws WriterException {
        // 生成一维条码,编码时指定大小,不要生成了图片以后再进行缩放,这样会模糊导致识别失败
        BitMatrix matrix = new MultiFormatWriter().encode(content, BarcodeFormat.CODE_128, width, height);
        int[] pixels = new int[width * height];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (matrix.get(x, y)) {
                    pixels[y * width + x] = BLACK;
                }
            }
        }
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        // 通过像素数组生成bitmap,具体参考api
        bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
        return bitmap;
    }

    public static Bitmap createBarCode(String content) throws WriterException {
        return createBarCode(content, DEFAULT_BAR_CODE_WIDTH, DEFAULT_BAR_CODE_HEIGHT);
    }

}

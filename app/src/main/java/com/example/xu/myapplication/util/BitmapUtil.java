package com.example.xu.myapplication.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by 逝 on 2017/10/08.
 */

public class BitmapUtil {
    /**
     * 质量压缩
     *
     * @param bm
     * @param size
     *            压缩到小于或者等于这个尺寸的图片
     * @return
     */
    public static Bitmap compressImage(Bitmap bm, int size) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.JPEG, 100, baos);// 质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
        int options = 50;
        while (baos.toByteArray().length / 1024 > size) { // 循环判断如果压缩后图片是否大于50kb,大于继续压缩
            baos.reset();// 重置baos即清空baos
            bm.compress(Bitmap.CompressFormat.JPEG, options, baos);// 这里压缩options%，把压缩后的数据存放到baos中

            options -= 10;// 每次都减少10
            if(options<0){
                break;
            }
        }
        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());// 把压缩后的数据baos存放到ByteArrayInputStream中
        Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);// 把ByteArrayInputStream数据生成图片
        return bitmap;
    }

    // 把Bitmap转换成Base64
    public static String getBitmapStrBase64(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] bytes = baos.toByteArray();
        return Base64.encodeToString(bytes, 0);
    }

    // 把Base64转换成Bitmap
    public static Bitmap getBitmapFromBase64(String iconBase64) {
        byte[] bitmapArray = Base64.decode(iconBase64, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(bitmapArray, 0, bitmapArray.length);
    }
}

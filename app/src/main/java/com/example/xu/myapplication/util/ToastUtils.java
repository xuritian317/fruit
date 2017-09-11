package com.example.xu.myapplication.util;

import android.content.Context;
import android.widget.Toast;

/**
 * @author WangYh
 * @version V1.0
 * @Name: ToastUtils
 * @Package com.looedu.android.mobileschool_tea.util
 * @Description: 吐司的工具类，防止多次点击一直弹出吐司
 * @date 2016/12/21 0021
 */

public class ToastUtils {

    private static Toast toast;

    /**
     * 静态toast
     * @param context
     * @param text
     */
    public static void showToast(Context context, String text) {
        if (toast == null) { // 1. 创建前 2.消失后toast为null
            toast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
        }
        toast.setText(text);
        toast.show();
    }
}
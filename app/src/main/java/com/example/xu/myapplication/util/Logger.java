package com.example.xu.myapplication.util;

import android.util.Log;

/**
 * Created by wangcheng on 2016-12-8.
 */

public class Logger {
    private static final boolean DEBUG = true;

    public static void e(String tag, String msg) {
        if (DEBUG)
            Log.e(tag, msg);
    }

    public static void i(String tag, String msg) {
        if (DEBUG)
            Log.i(tag, msg);
    }

    public static void d(String tag, String msg) {
        if (DEBUG)
            Log.d(tag, msg);
    }

    public static void w(String tag, String msg) {
        if (DEBUG)
            Log.w(tag, msg);
    }
}

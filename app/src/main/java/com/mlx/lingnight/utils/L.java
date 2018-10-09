package com.mlx.lingnight.utils;

import android.util.Log;

import com.mlx.lingnight.common.Debug;


/**
 * ============================
 * 作    者：mlx
 * 创建日期：2017/12/27.
 * 描    述：日志工具类
 * 修改历史：
 * ===========================
 */

public class L {
    private static final String TAG = "LingNightLog";

    public static void v(String msg) {
        v(TAG, msg);
    }

    public static void v(String tag, String msg) {
        if (Debug.DEBUG) Log.v(tag, msg);
    }

    public static void d(String msg) {
        d(TAG, msg);
    }

    public static void d(String tag, String msg) {
        if (Debug.DEBUG) Log.d(tag, msg);
    }

    public static void i(String msg) {
        i(TAG, msg);
    }

    public static void i(String tag, String msg) {
        if (Debug.DEBUG) Log.i(tag, msg);
    }

    public static void w(String msg) {
        w(TAG, msg);
    }

    public static void w(String tag, String msg) {
        if (Debug.DEBUG) Log.w(tag, msg);
    }

    public static void e(String msg) {
        e(TAG, msg);
    }

    public static void e(String tag, String msg) {
        if (Debug.DEBUG) Log.e(tag, msg);
    }

    public static void printStackTrace(Throwable t) {
        if (Debug.DEBUG && t != null) t.printStackTrace();
    }
}

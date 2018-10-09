package com.mlx.lingnight.http;


import android.util.Log;

/**
 * ============================
 * 作    者：mlx
 * 创建日期：2018/1/2.
 * 描    述：
 * 修改历史：
 * ===========================
 */

public class Logger implements LoggingInterceptor.Logger {
    @Override
    public void log(String message) {
        Log.i("Http Logger ", message);
    }
}

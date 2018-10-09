package com.mlx.lingnight.utils;

import android.util.DisplayMetrics;

import com.mlx.lingnight.app.MyApplication;


public class ScreenUtils {
    private static volatile ScreenUtils mScreenUtils;
    private int ScreenWidth;
    private int ScreenHeight;
    private float xdpi, ydpi;

    public ScreenUtils() {
    }

    public static ScreenUtils getInstance() {
        if (null == mScreenUtils) {
            synchronized (ScreenUtils.class) {
                if (null == mScreenUtils) {
                    mScreenUtils = new ScreenUtils();
                }
            }
        }
        return mScreenUtils;
    }

    public void init() {
        DisplayMetrics dm = MyApplication.getInstance().getResources().getDisplayMetrics();
        ScreenWidth = dm.widthPixels;
        ScreenHeight = dm.heightPixels;
        xdpi = dm.xdpi;
        ydpi = dm.ydpi;
    }

    public int getScreenWidth() {
        return ScreenWidth;
    }

    public int getSceenHeight() {
        return ScreenHeight;
    }

    public float getXdpi() {
        return xdpi;
    }

    public float getYdpi() {
        return ydpi;
    }
}

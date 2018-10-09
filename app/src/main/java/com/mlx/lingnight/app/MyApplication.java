package com.mlx.lingnight.app;

import android.app.Activity;
import android.os.Process;
import android.support.annotation.NonNull;
import android.support.multidex.MultiDexApplication;

import com.mlx.lingnight.di.component.AppComponent;
import com.mlx.lingnight.di.component.DaggerAppComponent;
import com.mlx.lingnight.di.module.AppModule;
import com.mlx.lingnight.di.module.NetModule;
import com.mlx.lingnight.utils.ScreenUtils;

import java.util.HashSet;
import java.util.Set;

public class MyApplication extends MultiDexApplication {

    private static MyApplication INSTANCE;      //Application
    private static int count = 0;
    private static boolean isOnForeground = false;
    private Set<Activity> activities; //Activity管理集合
    private AppComponent appComponent;


    public static MyApplication getInstance() {
        return INSTANCE;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        INSTANCE=this;

        initComponent();
        initScreenInfos();
    }

    private void initScreenInfos() {
        ScreenUtils.getInstance().init();
    }


    private void initComponent() {
        appComponent = DaggerAppComponent
                .builder()
                .netModule(new NetModule())
                .appModule(new AppModule(INSTANCE))
                .build();
    }


    /**
     * 管理Activity 添加
     *
     * @param act
     */
    public void addActivity(Activity act) {
        if (activities == null) {
            activities = new HashSet<>();
        }
        activities.add(act);
    }

    /**
     * 管理Activity 移除
     *
     * @param act
     */
    public void removeActivity(Activity act) {
        if (activities != null) {
            activities.remove(act);
        }
    }

    /**
     * 退出APP
     */
    public void removeAllApp() {
        if (activities != null) {
            synchronized (MyApplication.this) {
                for (Activity act : activities) {
                    act.finish();
                }
            }
        }
    }

    /**
     * 退出APP
     */
    public void exitApp() {
        if (activities != null) {
            synchronized (MyApplication.this) {
                for (Activity act : activities) {
                    act.finish();
                }
            }
        }
        Process.killProcess(Process.myPid());
        System.exit(0);
    }

    @NonNull
    public AppComponent getAppComponent() {
        if (appComponent == null) {
            appComponent = DaggerAppComponent
                    .builder()
                    .netModule(new NetModule())
                    .appModule(new AppModule(INSTANCE))
                    .build();
        }
        return appComponent;
    }
}


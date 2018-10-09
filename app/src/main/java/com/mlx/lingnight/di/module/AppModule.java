package com.mlx.lingnight.di.module;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * ============================
 * 作    者：mlx
 * 创建日期：2017/12/28.
 * 描    述：
 * 修改历史：
 * ===========================
 */

@Module
public class AppModule {

    private Application mApplication;

    public AppModule(Application application){
        this.mApplication = application;
    }

    @Provides
    @Singleton
    public Application getApplication(){return mApplication;}
}

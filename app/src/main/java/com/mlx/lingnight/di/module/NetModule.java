package com.mlx.lingnight.di.module;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mlx.lingnight.api.Api;
import com.mlx.lingnight.http.HeaderInterceptor;
import com.mlx.lingnight.http.Logger;
import com.mlx.lingnight.http.LoggingInterceptor;
import com.mlx.lingnight.manager.CookiesManager;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * ============================
 * 作    者：mlx
 * 创建日期：2017/12/29.
 * 描    述：网络以及第三方实例
 * 修改历史：
 * ===========================
 */

@Module
public class NetModule {

    private static final int TIME_OUT = 30_000;

//    @Provides
//    @Singleton
//    public IRepositoryManager provideRepositoryManager(RepositoryManager repositoryManager){
//        return repositoryManager;
//    }

    /**
     * 提供retrofit
     * @param builder
     * @param client
     * @param gson
     * @return
     */
    @Singleton
    @Provides
    Retrofit provideRetrofit(Retrofit.Builder builder, OkHttpClient client,Gson gson){
        builder.baseUrl(Api.BASE_URL)//域名
                .client(client)//配置okhttp
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson));
        return builder.build();
    }

    /**
     * 获取OkHttpClient
     * @param builder
     * @return
     */
    @Singleton
    @Provides
    OkHttpClient provideOkHttpClient(OkHttpClient.Builder builder){
        LoggingInterceptor logging = new LoggingInterceptor(new Logger());
        logging.setLevel(LoggingInterceptor.Level.BODY);

        builder.connectTimeout(TIME_OUT, TimeUnit.MILLISECONDS)
                .readTimeout(TIME_OUT, TimeUnit.MILLISECONDS)
                .retryOnConnectionFailure(true)
                .cookieJar(new CookiesManager())
                .addInterceptor(new HeaderInterceptor())
                .addInterceptor(logging);
        return builder.build();
    }

    @Singleton
    @Provides
    Api provideApi(Retrofit retrofit,Gson gson,OkHttpClient okHttpClient){
        return new Api(retrofit,gson,okHttpClient);
    }

    @Singleton
    @Provides
    Retrofit.Builder provideRetrofitBuilder(){
        return new Retrofit.Builder();
    }

    @Singleton
    @Provides
    OkHttpClient.Builder provideOkHttpBuilder(){
        return new OkHttpClient.Builder();
    }

    @Provides
    @Singleton
    Gson provideGson(){
        GsonBuilder builder = new GsonBuilder();
        builder.setFieldNamingPolicy(FieldNamingPolicy.IDENTITY);
        return builder.create();
    }
}

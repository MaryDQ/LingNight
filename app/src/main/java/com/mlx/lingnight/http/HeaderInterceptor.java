package com.mlx.lingnight.http;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * ============================
 * 作    者：mlx
 * 创建日期：2018/1/2.
 * 描    述：统一Head
 * 修改历史：
 * ===========================
 */

public class HeaderInterceptor implements Interceptor{

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();
        String url = original.url().toString();
        if(url.contains("bussiness/doBusiness.do")){
//            UserEntity entity = SharedPreferencesUtil.getInstance().getObject(Params.USER_INFO,UserEntity.class);
            Request request = original.newBuilder()
                    .addHeader("loginName", "Admin")
                    .addHeader("type","1")
                    .addHeader("key","theKey")

                    .build();
            return chain.proceed(request);
        }
        return chain.proceed(original);
    }
}

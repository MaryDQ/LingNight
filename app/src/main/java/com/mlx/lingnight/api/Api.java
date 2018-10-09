package com.mlx.lingnight.api;

import com.google.gson.Gson;
import com.mlx.lingnight.http.BaseResponse;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

public class Api {
    public static final String BASE_URL="";

    private ApiService apiService;
    private Gson gson;
    private OkHttpClient okHttpClient;

    public Gson getGson(){
        return gson;
    }

    public OkHttpClient getOkHttpClient(){
        return okHttpClient;
    }

    public Api(Retrofit retrofit, Gson gson, OkHttpClient okHttpClient){
        this.gson = gson;
        this.okHttpClient = okHttpClient;
        apiService = retrofit.create(ApiService.class);
    }


    public Observable<BaseResponse> doBusiness(Map map){
//        return apiService.doBusiness(composeJson(map));
        return null;
    }


    private String composeJson(Map map){
        String jsondata = gson.toJson(map);
        return jsondata;
    }
}

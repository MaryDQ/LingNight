package com.mlx.lingnight.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;

/**
 * ============================
 * 作    者：mlx
 * 创建日期：2018/1/12.
 * 描    述：Cookies管理
 * 修改历史：
 * ===========================
 */

public class CookiesManager implements CookieJar{

    private Map<String,List<Cookie>> cookieStore = new HashMap<>();

    @Override
    public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
        cookieStore.put(url.host(),cookies);
//        SharedPreferencesUtil.getInstance().setDataList(Params.COOKIES,cookies);
    }

    @Override
    public List<Cookie> loadForRequest(HttpUrl url) {
        List<Cookie> cookies = cookieStore.get(url.host());
        return cookies != null ? cookies : new ArrayList<Cookie>();
    }
}

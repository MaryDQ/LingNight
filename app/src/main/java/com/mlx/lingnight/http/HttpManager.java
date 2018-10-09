package com.mlx.lingnight.http;

/**
 * ============================
 * 作    者：mlx
 * 创建日期：2017/12/27.
 * 描    述：请求管理类(未用)
 * 修改历史：
 * ===========================
 */

public class HttpManager {

    private volatile static HttpManager INSTANCE;

    private HttpManager(){}

    /**
     * 单例
     * @return
     */
    public static HttpManager getInstance(){
        if(INSTANCE == null){
            synchronized (HttpManager.class){
                if(INSTANCE == null){
                    INSTANCE = new HttpManager();
                }
            }
        }
        return INSTANCE;
    }


}

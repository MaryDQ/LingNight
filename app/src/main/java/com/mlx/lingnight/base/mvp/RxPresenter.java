package com.mlx.lingnight.base.mvp;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * ============================
 * 作    者：mlx
 * 创建日期：2017/12/29.
 * 描    述：基于Rx的presenter封装，控制订阅生命周期
 * 修改历史：
 * ===========================
 */

public class RxPresenter<T extends IView> implements IPresenter<T> {

    protected T view;
    protected CompositeDisposable compositeDisposable;

    protected void unSubscribe(){
        if(compositeDisposable != null){
            compositeDisposable.clear();
        }
    }

    protected void addSubcribe(Disposable disposable){
        if(compositeDisposable == null){
            compositeDisposable = new CompositeDisposable();
        }
        compositeDisposable.add(disposable);
    }

    @Override
    public void attachView(T view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        this.view = null;
        unSubscribe();
    }
}

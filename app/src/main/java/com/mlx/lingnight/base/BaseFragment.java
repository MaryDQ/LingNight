package com.mlx.lingnight.base;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mlx.lingnight.app.MyApplication;
import com.mlx.lingnight.R;
import com.mlx.lingnight.base.mvp.IPresenter;
import com.mlx.lingnight.di.component.AppComponent;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * ============================
 * 作    者：mlx
 * 创建日期：2017/12/27.
 * 描    述：
 * 修改历史：
 * ===========================
 */

public abstract class BaseFragment<P extends IPresenter> extends Fragment {

    private Unbinder unbinder;
    private View rootView;
    protected Context mContext;

    @Inject
    protected P presenter;

    private ProgressDialog dialog;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(getRootViewId(),container,false);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbinder = ButterKnife.bind(this,view);
        mContext = getActivity();
        setupActivityComponent(MyApplication.getInstance().getAppComponent());
        if(presenter != null) {
            presenter.attachView(this);
        }
        initViewAndData();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if(unbinder != null) {
            unbinder.unbind();
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if(presenter != null){
            presenter.detachView();
        }
        this.presenter = null;
    }

    protected String getResString(@StringRes int id){
        return mContext.getResources().getString(id);
    }

    protected void showLoading(){
        if(dialog == null) {
            dialog = new ProgressDialog(mContext);
            dialog.setMessage(getResString(R.string.loading));
            dialog.setCancelable(false);
        }
        dialog.show();
    }

    protected void showLoading(String msg){
        if(dialog == null) {
            dialog = new ProgressDialog(mContext);
            dialog.setMessage(msg);
            dialog.setCancelable(false);
        }
        dialog.show();
    }

    protected void dismissLoading(){
        if(dialog != null&&dialog.isShowing()){
            dialog.dismiss();
        }
    }

    /**
     * 关闭Activity
     */
    public void finish(){
        getActivity().finish();
    }

    /**
     * 获取Application
     * @return
     */
    public MyApplication getApp(){
        return (MyApplication) getActivity().getApplication();
    }

    /**
     * 获取根ID
     * @return
     */
    public abstract @LayoutRes
    int getRootViewId();

    protected abstract void setupActivityComponent(AppComponent appComponent);

    /**
     * 初始化View和Data
     * @return
     */
    public abstract void initViewAndData();
}

package com.mlx.lingnight.base;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseArray;

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
 * 描    述：没有标题的基类
 * 修改历史：
 * ===========================
 */

public abstract class BaseNoTitleActivity<P extends IPresenter> extends AppCompatActivity {

    protected final String TAG = this.getClass().getSimpleName();
    protected Context mContext;
    @Inject
    protected P presenter;
    //    private Map<Integer, RequestPermissionCallback> requestPermissionCallbackMap = new HashMap<>();
    private SparseArray<RequestPermissionCallback> requestPermissionCallbackMap = new SparseArray<>();
    private Unbinder unbinder;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            int layoutResID = getRootViewId();
            if (layoutResID != 0) {
                setContentView(layoutResID);
                //绑定butterKnife
                unbinder = ButterKnife.bind(this);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        setupActivityComponent(MyApplication.getInstance().getAppComponent());
        mContext = this;
        if (presenter != null) {
            presenter.attachView(this);
        }
        MyApplication.getInstance().addActivity(this);
        initViewAndData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (unbinder != null && unbinder != Unbinder.EMPTY) {
            unbinder.unbind();
        }
        this.unbinder = null;
        if (presenter != null) {
            presenter.detachView();
        }
        MyApplication.getInstance().removeActivity(this);
        this.presenter = null;
    }

    /**
     * 获取根ID
     */
    public abstract @LayoutRes
    int getRootViewId();

    protected abstract void setupActivityComponent(AppComponent appComponent);

    /**
     * 初始化View和Data
     */
    public abstract void initViewAndData();

    protected void requestPermission(String permission, int requestCode, RequestPermissionCallback requestPermissionCallback) {
        if (requestPermissionCallback == null) {
            throw new IllegalArgumentException("requestPermissionCallback == null");
        }
        requestPermissionCallbackMap.put(requestCode, requestPermissionCallback);
        if (Build.VERSION.SDK_INT >= 23) {
            int readPhoneState = ContextCompat.checkSelfPermission(this, permission);
            if (readPhoneState != PackageManager.PERMISSION_GRANTED) {
                this.requestPermissions(new String[]{permission}, requestCode);
            } else {
                requestPermissionCallback.GrantedPermission();
            }
        } else {
            requestPermissionCallback.GrantedPermission();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            RequestPermissionCallback callback = requestPermissionCallbackMap.get(requestCode);
            callback.GrantedPermission();
        } else {
            RequestPermissionCallback disCallback = requestPermissionCallbackMap.get(requestCode);
            disCallback.dentedPermission();
        }
    }

    protected void showLoading() {
        if (dialog == null) {
            dialog = new ProgressDialog(mContext);
            dialog.setMessage(getResString(R.string.loading));
            dialog.setCancelable(false);
        }
        dialog.show();
    }

    protected String getResString(@StringRes int id) {
        return mContext.getResources().getString(id);
    }

    protected void showLoading(String msg) {
        if (dialog == null) {
            dialog = new ProgressDialog(mContext);
            dialog.setMessage(msg);
            dialog.setCancelable(false);
        }
        dialog.show();
    }

    protected void dismissLoading() {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }

    /**
     * 获取Application
     *
     * @return getApplication()
     */
    public MyApplication getApp() {
        return (MyApplication) getApplication();
    }
}

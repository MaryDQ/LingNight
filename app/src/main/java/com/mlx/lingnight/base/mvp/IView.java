package com.mlx.lingnight.base.mvp;

/**
 * ============================
 * 作    者：mlx
 * 创建日期：2017/12/27.
 * 描    述：mvp的V基类接口
 * 修改历史：
 * ===========================
 */

public interface IView {
    void showError(String msg);
    void complete();
}

package com.mlx.lingnight.base;

/**
 * ============================
 * 作    者：mlx
 * 创建日期：2018/1/3.
 * 描    述：权限申请回调
 * 修改历史：
 * ===========================
 */

public interface RequestPermissionCallback {
    void GrantedPermission();
    void dentedPermission();

    //空实现
    RequestPermissionCallback EMPTY = new RequestPermissionCallback() {
        @Override
        public void GrantedPermission() {
            //不做事
        }

        @Override
        public void dentedPermission() {
            //不做事
        }
    };
}

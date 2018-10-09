package com.mlx.lingnight.di.component;


import com.mlx.lingnight.di.ActivityScope;

import dagger.Component;

/**
 * ============================
 * 作    者：mlx
 * 创建日期：2018/1/16.
 * 描    述：通用Activity
 * 修改历史：
 * ===========================
 */
@ActivityScope
@Component(dependencies = AppComponent.class)
public interface CommonActivityComponent {

}

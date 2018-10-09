package com.mlx.lingnight.di.component;


import com.mlx.lingnight.di.FragmentScope;

import dagger.Component;

/**
 * ============================
 * 作    者：mlx
 * 创建日期：2018/1/22.
 * 描    述：通用fragment
 * 修改历史：
 * ===========================
 */

@FragmentScope
@Component(dependencies = AppComponent.class)
public interface CommonFragmentComponent {
}

package com.mlx.lingnight.di;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * ============================
 * 作    者：mlx
 * 创建日期：2017/12/28.
 * 描    述：
 * 修改历史：
 * ===========================
 */

@Scope
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface ActivityScope {
}

package com.wondeful.wanandroid.app.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity

/**
 * BaseActivity
 * @Description
 * @Author defeng.wang@ubtrobot.com
 * @Time 2021/1/25 17:35
 */
abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        initView()
    }

    // get layout id
    @LayoutRes
    abstract fun getLayoutId(): Int

    // init view
    abstract fun initView()
}
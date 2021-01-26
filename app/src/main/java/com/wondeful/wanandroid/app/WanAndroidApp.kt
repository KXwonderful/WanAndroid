package com.wondeful.wanandroid.app

import android.app.Application

/**
 * WanAndroidApp
 * @Description
 * @Author defeng.wang@ubtrobot.com
 * @Time 2021/1/25 17:30
 */
class WanAndroidApp : Application() {

    override fun onCreate() {
        super.onCreate()
        context = this
    }

    companion object {
        @JvmStatic
        lateinit var context: WanAndroidApp
            private set
    }
}
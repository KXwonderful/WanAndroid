package com.wondeful.wanandroid.extension

import android.content.Context
import android.widget.Toast
import com.wondeful.wanandroid.app.WanAndroidApp

/**
 * Toast 工具类
 * @Description
 * @Author defeng.wang@ubtrobot.com
 * @Time 2020/9/4 10:29
 */
fun String.showToast(
    context: Context = WanAndroidApp.context,
    duration: Int = Toast.LENGTH_SHORT
) {
    Toast.makeText(context, this, duration).show()
}

fun Int.showToast(context: Context = WanAndroidApp.context, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(context, this, duration).show()
}

fun showToast(content: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(WanAndroidApp.context, content, duration).show()
}

fun showToast(resId: Int, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(WanAndroidApp.context, resId, duration).show()
}
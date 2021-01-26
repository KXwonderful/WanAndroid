package com.wondeful.wanandroid.extension

import com.wondeful.wanandroid.app.WanAndroidApp

/**
 * Number extension methods.
 * @Description
 * @Author defeng.wang@ubtrobot.com
 * @Time 2021/1/6 16:24
 */

/**
 * convert dp to px
 */
val Int.dp: Int
    get() {
        val scale = WanAndroidApp.context.resources.displayMetrics.density
        return (this * scale + 0.5).toInt()
    }

val Float.dp: Float
    get() {
        val scale = WanAndroidApp.context.resources.displayMetrics.density
        return (this * scale + 0.5).toFloat()
    }

val Double.dp: Double
    get() {
        val scale = WanAndroidApp.context.resources.displayMetrics.density
        return (this * scale + 0.5)
    }

/**
 * Convert a number to a numeric string.
 * e.g. 12365 wil be converted into 12,365
 */
fun Int.toNumericString(): String {
    val chars = toString().toCharArray()
    chars.reverse()
    val builder = StringBuilder()
    chars.forEachIndexed { index, c ->
        if (index != 0 && index % 3 == 0 && c != '-') {
            builder.append(",")
        }
        builder.append(c)
    }
    return builder.reverse().toString()
}

/**
 * Convert a number to Three numbers string
 * e.g. 1 will be converted into 001
 */
fun Int.toThreeNumString(): String {
    if (this >= 100) {
        return this.toString()
    }
    if (this > 10) {
        return "0$this"
    }
    return "00$this"
}
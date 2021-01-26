package com.wondeful.wanandroid.util

import android.util.Log

/**
 * log util
 * @Description
 * @Author defeng.wang@ubtrobot.com
 * @Time 2020/11/26 13:37
 */
object LogUtils {

    private const val TAG = "WanAndroid ---->"

    private const val MAX_LENGTH = 4000

    private var logFlag = true

    @JvmStatic
    fun e(message: String) {
        Log.e(TAG, message)
    }

    @JvmStatic
    fun e(tag: String, message: String) {
        Log.e(TAG, "$tag -> $message")
    }

    @JvmStatic
    fun i(str: String) {
        if (logFlag) {
            val name = getFunctionName()
            if (name != null) {
                printLog(Log.INFO, "$name - $str")
            } else {
                printLog(Log.INFO, str)
            }
        }
    }

    @JvmStatic
    fun i(tag: String, str: String) {
        if (logFlag) {
            val name = getFunctionName()
            if (name != null) {
                printLog(Log.ERROR, "$name - $tag - $str")
            } else {
                printLog(Log.ERROR, "$tag - $str")
            }
        }
    }

    /**
     * The Log Level:e
     *
     * @param str
     */
    @JvmStatic
    fun w(str: String) {
        if (logFlag) {
            val name = getFunctionName()
            if (name != null) {
                printLog(Log.ERROR, "$name - $str")
            } else {
                printLog(Log.ERROR, str)
            }
        }
    }

    @JvmStatic
    fun w(tag: String, str: String) {
        if (logFlag) {
            val name = getFunctionName()
            if (name != null) {
                printLog(Log.ERROR, "$name - $tag - $str")
            } else {
                printLog(Log.ERROR, "$tag - $str")
            }
        }
    }

    /**
     * Get The Current Function Name
     *
     * @return
     */
    private fun getFunctionName(): String? {
        val sts = Thread.currentThread().stackTrace
        for (st in sts) {
            if (st.isNativeMethod) {
                continue
            }
            if (st.className == Thread::class.java.name) {
                continue
            }
            if (st.className == this.javaClass.name) {
                continue
            }
            return "@Wonderful@[ ${Thread.currentThread().name} ${st.methodName} (${st.fileName}:${st.lineNumber})]"
        }
        return null
    }

    /**
     * 因为logcat输出log的字符长度是4k，超过长度字符串会被丢弃，所以对超过4k长度的log做分段输出.
     *
     * @param level
     * @param mLogText
     */
    private fun printLog(level: Int, mLogText: String) {
        var logText = mLogText
        var index = 0
        var sub: String
        logText = logText.trim()
        while (index < logText.length) {
            // java的字符不允许指定超过总的长度
            sub = if (logText.length <= index + MAX_LENGTH) {
                logText.substring(index)
            } else {
                logText.substring(index, index + MAX_LENGTH)
            }
            index += MAX_LENGTH
            when (level) {
                Log.INFO -> Log.i(TAG, sub.trim())
                Log.DEBUG -> Log.d(TAG, sub.trim())
                Log.WARN -> Log.w(TAG, sub.trim())
                Log.VERBOSE -> Log.v(TAG, sub.trim())
                else -> Log.e(TAG, sub.trim())
            }
        }
    }
}
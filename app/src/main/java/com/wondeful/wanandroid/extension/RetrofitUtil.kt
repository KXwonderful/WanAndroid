package com.wondeful.wanandroid.extension

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

/**
 * Retrofit extension methods
 * @Description
 * @Author defeng.wang@ubtrobot.com
 * @Time 2021/1/6 20:08
 */

/**
 * 这里 await() 定义成一个挂起函数，并定义成 Call<T> 的扩展函数，
 * 从而所有返回值是 Call 类型的 Retrofit 网络请求接口都可以直接调用 await() 函数
 */
suspend fun <T> Call<T>.await(): T {
    return suspendCoroutine { continuation ->
        // 由于扩展函数的原因，这里拥有了 Call 对象的上下文，
        // 从而可以直接调用 enqueue() 方法让 Retrofit 发起网络请求
        enqueue(object : Callback<T> {
            override fun onResponse(call: Call<T>, response: Response<T>) {
                val body = response.body()
                if (body != null) continuation.resume(body)
                else continuation.resumeWithException(RuntimeException("response body is null"))
            }

            override fun onFailure(call: Call<T>, t: Throwable) {
                continuation.resumeWithException(t)
            }
        })
    }
}

/**
 * 构建协程域并简化请求回调
 */
fun launch(block: suspend () -> Unit, error: suspend (Throwable) -> Unit) =
    GlobalScope.launch {
        try {
            block()
        } catch (e: Throwable) {
            e.printStackTrace()
            error(e)
        }
    }
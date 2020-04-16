package com.example.android_mvvm_kotlin.utils

import okhttp3.Interceptor
import okhttp3.Response
import org.greenrobot.eventbus.EventBus
import java.io.IOException

class UnauthorizedInterceptor : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val response = chain.proceed(chain.request())
        if (response.code() == 401) {
            EventBus.getDefault().post(UnauthorizedEvent.instance(response.body()))
        }
        return response
    }
}
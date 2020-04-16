package com.example.android_mvvm_kotlin.helpers

import android.content.Context
import android.net.ConnectivityManager
import okhttp3.Interceptor
import okhttp3.Response
import org.greenrobot.eventbus.EventBus
import java.io.IOException

class ConnectivityInterceptor(val context: Context) : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        return if (!isNetworkAvailable) {
            EventBus.getDefault().post(NoConnectivityEvent.instance)
            throw NoConnectivityException(context)
        } else {
            chain.proceed(chain.request())
        }
    }

    private val isNetworkAvailable: Boolean
        private get() {
            val cm =
                context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetwork = cm.activeNetworkInfo
            return activeNetwork != null && activeNetwork.isConnectedOrConnecting
        }

}

package com.example.android_mvvm_kotlin.utils

import com.example.android_mvvm_kotlin.helpers.NoConnectivityEvent
import okhttp3.ResponseBody

class UnauthorizedEvent {
    companion object {
        fun instance(error: ResponseBody?): UnauthorizedEvent {
            return UnauthorizedEvent()
        }
    }
}

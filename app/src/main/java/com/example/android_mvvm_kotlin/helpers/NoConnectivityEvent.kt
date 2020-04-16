package com.example.android_mvvm_kotlin.helpers

class NoConnectivityEvent {
    companion object {
        private var singleTonInstance: NoConnectivityEvent? = null
        val instance: NoConnectivityEvent?
            get() {
                if (singleTonInstance == null) {
                    singleTonInstance =
                        NoConnectivityEvent()
                }
                return singleTonInstance
            }
    }
}
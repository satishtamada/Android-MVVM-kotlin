package com.example.android_mvvm_kotlin.helpers

import android.content.Context
import com.example.android_mvvm_kotlin.R
import java.io.IOException

class NoConnectivityException(private val context: Context) :
    IOException() {
    override val message: String
        get() = context.getString(R.string.error_no_internet_connection)

}
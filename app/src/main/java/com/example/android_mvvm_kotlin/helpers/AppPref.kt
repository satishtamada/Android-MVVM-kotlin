package com.example.android_mvvm_kotlin.helpers

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson

class AppPref(private val context: Context) {

    fun saveAuthToken(authToken: String?) {
        editor.putString(KEY_AUTH_TOKEN, authToken)
        editor.commit()
    }

    val authToken: String get() = sharedPreferences.getString(KEY_AUTH_TOKEN, null)

    fun clearSession() {
        editor.clear()
        editor.commit()
    }

    fun saveUserData(userModel: String?) {
        editor.putString(KEY_USER, userModel)
        editor.commit()
    }

    val userData: String
        get() = sharedPreferences.getString(KEY_USER, null)

    companion object {
        private var singleTonInstance: AppPref? = null
        private lateinit var sharedPreferences: SharedPreferences
        private lateinit var editor: SharedPreferences.Editor
        private lateinit var gson: Gson
        const val PREF_NAME = "app_prefs"
        private const val PRIVATE_MODE = 0
        private const val KEY_AUTH_TOKEN = "auth_token"
        private const val KEY_USER = "auth_token"
        val instance: AppPref?
            get() {
                if (singleTonInstance == null) {
                    singleTonInstance =
                        AppPref(MyApplication.instance!!.getApplicationContext())
                }
                return singleTonInstance
            }
    }

    init {
        gson = Gson()
        sharedPreferences = context.getSharedPreferences(
            PREF_NAME,
            PRIVATE_MODE
        )
        editor = sharedPreferences.edit()
    }
}

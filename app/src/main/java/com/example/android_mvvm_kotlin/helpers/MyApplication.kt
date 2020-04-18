package com.example.android_mvvm_kotlin.helpers

import android.app.Activity
import android.app.Application
import android.app.Service
import androidx.fragment.app.Fragment
import com.example.android_mvvm_kotlin.di.component.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import dagger.android.HasServiceInjector
import dagger.android.support.DaggerApplication
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject


class MyApplication : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().create(this)
    }
}

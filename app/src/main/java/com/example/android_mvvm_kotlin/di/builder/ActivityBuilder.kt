package com.example.android_mvvm_kotlin.di.builder

import com.example.android_mvvm_kotlin.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector
    abstract fun bindMainctivity(): MainActivity
}
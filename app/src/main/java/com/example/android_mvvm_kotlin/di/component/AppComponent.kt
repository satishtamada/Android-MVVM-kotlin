package com.example.android_mvvm_kotlin.di.component

import android.app.Application
import com.example.android_mvvm_kotlin.di.AppModule
import com.example.android_mvvm_kotlin.di.builder.ActivityBuilder
import com.example.android_mvvm_kotlin.helpers.MyApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AndroidSupportInjectionModule::class, AppModule::class, ActivityBuilder::class))

interface AppComponent {

    fun inject(app: MyApplication?)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application?): Builder?

        fun build(): AppComponent?
    }
}
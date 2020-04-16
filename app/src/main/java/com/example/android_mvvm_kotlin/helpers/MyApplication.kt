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
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject


class MyApplication : Application(), HasActivityInjector, HasServiceInjector,
    HasSupportFragmentInjector {

    @Inject
    lateinit var activityDispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    @Inject
    lateinit var dispatchingServiceInjector: DispatchingAndroidInjector<Service>

    @Inject
    lateinit var mFragmentInjector: DispatchingAndroidInjector<Fragment>

    override fun onCreate() {
        super.onCreate()
        instance = this
        DaggerAppComponent.builder()
            .application(this)
            ?.build()
            ?.inject(this)
        // plantTimber()
        //initRealm()
    }


    /* private fun initRealm() {
         Realm.init(this)
         val config: RealmConfiguration = Builder()
             .name("mvvmapp.realm")
             .build()
         Realm.setDefaultConfiguration(config)
     }

     private fun plantTimber() {
         if (BuildConfig.DEBUG) {
             Timber.plant(DebugTree())
         }
     }*/

    override fun activityInjector(): AndroidInjector<Activity> {
        return activityDispatchingAndroidInjector!!
    }

    override fun serviceInjector(): AndroidInjector<Service> {
        return dispatchingServiceInjector!!
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> {
        return mFragmentInjector!!
    }

    companion object {
        var instance: MyApplication? = null
            private set
    }
}

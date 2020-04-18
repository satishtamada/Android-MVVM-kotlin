package com.example.android_mvvm_kotlin.data

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.example.android_mvvm_kotlin.helpers.AppPref
import com.example.android_mvvm_kotlin.network.WebService
import com.google.gson.Gson
import io.reactivex.Observer
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.schedulers.Schedulers.io
import javax.inject.Inject

class  DataRepository @Inject constructor(private var appPref: AppPref,private var webService: WebService,private var context: Context) {
    private var disposables = CompositeDisposable()
    fun getData(authtoken:String) {
       Log.e("here","DataRepo")
        Toast.makeText(context,appPref.authToken,Toast.LENGTH_LONG).show()
        webService.getData().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(object :
            Observer<List<String>> {
            override fun onNext(t: List<String>) {

            }

            override fun onSubscribe(d: Disposable) {
                disposables.add(d)
            }

            override fun onError(e: Throwable) {
                Log.d("", "")
            }

            override fun onComplete() {
            }
        })
     }
}
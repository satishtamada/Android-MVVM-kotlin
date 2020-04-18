package com.example.android_mvvm_kotlin.network

import io.reactivex.Observable
import retrofit2.http.GET

interface WebService {
    @GET("get_feed/lgBrscTflwtpbWWRuutzO8wvB9lxXiQ8/0/1/Igf4TYgzblBFPR4mNmVKS1j5wOyNoe6A")
    fun getData(): Observable<ArrayList<String>>
}
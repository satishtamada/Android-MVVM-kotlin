package com.example.android_mvvm_kotlin.viewModels

import androidx.lifecycle.ViewModel
import com.example.android_mvvm_kotlin.data.DataRepository
import javax.inject.Inject

class  MainViewModel @Inject internal constructor(private val repository: DataRepository): ViewModel(){
    fun getData(authToken: String) {
      repository.getData(authToken)
    }

}
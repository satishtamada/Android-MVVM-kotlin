package com.example.android_mvvm_kotlin

import android.os.Bundle
import android.util.Log
import com.example.android_mvvm_kotlin.base.BaseActivity
import com.example.android_mvvm_kotlin.databinding.ActivityMainBinding
import com.example.android_mvvm_kotlin.helpers.AppPref
import com.example.android_mvvm_kotlin.viewModels.MainViewModel
import javax.inject.Inject
import kotlinx.android.synthetic.main.activity_main.*;

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {

    @Inject
    lateinit var pref: AppPref
    lateinit var binding: ActivityMainBinding
    var value: Int = 0

    override val viewModel: Class<MainViewModel>
        get() = MainViewModel::class.java

    override val layoutResId: Int
        get() = R.layout.activity_main

    override fun onCreate(
        instance: Bundle?,
        binding: ActivityMainBinding,
        viewModel: MainViewModel
    ) {
        this.binding = binding
        pref.saveAuthToken("vvvvvvvvvv")
        btnClick.setOnClickListener() {
            Log.e("here", "clicked")
            binding.idText.text = "here" + value
            value++
            viewModel.getData(pref.authToken)

        }
    }
}

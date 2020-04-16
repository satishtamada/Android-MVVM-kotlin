package com.example.android_mvvm_kotlin.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import dagger.android.AndroidInjection
import javax.inject.Inject

abstract class BaseActivity <B : ViewDataBinding , V: ViewModel> : AppCompatActivity(){

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    abstract val viewModel: Class<V>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        performDependencyInjection()
        val binding = DataBindingUtil.setContentView<ViewDataBinding>(this, layoutResId)
        val viewModel = ViewModelProviders.of(this, viewModelFactory!!).get(viewModel);
        onCreate(savedInstanceState, binding as B, viewModel as V)
    }


    @get:LayoutRes
    abstract val layoutResId: Int

    private fun performDependencyInjection() {
        AndroidInjection.inject(this)
    }

    protected abstract fun onCreate(instance: Bundle?, binding: B ,viewModel: V)

}
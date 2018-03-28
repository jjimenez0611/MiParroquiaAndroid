package com.effeta.miparroquiaandroid.common

import android.arch.lifecycle.ViewModelProvider
import android.os.Bundle
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

/**
 * Created by aulate on 5/1/18.
 */
abstract class BaseActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mLayout)
        initialize()
    }

    abstract val mLayout: Int

    private fun initialize() {
        initializeViewModels()
        initializeUI()
        observeLiveData()
    }

    abstract fun initializeViewModels()
    abstract fun initializeUI()
    abstract fun observeLiveData()
}
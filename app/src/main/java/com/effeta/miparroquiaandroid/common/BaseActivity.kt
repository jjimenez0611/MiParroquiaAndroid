package com.effeta.miparroquiaandroid.common

import android.arch.lifecycle.LifecycleOwner
import android.os.Bundle
import dagger.android.support.DaggerAppCompatActivity

/**
 * Created by aulate on 5/1/18.
 */
abstract class BaseActivity : DaggerAppCompatActivity(), LifecycleOwner {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mLayout)
        initialize(savedInstanceState == null)
    }

    abstract val mLayout: Int

    private fun initialize(isNewActivity: Boolean) {
        initializeViewModels()
        initializeUI()
        observeLiveData(isNewActivity)
        fetchData()
    }

    abstract fun initializeViewModels()
    abstract fun initializeUI()
    abstract fun observeLiveData(isNewActivity: Boolean)
    abstract fun fetchData()
}
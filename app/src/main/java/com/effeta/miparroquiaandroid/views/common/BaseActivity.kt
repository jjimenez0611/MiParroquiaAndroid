package com.effeta.miparroquiaandroid.views.common

import android.arch.lifecycle.LifecycleOwner
import android.os.Bundle
import android.support.v7.app.AppCompatActivity

/**
 * Created by aulate on 5/1/18.
 */
abstract class BaseActivity : AppCompatActivity(), LifecycleOwner {

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
    }

    abstract fun initializeViewModels()
    abstract fun initializeUI()
    abstract fun observeLiveData(isNewActivity: Boolean)
}
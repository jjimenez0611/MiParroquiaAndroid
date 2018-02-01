package com.effeta.miparroquiaandroid.views.common

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

/**
 * Created by aulate on 5/1/18.
 */
abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayout())
        init()
    }

    abstract fun getLayout(): Int

    fun init() {
        initViewModel()
        initUI()
        initObservers()
    }

    abstract fun initViewModel()
    abstract fun initObservers()
    abstract fun initUI()
}
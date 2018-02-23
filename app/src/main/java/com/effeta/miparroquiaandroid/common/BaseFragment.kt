package com.effeta.miparroquiaandroid.common

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dagger.android.support.DaggerFragment

/**
 * Created by aulate on 1/2/18.
 */
abstract class BaseFragment : DaggerFragment
() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initialize(savedInstanceState == null)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val v = inflater!!.inflate(mLayout, container, false)
        fetchData()
        return v
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
    abstract fun fetchData()

}
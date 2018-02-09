package com.effeta.miparroquiaandroid.views.common

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Created by aulate on 1/2/18.
 */
abstract class BaseFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val v = inflater!!.inflate(mLayout, container, false)
        initialize(savedInstanceState == null)
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

}
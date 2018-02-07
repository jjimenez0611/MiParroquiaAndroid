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
        val v = inflater!!.inflate(getLayout(), container, false)
        initialize(savedInstanceState == null)
        return v
    }

    abstract fun getLayout(): Int

    private fun initialize(isNewActivity: Boolean) {
        createViewModel()
        initializeUI()
        observeLiveData(isNewActivity)
    }

    abstract fun createViewModel()
    abstract fun initializeUI()
    abstract fun observeLiveData(isNewActivity: Boolean)

}
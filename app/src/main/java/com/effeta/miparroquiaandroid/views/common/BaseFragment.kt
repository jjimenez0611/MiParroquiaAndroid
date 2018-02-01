package com.effeta.miparroquiaandroid.views.common

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.effeta.miparroquiaandroid.R

/**
 * Created by aulate on 1/2/18.
 */
abstract class BaseFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val v = inflater!!.inflate(getLayout(), container, false)
        init()
        return v
    }

    abstract fun getLayout(): Int

    fun init() {
        initViewModel()
        initUI()
        initObservers()
    }

    abstract fun initViewModel()
    abstract fun initUI()
    abstract fun initObservers()

}
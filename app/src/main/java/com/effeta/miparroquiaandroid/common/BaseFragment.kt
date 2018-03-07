package com.effeta.miparroquiaandroid.common

import android.arch.lifecycle.Lifecycle.Event
import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.LifecycleRegistry
import android.os.Bundle
import android.support.annotation.Nullable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dagger.android.support.DaggerFragment


/**
 * Created by aulate on 1/2/18.
 */
abstract class BaseFragment : DaggerFragment() {

    internal class ViewLifecycleOwner : LifecycleOwner {
        private val lifecycleRegistry = LifecycleRegistry(this)

        override fun getLifecycle(): LifecycleRegistry {
            return lifecycleRegistry
        }
    }

    @Nullable
    private var mLifeCycleOwner: ViewLifecycleOwner? = null

    /**
     * @return the Lifecycle owner of the current view hierarchy,
     * or null if there is no current view hierarchy.
     */
    fun getViewLifecycleOwner(): LifecycleOwner? {
        return mLifeCycleOwner
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mLifeCycleOwner = ViewLifecycleOwner()
        mLifeCycleOwner!!.lifecycle.handleLifecycleEvent(Event.ON_CREATE)
    }

    override fun onStart() {
        super.onStart()
        mLifeCycleOwner?.lifecycle?.handleLifecycleEvent(Event.ON_START)
    }

    override fun onResume() {
        super.onResume()
        mLifeCycleOwner?.lifecycle?.handleLifecycleEvent(Event.ON_RESUME)
    }

    override fun onPause() {
        mLifeCycleOwner?.lifecycle?.handleLifecycleEvent(Event.ON_PAUSE)
        super.onPause()
    }

    override fun onStop() {
        mLifeCycleOwner?.lifecycle?.handleLifecycleEvent(Event.ON_STOP)
        super.onStop()
    }

    override fun onDestroyView() {
        if (mLifeCycleOwner != null) {
            mLifeCycleOwner!!.lifecycle.handleLifecycleEvent(Event.ON_DESTROY)
            mLifeCycleOwner = null
        }
        super.onDestroyView()
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initializeViewModels()
        observeLiveData(savedInstanceState == null)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeUI()
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater!!.inflate(mLayout, container, false)
    }

    abstract val mLayout: Int

    abstract fun initializeViewModels()
    abstract fun initializeUI()
    abstract fun observeLiveData(isNewActivity: Boolean)

}
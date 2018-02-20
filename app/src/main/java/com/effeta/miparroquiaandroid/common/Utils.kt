package com.effeta.miparroquiaandroid.common

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProviders
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by aulate on 6/2/18.
 */


/**
 * A thread unsafe lazy function.
 * This function 'must' be called only on single thread.
 */
fun <T> unsafeLazy(initializer: () -> T) = lazy(LazyThreadSafetyMode.NONE, initializer)

fun <T : ViewModel> createViewModel(activity: BaseActivity, viewModelClass: Class<T>): T {
    return ViewModelProviders.of(activity).get(viewModelClass)
}

fun Date.toString(pattern: String): String {
    val simpleDateFormat = SimpleDateFormat(pattern)
    return simpleDateFormat.format(this)
}
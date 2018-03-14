package com.effeta.miparroquiaandroid.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.effeta.miparroquiaandroid.models.Church
import javax.inject.Inject

/**
 * Created by jjimenez on 3/12/18.
 */
class DetailMapViewModel @Inject constructor():ViewModel() {

    private var mChurch: MutableLiveData<Church> = MutableLiveData()

    fun setChurchInformation(church:Church){
        mChurch.value = church
    }

    fun getChurch() = mChurch
}
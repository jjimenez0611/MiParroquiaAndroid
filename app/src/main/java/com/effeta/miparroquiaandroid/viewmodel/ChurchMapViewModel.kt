package com.effeta.miparroquiaandroid.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.effeta.miparroquiaandroid.models.Church
import com.effeta.miparroquiaandroid.repositories.ChurchMapRepository
import javax.inject.Inject

/**
 * Created by jjimenez on 2/26/18.
 */
class ChurchMapViewModel @Inject constructor(mChurchMapRepository : ChurchMapRepository): ViewModel() {

    private var mChurchList: MutableLiveData<List<Church>> = MutableLiveData()

    var isError: MutableLiveData<Boolean> = MutableLiveData()

    init {
        mChurchMapRepository.getChurches()
                .subscribe { list ->
                    mChurchList.postValue(list)
                }
    }

    fun getChurches() = mChurchList

}
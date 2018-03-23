package com.effeta.miparroquiaandroid.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.effeta.miparroquiaandroid.models.Church
import com.effeta.miparroquiaandroid.repositories.ChurchRepository
import javax.inject.Inject

/** -*- coding: utf-8 -*-
 * This file was created by
 * @Author: jjimenez
 * @Date:   2/26/18
 */
class ChurchListViewModel @Inject constructor(mChurchRepository: ChurchRepository) : ViewModel() {

    private var mChurchList: MutableLiveData<List<Church>> = MutableLiveData()

    var isError: MutableLiveData<Boolean> = MutableLiveData()

    init {
        mChurchRepository.getChurches()
                .subscribe { list ->
                    mChurchList.postValue(list)
                }
    }

    fun getChurches() = mChurchList
}

package com.effeta.miparroquiaandroid.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.effeta.miparroquiaandroid.models.Church
import com.effeta.miparroquiaandroid.models.Eucharist
import com.effeta.miparroquiaandroid.repositories.ChurchRepository
import com.effeta.miparroquiaandroid.repositories.EucharistRepository
import javax.inject.Inject

/** -*- coding: utf-8 -*-
 * This file was created by
 * @Author: jjimenez
 * @Date:   2/26/18
 */
class ChurchListViewModel @Inject constructor(private val mChurchRepository: ChurchRepository,
                                              private val mEucharistRepository: EucharistRepository) : ViewModel() {

    private var mChurchList: MutableLiveData<List<Church>> = MutableLiveData()

    private var mEucharistList: MutableLiveData<List<Eucharist>> = MutableLiveData()

    var isError: MutableLiveData<Boolean> = MutableLiveData()

    init {
    }

    fun getChurches(): MutableLiveData<List<Church>> {
        //first try to get from firebase
        mChurchRepository.getChurchesFromFirebase().subscribe {
            //them get the churches from database
            mChurchRepository.getChurchesFromRoom().subscribe { list ->
                mChurchList.postValue(list)
            }
        }
        return mChurchList
    }


    fun getEucharists(): MutableLiveData<List<Eucharist>> {
        mEucharistRepository.getEucharistsFromFirebaseAndSave().subscribe { list ->
            mEucharistList.postValue(list)
        }
        return mEucharistList
    }

}


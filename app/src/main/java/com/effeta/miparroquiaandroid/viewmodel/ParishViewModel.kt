package com.effeta.miparroquiaandroid.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.effeta.miparroquiaandroid.models.Parish
import com.effeta.miparroquiaandroid.repositories.ParishRepository
import javax.inject.Inject

/**
 * Created by aulate on 5/3/18.
 */
class ParishViewModel @Inject constructor(
        private val mParishRepository: ParishRepository) : ViewModel() {

    private var mParishList: MutableLiveData<List<Parish>> = MutableLiveData()

    private var mParish: MutableLiveData<Parish> = MutableLiveData()

    init {
        mParishRepository.getParishes().subscribe {
            mParishList.postValue(it)
        }
    }

    fun getParishes() = mParishList

    fun storeSelectedParish(parishKey: String) {
        mParishRepository.storeParish(parishKey)
    }

    fun getParish(): MutableLiveData<Parish> {
        mParishRepository.getParish().subscribe {
            mParish.postValue(it)
        }
        return mParish
    }
}
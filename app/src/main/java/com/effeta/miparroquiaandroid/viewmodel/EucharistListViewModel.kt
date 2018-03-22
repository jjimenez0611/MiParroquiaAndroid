package com.effeta.miparroquiaandroid.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.effeta.miparroquiaandroid.models.Eucharist
import com.effeta.miparroquiaandroid.repositories.EucharistRepository
import com.effeta.miparroquiaandroid.utils.DayUtils

/**
 * Created by aulate on 14/3/18.
 */
class EucharistListViewModel @javax.inject.Inject constructor(private val mEucharistRepository: EucharistRepository) : ViewModel() {

    private var mEucharists: MutableLiveData<List<Eucharist>> = MutableLiveData()

    init {

    }

    fun getWeekDays() = DayUtils.getDaysOfWeek()

    fun getEucharists(): MutableLiveData<List<Eucharist>> {
        mEucharistRepository.getEucharistsByParish().subscribe {
            mEucharists.postValue(it)
        }
        return mEucharists
    }
}
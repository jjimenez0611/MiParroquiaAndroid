package com.effeta.miparroquiaandroid.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.effeta.miparroquiaandroid.models.Eucharist
import com.effeta.miparroquiaandroid.repositories.EucharistRepository
import com.effeta.miparroquiaandroid.utils.DayUtils
import org.joda.time.DateTime

/** -*- coding: utf-8 -*-
 * This file was created by
 * @Author: aulate
 * @Date:   14/3/18
 */
class EucharistListViewModel @javax.inject.Inject constructor(private val mEucharistRepository: EucharistRepository) : ViewModel() {

    private var mEucharists: MutableLiveData<List<Eucharist>> = MutableLiveData()

    fun getWeekDays() = DayUtils.getDaysOfWeek()

    fun getEucharistsByDay(day: DateTime): MutableLiveData<List<Eucharist>> {
        mEucharistRepository.getEucharistsByParishAndDayFromRoom(day).subscribe {
            mEucharists.postValue(it)
        }
        return mEucharists
    }
}
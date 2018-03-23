package com.effeta.miparroquiaandroid.viewmodel

import android.arch.lifecycle.MutableLiveData
import com.effeta.miparroquiaandroid.common.HOUR
import com.effeta.miparroquiaandroid.models.Eucharist
import com.effeta.miparroquiaandroid.repositories.ChurchRepository
import org.joda.time.DateTime
import javax.inject.Inject

/** -*- coding: utf-8 -*-
 * This file was created by
 * @Author: aulate
 * @Date:   20/3/18
 */
class EucharistViewModel @Inject constructor(private val mChurchRepository: ChurchRepository) {

    var eucharist: Eucharist? = null
        set(value) {
            field = value
            value?.mChurch?.let { getChurch(it) }
            value?.mHour?.let { getEucharistHour(it) }
            value?.mPriestName?.let { getPriest(it) }
        }

    val hour: MutableLiveData<String> = MutableLiveData()

    val church: MutableLiveData<String> = MutableLiveData()

    val priest: MutableLiveData<String> = MutableLiveData()

    private fun getEucharistHour(eucharistHour: DateTime) {
        hour.postValue(eucharistHour.toString(HOUR))
    }

    private fun getPriest(priestKey: String) {
        priest.postValue(priestKey)
    }

    private fun getChurch(chuchKey: String) {
        mChurchRepository.getChurch(chuchKey).subscribe({
            church.postValue(it.mName)
        })
    }
}
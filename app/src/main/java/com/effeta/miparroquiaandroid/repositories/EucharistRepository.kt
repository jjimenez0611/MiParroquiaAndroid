package com.effeta.miparroquiaandroid.repositories

import com.effeta.miparroquiaandroid.common.ISO_8601_DATE_FORMAT
import com.effeta.miparroquiaandroid.common.getEndDay
import com.effeta.miparroquiaandroid.common.getStartDay
import com.effeta.miparroquiaandroid.models.Eucharist
import com.effeta.miparroquiaandroid.services.firebase.FirebaseEucharist
import com.effeta.miparroquiaandroid.services.room.dao.EucharistDao
import com.effeta.miparroquiaandroid.services.sharedPref.SharedPreferencesHelper
import com.effeta.miparroquiaandroid.utils.DayUtils
import io.reactivex.Observable
import org.joda.time.DateTime
import javax.inject.Inject


/** -*- coding: utf-8 -*-
 * This file was created by
 * @Author: aulate
 * @Date:   14/3/18
 */
class EucharistRepository @Inject constructor(private val mFirebaseEucharist: FirebaseEucharist,
                                              private val mSharedPreferences: SharedPreferencesHelper,
                                              private val mEucharistDao: EucharistDao) {


    fun getEucharistsByParish(): Observable<List<Eucharist>> {
        val parishKey = mSharedPreferences.getParishKey()
        return mFirebaseEucharist.getEucharistsByParish(parishKey!!)
    }

    fun getEucharistsByParishAndDay(day: DateTime): Observable<List<Eucharist>> {
        val parishKey = mSharedPreferences.getParishKey()
        return mFirebaseEucharist.getEucharistsByParishAndDay(parishKey!!, day)
    }

    fun getEucharistsByParishAndDayFromRoom(day: DateTime): Observable<List<Eucharist>> {
        val parishKey = mSharedPreferences.getParishKey()
        return mEucharistDao.getEucharistsByDay(parishKey!!, day.getStartDay().toString(ISO_8601_DATE_FORMAT),
                day.getEndDay().toString(ISO_8601_DATE_FORMAT)).toObservable()


    }

    fun getEucharistsFromFirebaseAndSave(): Observable<List<Eucharist>> {
        val parishKey = mSharedPreferences.getParishKey()
        val days = DayUtils.getDaysOfWeek()
        return mFirebaseEucharist.getEucharistsByParishAndDays(parishKey!!, days.first(), days.last()).doOnNext {
            mEucharistDao.insertAll(it)
        }
    }
}
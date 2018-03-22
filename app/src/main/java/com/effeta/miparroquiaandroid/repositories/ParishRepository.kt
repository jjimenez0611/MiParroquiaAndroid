package com.effeta.miparroquiaandroid.repositories

import com.effeta.miparroquiaandroid.models.Parish
import com.effeta.miparroquiaandroid.services.firebase.FirebaseParish
import com.effeta.miparroquiaandroid.services.room.dao.ParishDao
import com.effeta.miparroquiaandroid.services.sharedPref.SharedPreferencesHelper
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by aulate on 5/3/18.
 */
class ParishRepository @Inject constructor(private val mFirebaseParish: FirebaseParish,
                                           private val mRoomParish: ParishDao,
                                           private val mSharedPreferences: SharedPreferencesHelper) {

    fun getParishes(): Observable<List<Parish>> {
        return Observable.concatArray(
                getParishesFromRoom(),
                getParishesFromFirebase())
    }

    private fun getParishesFromRoom(): Observable<List<Parish>> {
//        Log.d(logName(), "Getting parishes from Room...")
        return mRoomParish.getAllParishes()
                .filter {
                    it.isNotEmpty()
                }.toObservable()
    }

    private fun getParishesFromFirebase(): Observable<List<Parish>> {
//        Log.d(logName(), "Getting parishes from Firebase...")
        return mFirebaseParish.getAllParishes()
                .doOnNext {
                    //                    Log.d(logName(), "Storing ${it.size} parishes from API...")
                    mRoomParish.insertAll(it)
                }
    }

    fun storeParish(parishKey: String) {
        mSharedPreferences.storeParishKey(parishKey)
    }

    fun getParish(): Observable<Parish> {
        val parishKey = mSharedPreferences.getParishKey()
        return mRoomParish.getParishByKey(parishKey!!).toObservable()
    }

    fun hasParishStored(): Boolean {
        return mSharedPreferences.hasParishStored()
    }
}
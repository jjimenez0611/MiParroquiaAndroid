package com.effeta.miparroquiaandroid.repositories

import com.effeta.miparroquiaandroid.models.Parish
import com.effeta.miparroquiaandroid.services.firebase.FirebaseParish
import com.effeta.miparroquiaandroid.services.sharedPref.SharedPreferencesHelper
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by aulate on 5/3/18.
 */
class ParishRepository @Inject constructor(private val mFirebaseParish: FirebaseParish,
                                           private val mSharedPreferences: SharedPreferencesHelper) {
    fun getParishes(): Observable<List<Parish>> {
        return mFirebaseParish.getAllParishes()
    }

    fun storeParish(parishKey: String) {
        mSharedPreferences.storeParishKey(parishKey)
    }

    fun getParish(): Observable<Parish> {
        val parishKey = mSharedPreferences.getParishKey()
        return mFirebaseParish.getParish(parishKey!!)
    }
}
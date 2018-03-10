package com.effeta.miparroquiaandroid.repositories

import com.effeta.miparroquiaandroid.models.Church
import com.effeta.miparroquiaandroid.services.firebase.FirebaseChurchMap
import com.effeta.miparroquiaandroid.services.sharedPref.SharedPreferencesHelper
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by jjimenez on 2/26/18.
 */
class ChurchMapRepository @Inject constructor(private val mChurchMapFirebaseChurchMap: FirebaseChurchMap,
                                              private val mSharedPreferences: SharedPreferencesHelper) {


    fun getChurches(): Observable<List<Church>> {
        val parishKey = mSharedPreferences.getParishKey()

        return mChurchMapFirebaseChurchMap.getChurchListByParish(parishKey)
    }

}
package com.effeta.miparroquiaandroid.repositories

import com.effeta.miparroquiaandroid.models.Eucharist
import com.effeta.miparroquiaandroid.services.firebase.FirebaseEucharist
import com.effeta.miparroquiaandroid.services.sharedPref.SharedPreferencesHelper
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by aulate on 14/3/18.
 */
class EucharistRepository @Inject constructor(private val mFirebaseEucharist: FirebaseEucharist,
                                              private val mSharedPreferences: SharedPreferencesHelper) {


    fun getEucharistsByParish(): Observable<List<Eucharist>> {
        val parishKey = mSharedPreferences.getParishKey()
        return mFirebaseEucharist.getEucharistsByParish(parishKey!!)
    }
}
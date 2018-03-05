package com.effeta.miparroquiaandroid.repositories

import com.effeta.miparroquiaandroid.models.Church
import com.effeta.miparroquiaandroid.services.firebase.FirebaseChurchMap
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by jjimenez on 2/26/18.
 */
class ChurchMapRepository @Inject constructor(private val mChurchMapFirebaseChurchMap: FirebaseChurchMap) {


    fun getChurches() : Observable<List<Church>>{
        return mChurchMapFirebaseChurchMap.getAllChurches()
    }
}
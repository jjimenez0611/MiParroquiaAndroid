package com.effeta.miparroquiaandroid.repositories

import com.effeta.miparroquiaandroid.models.Church
import com.effeta.miparroquiaandroid.services.firebase.FirebaseChurchMap
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by jjimenez on 2/26/18.
 */
class ChurchMapRepository @Inject constructor(private val mChurchMapFirebaseChruchMap: FirebaseChurchMap) {


    fun getChurchs() : Observable<List<Church>>{
        return mChurchMapFirebaseChruchMap.getAllChurchs()
    }
}
package com.effeta.miparroquiaandroid.repositories

import com.effeta.miparroquiaandroid.models.Parish
import com.effeta.miparroquiaandroid.services.firebase.FirebaseParish
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by aulate on 5/3/18.
 */
class ParishRepository @Inject constructor(private val mFirebaseParish: FirebaseParish) {
    fun getParishes(): Observable<List<Parish>> {
        return mFirebaseParish.getAllParishes()
    }
}
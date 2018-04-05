package com.effeta.miparroquiaandroid.repositories

import com.effeta.miparroquiaandroid.models.Church
import com.effeta.miparroquiaandroid.services.firebase.FirebaseChurch
import com.effeta.miparroquiaandroid.services.firebase.FirebaseStorageImages
import com.effeta.miparroquiaandroid.services.room.dao.ChurchDao
import com.effeta.miparroquiaandroid.services.sharedPref.SharedPreferencesHelper
import com.google.firebase.storage.StorageReference
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by jjimenez on 2/26/18.
 */
class ChurchRepository @Inject constructor(private val mChurchMapFirebaseChurch: FirebaseChurch,
                                           private val mRoomChurch: ChurchDao,
                                           private val mSharedPreferences: SharedPreferencesHelper,
                                           private val mFirebaseStorageImages: FirebaseStorageImages) {


    fun getChurchesFromFirebase(): Observable<List<Church>> {
        val parishKey = mSharedPreferences.getParishKey()
        return mChurchMapFirebaseChurch.getChurchListByParish(parishKey).doOnNext {
            mRoomChurch.insertAll(it)
        }
    }

    fun getChurchesFromRoom(): Observable<List<Church>> {
        val parishKey = mSharedPreferences.getParishKey()
        return mRoomChurch.getAllChurchesByParish(parishKey!!).toObservable()
    }

    fun getChurch(churchKey: String): Observable<Church> {
        return mRoomChurch.getChurchByKey(churchKey).toObservable()
    }

    fun getChurchImage(churchKey: String): StorageReference {
        return mFirebaseStorageImages.getReferenceStorageImage(churchKey)
    }
}
package com.effeta.miparroquiaandroid.repositories

import com.effeta.miparroquiaandroid.models.Announcement
import com.effeta.miparroquiaandroid.services.firebase.FirebaseAnnouncement
import com.effeta.miparroquiaandroid.services.sharedPref.SharedPreferencesHelper
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by aulate on 16/2/18.
 */

class AnnouncementRepository @Inject constructor(
        private val mFirebaseAnnouncement: FirebaseAnnouncement,
        private val mSharedPreferences: SharedPreferencesHelper) {

    fun getAnnouncements(): Observable<List<Announcement>> {
        val parishKey = mSharedPreferences.getParishKey()
        return mFirebaseAnnouncement.getAnnouncementListByParish(parishKey!!)
    }
}
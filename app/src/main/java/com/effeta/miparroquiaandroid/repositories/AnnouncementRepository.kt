package com.effeta.miparroquiaandroid.repositories

import com.effeta.miparroquiaandroid.models.Announcement
import com.effeta.miparroquiaandroid.services.firebase.FirebaseAnnouncement
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by aulate on 16/2/18.
 */

class AnnouncementRepository @Inject constructor(private val mFirebaseAnnouncement : FirebaseAnnouncement)  {

    fun getAnnouncements(): Observable<List<Announcement>> {

        return mFirebaseAnnouncement.getAllAnnouncements()
    }
}
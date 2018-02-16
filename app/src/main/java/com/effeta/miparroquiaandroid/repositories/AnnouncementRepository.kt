package com.effeta.miparroquiaandroid.repositories

import com.effeta.miparroquiaandroid.models.Announcement
import com.effeta.miparroquiaandroid.services.firebase.FirebaseAnnouncement
import io.reactivex.Observable

/**
 * Created by aulate on 16/2/18.
 */
object AnnouncementRepository {

    fun getAnnouncements() : Observable<List<Announcement>> {
        return FirebaseAnnouncement.getAllAnnouncements()
    }
}
package com.effeta.miparroquiaandroid.services.firebase

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot

/**
 * Created by aulate on 16/2/18.
 */
object FirebaseAnnouncement {
    val announcementKey = "announcements"
    val announcements: CollectionReference = FirebaseFirestore.getInstance().collection(announcementKey)

    fun getAnnouncementList(): Task<QuerySnapshot> {
        return announcements.get()
    }
}
package com.effeta.miparroquiaandroid.services.firebase

import android.util.Log
import com.effeta.miparroquiaandroid.models.Announcement
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import io.reactivex.Observable

/**
 * Created by aulate on 16/2/18.
 */
object FirebaseAnnouncement {
    val announcementKey = "announcements"
    val announcements: CollectionReference = FirebaseFirestore.getInstance().collection(announcementKey)

    fun getAnnouncementList(): Observable<List<Announcement>> {
        return Observable.create<List<Announcement>> {
            announcements.get().addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val list = ArrayList<Announcement>()
                    task.result.documents.forEach({ documentSnapshot ->
                        val a = documentSnapshot.toObject(Announcement::class.java)
                        a.mKey = documentSnapshot.id
                        Log.d("AnnouncementRepository", a.toString())
                        list.plus(a)
                    })
                    it.onNext(list)
                    it.onComplete()
                } else {
                    it.onError(task.exception!!)
                }
            }
        }
    }

    fun getAnnouncementList(type : String): Observable<List<Announcement>> {
        return Observable.create<List<Announcement>> {
            announcements.whereEqualTo(Announcement.FirebaseProperties.type, type).get().addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val list = ArrayList<Announcement>()
                    task.result.documents.forEach({ documentSnapshot ->
                        val a = documentSnapshot.toObject(Announcement::class.java)
                        a.mKey = documentSnapshot.id
                        Log.d("AnnouncementRepository", a.toString())
                        list.plus(a)
                    })
                    it.onNext(list)
                    it.onComplete()
                } else {
                    it.onError(task.exception!!)
                }
            }
        }
    }
}
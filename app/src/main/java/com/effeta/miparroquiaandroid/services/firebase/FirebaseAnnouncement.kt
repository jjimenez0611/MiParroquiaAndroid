package com.effeta.miparroquiaandroid.services.firebase

import android.util.Log
import com.effeta.miparroquiaandroid.TestOpen
import com.effeta.miparroquiaandroid.models.Announcement
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by aulate on 16/2/18.
 */
@TestOpen

class FirebaseAnnouncement @Inject constructor() {
    val announcementKey = "announcements"
    val announcements: CollectionReference = FirebaseFirestore.getInstance().collection(announcementKey)

    fun getAllAnnouncements(): Observable<List<Announcement>> {
        return Observable.create<List<Announcement>> {
            announcements.get().addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val list = ArrayList<Announcement>()
                    task.result.documents.forEach({ documentSnapshot ->
                        val a = documentSnapshot.toObject(Announcement::class.java)
                        a.mKey = documentSnapshot.id
                        Log.d("FirebaseAnnouncement", a.toString())
                        list.add(a)
                    })
                    it.onNext(list)
                    it.onComplete()
                } else {
                    it.onError(task.exception!!)
                }
            }
        }
    }

    fun getAllAnnouncements(limit: Long): Observable<List<Announcement>> {
        return Observable.create<List<Announcement>> {
            announcements.limit(limit).get().addOnCompleteListener { task ->
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

    fun getAnnouncementListByType(type: String): Observable<List<Announcement>> {
        return Observable.create<List<Announcement>> {
            announcements.whereEqualTo(Announcement.FirebasePropertiesAnnouncement.type, type).get().addOnCompleteListener { task ->
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

    fun getAnnouncementListByChurch(church: String): Observable<List<Announcement>> {
        return Observable.create<List<Announcement>> {
            announcements.whereEqualTo(Announcement.FirebasePropertiesAnnouncement.church, church).get().addOnCompleteListener { task ->
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
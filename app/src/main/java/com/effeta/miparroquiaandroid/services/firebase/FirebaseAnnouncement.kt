package com.effeta.miparroquiaandroid.services.firebase

import android.util.Log
import com.effeta.miparroquiaandroid.TestOpen
import com.effeta.miparroquiaandroid.models.Announcement
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.QuerySnapshot
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
                    it.onNext(parseAnnouncements(task))
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
                    it.onNext(parseAnnouncements(task))
                    it.onComplete()
                } else {
                    it.onError(task.exception!!)
                }
            }
        }
    }

    fun getAnnouncementListByType(type: String): Observable<List<Announcement>> {
        return Observable.create<List<Announcement>> {
            announcements.whereEqualTo(Announcement.FirebaseProperties.type, type).get().addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    it.onNext(parseAnnouncements(task))
                    it.onComplete()
                } else {
                    it.onError(task.exception!!)
                }
            }
        }
    }

    fun getAnnouncementListByChurch(church: String): Observable<List<Announcement>> {
        return Observable.create<List<Announcement>> {
            announcements.whereEqualTo(Announcement.FirebaseProperties.church, church).get().addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    it.onNext(parseAnnouncements(task))
                    it.onComplete()
                } else {
                    it.onError(task.exception!!)
                }
            }
        }
    }

    fun getAnnouncementListByParish(parish: String?): Observable<List<Announcement>> {
        return Observable.create {
            if (!parish.isNullOrEmpty()) {
                val q: Query = announcements.whereEqualTo(Announcement.FirebaseProperties.parish, parish)
                q.get().addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        it.onNext(parseAnnouncements(task))
                        it.onComplete()
                    } else {
                        it.onError(task.exception!!)
                    }
                }
            }
        }
    }

    private fun parseAnnouncements(task: Task<QuerySnapshot>): List<Announcement> {
        val list = ArrayList<Announcement>()
        task.result.documents.forEach({ documentSnapshot ->
            val a = documentSnapshot.toObject(Announcement::class.java)
            a.mKey = documentSnapshot.id
            Log.d("AnnouncementRepository", a.toString())
            list.add(a)
        })
        return list
    }
}
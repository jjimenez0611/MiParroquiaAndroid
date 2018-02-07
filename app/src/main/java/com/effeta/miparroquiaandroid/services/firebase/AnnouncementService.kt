package com.effeta.miparroquiaandroid.services.firebase

import android.util.Log
import com.effeta.miparroquiaandroid.models.Announcement
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import io.reactivex.Observable
import java.util.*


/**
 * Created by aulate on 6/2/18.
 */
object AnnouncementService {
    private const val TAG = "FB.AnnouncementService"
    private val mAnnouncementRef: CollectionReference = FirebaseFirestore.getInstance()
            .collection(AnnouncementKeys.ANNOUNCEMENT_REFERENCE)

    fun getAnnouncementsByChurch(churchID: String): Observable<List<Announcement>> {
        return Observable.create {
            val announcementsList : List<Announcement> = ArrayList()
            try {
                val announcements = mAnnouncementRef
                        .whereEqualTo(AnnouncementKeys.FIELD_CHURCH, churchID)
                    .whereLessThan(AnnouncementKeys.FIELD_PUBLISHED_AT, Date())
//                    .whereGreaterThan(AnnouncementKeys.FIELD_EXPIRES_AT, Date())

                announcements.get().addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        for (document in task.result) {
                            Log.d(TAG, document.id + " => " + document.data)
                            val announcement = document.toObject(Announcement::class.java)
                            announcementsList.plus(announcement)
                        }
                        it.onNext(announcementsList)
                        it.onComplete()
                    } else {
                        Log.d(TAG, "Error getting documents: ", task.exception)
                        it.onError(task.exception!!)
                    }
                }
            } catch (e: Exception) {
                it.onError(e)
                Log.d(TAG, "Error getting documents: ", e)
            }
        }
    }

    object AnnouncementKeys {
        const val ANNOUNCEMENT_REFERENCE = "announcements"
        const val FIELD_CREATED_AT = "created_at"
        const val FIELD_PUBLISHED_AT = "published_at"
        const val FIELD_EXPIRES_AT = "expires_at"
        const val FIELD_CHURCH = "church"
        const val FIELD_ANNOUNCEMENT_TYPE = "type"
    }
}
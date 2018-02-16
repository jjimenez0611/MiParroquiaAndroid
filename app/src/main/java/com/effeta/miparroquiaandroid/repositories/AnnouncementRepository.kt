package com.effeta.miparroquiaandroid.repositories

import android.arch.lifecycle.Observer
import android.util.Log
import com.effeta.miparroquiaandroid.models.Announcement
import com.effeta.miparroquiaandroid.services.firebase.FirebaseAnnouncement
import io.reactivex.*

/**
 * Created by aulate on 16/2/18.
 */
object AnnouncementRepository {

    fun getAnnouncements() : Observable<List<Announcement>> {
        return Observable.create<List<Announcement>> {
            FirebaseAnnouncement.getAnnouncementList().addOnCompleteListener { task ->
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
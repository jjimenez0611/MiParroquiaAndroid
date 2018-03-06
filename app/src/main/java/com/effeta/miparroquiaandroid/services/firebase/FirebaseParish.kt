package com.effeta.miparroquiaandroid.services.firebase

import android.util.Log
import com.effeta.miparroquiaandroid.models.Parish
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by aulate on 5/3/18.
 */
class FirebaseParish @Inject constructor() {
    private val parishesKey = "parishes"
    val parishes: CollectionReference = FirebaseFirestore.getInstance().collection(parishesKey)

    fun getAllParishes(): Observable<List<Parish>> {
        return Observable.create<List<Parish>> {
            parishes.orderBy(Parish.FirebaseProperties.name).get().addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val list = ArrayList<Parish>()
                    task.result.documents.forEach({ documentSnapshot ->
                        val a = documentSnapshot.toObject(Parish::class.java)
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
}
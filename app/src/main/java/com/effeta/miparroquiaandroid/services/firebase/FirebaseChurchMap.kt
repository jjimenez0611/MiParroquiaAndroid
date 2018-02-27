package com.effeta.miparroquiaandroid.services.firebase

import android.util.Log
import com.effeta.miparroquiaandroid.models.Church
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by jjimenez on 2/26/18.
 */


class FirebaseChurchMap @Inject constructor(){

    val churchsKey = "churchs"
    val churchs: CollectionReference = FirebaseFirestore.getInstance().collection(churchsKey)

    fun getAllChurchs(): Observable<List<Church>> {
        return Observable.create<List<Church>> {
            churchs.get().addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val list = ArrayList<Church>()
                    task.result.documents.forEach({ documentSnapshot ->
                        val a = documentSnapshot.toObject(Church::class.java)
                        a.mKey = documentSnapshot.id
                        Log.d("FirebaseChurchs", a.toString())
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
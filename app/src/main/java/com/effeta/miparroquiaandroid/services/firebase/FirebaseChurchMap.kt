package com.effeta.miparroquiaandroid.services.firebase

import com.effeta.miparroquiaandroid.models.Church
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by jjimenez on 2/26/18.
 */


class FirebaseChurchMap @Inject constructor(){

    private val churchesKey = "churchs"
    private val churches: CollectionReference = FirebaseFirestore.getInstance().collection(churchesKey)

    fun getAllChurches(): Observable<List<Church>> {
        return Observable.create<List<Church>> {
            churches.get().addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val list = ArrayList<Church>()
                    task.result.documents.forEach({ documentSnapshot ->
                        val a = documentSnapshot.toObject(Church::class.java)
                        a.mKey = documentSnapshot.id
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
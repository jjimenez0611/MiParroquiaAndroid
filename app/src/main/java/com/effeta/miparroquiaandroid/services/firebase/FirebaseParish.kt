package com.effeta.miparroquiaandroid.services.firebase

import com.effeta.miparroquiaandroid.models.Parish
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentSnapshot
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

    fun getParish(parishKey: String): Observable<Parish> {
        return Observable.create<Parish> {
            parishes.document(parishKey).get().addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val parish = parseParish(task.result)
                    it.onNext(parish)
                    it.onComplete()
                } else {
                    it.onError(task.exception!!)
                }
            }
        }
    }

    private fun parseParish(result: DocumentSnapshot): Parish {
        return result.toObject(Parish::class.java)
    }
}
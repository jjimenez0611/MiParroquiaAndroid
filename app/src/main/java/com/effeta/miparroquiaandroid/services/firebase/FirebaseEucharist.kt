package com.effeta.miparroquiaandroid.services.firebase

import com.effeta.miparroquiaandroid.models.Eucharist
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by aulate on 14/3/18.
 */
class FirebaseEucharist @Inject constructor() {
    private val eucharistKey = "eucharists"
    private val eucharists: CollectionReference = FirebaseFirestore.getInstance().collection(eucharistKey)

    fun getAllEucharist(): Observable<List<Eucharist>> {
        return Observable.create<List<Eucharist>> {
            eucharists.orderBy(Eucharist.FirebaseProperties.hour).get().addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    it.onNext(parseEucharistList(task))
                    it.onComplete()
                } else {
                    it.onError(task.exception!!)
                }
            }
        }
    }

    fun getEucharistsByParish(parishKey: String): Observable<List<Eucharist>> {
        return Observable.create<List<Eucharist>> {
            eucharists.whereEqualTo(Eucharist.FirebaseProperties.parish, parishKey)
                    .orderBy(Eucharist.FirebaseProperties.hour)
                    .get().addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            it.onNext(parseEucharistList(task))
                            it.onComplete()
                        } else {
                            it.onError(task.exception!!)
                        }
                    }
        }
    }

    private fun parseEucharistList(task: Task<QuerySnapshot>): List<Eucharist> {
        val list = ArrayList<Eucharist>()
        task.result.documents.forEach({ documentSnapshot ->
            list.add(parseEucharist(documentSnapshot))
        })
        return list
    }

    private fun parseEucharist(documentSnapshot: DocumentSnapshot): Eucharist {
        val eucharist = documentSnapshot.toObject(Eucharist::class.java)
        eucharist.mKey = documentSnapshot.id
        return eucharist
    }
}
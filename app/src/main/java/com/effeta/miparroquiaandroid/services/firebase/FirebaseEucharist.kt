package com.effeta.miparroquiaandroid.services.firebase

import com.effeta.miparroquiaandroid.common.getEndDay
import com.effeta.miparroquiaandroid.common.getStartDay
import com.effeta.miparroquiaandroid.models.Eucharist
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import io.reactivex.Observable
import org.joda.time.DateTime
import java.util.*
import javax.inject.Inject


/** -*- coding: utf-8 -*-
 * This file was created by
 * @Author: aulate
 * @Date:   14/3/18
 */
class FirebaseEucharist @Inject constructor() {
    private val eucharistKey = "eucharists"
    private val eucharists: CollectionReference = FirebaseFirestore.getInstance().collection(eucharistKey)

    fun getAllEucharist(): Observable<List<Eucharist>> {
        return Observable.create<List<Eucharist>> {
            eucharists.orderBy(Eucharist.Properties.hour).get().addOnCompleteListener { task ->
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
            eucharists
                    .whereEqualTo(Eucharist.Properties.parish, parishKey)
                    .orderBy(Eucharist.Properties.hour)
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

    fun getEucharistsByParishAndDay(parishKey: String, day: DateTime): Observable<List<Eucharist>> {
        val dayStart = day.getStartDay()
        val dayEnd = day.getEndDay()
        return Observable.create<List<Eucharist>> {
            eucharists
                    .whereEqualTo(Eucharist.Properties.parish, parishKey)
                    .whereGreaterThanOrEqualTo(Eucharist.Properties.hour, dayStart)
                    .whereLessThanOrEqualTo(Eucharist.Properties.hour, dayEnd)
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
        eucharist.mHour = DateTime(documentSnapshot[Eucharist.Properties.hour] as Date)
        return eucharist
    }
}
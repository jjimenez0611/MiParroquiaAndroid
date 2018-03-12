package com.effeta.miparroquiaandroid.services.firebase

import com.effeta.miparroquiaandroid.models.Church
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.*
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by jjimenez on 2/26/18.
 */


class FirebaseChurchMap @Inject constructor() {

    private val churchesKey = "churchs"
    private val churches: CollectionReference = FirebaseFirestore.getInstance().collection(churchesKey)

    fun getAllChurches(): Observable<List<Church>> {
        return Observable.create<List<Church>> {
            churches.get().addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val list = ArrayList<Church>()
                    task.result.documents.forEach({ documentSnapshot ->
                        val a = parseChurch(documentSnapshot)
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

    fun getChurchListByParish(parishKey: String?): Observable<List<Church>> {
        return Observable.create {
            if (!parishKey.isNullOrEmpty()) {
                val q: Query = churches.whereEqualTo(Church.FirebaseProperties.parish, parishKey)
                q.get().addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        it.onNext(parseChurches(task))
                        it.onComplete()
                    } else {
                        it.onError(task.exception!!)
                    }
                }
            }
        }
    }

    private fun parseChurch(result: DocumentSnapshot): Church {
        return result.toObject(Church::class.java)
    }

    private fun parseChurches(task: Task<QuerySnapshot>): List<Church> {
        val list = ArrayList<Church>()
        task.result.documents.forEach({ documentSnapshot ->
            val data = documentSnapshot.data

            val churchToAdd  = documentSnapshot.toObject(Church::class.java)

            churchToAdd.mKey = documentSnapshot.id

            if(data.getValue(Church.FirebaseProperties.geolocation) != null) {
                val geoPoint = data.getValue(Church.FirebaseProperties.geolocation) as GeoPoint
                churchToAdd.mLatitude = geoPoint.latitude
                churchToAdd.mLongitude = geoPoint.longitude
            }

            list.add(churchToAdd)
        })
        return list
    }
}
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

    private val churchesKey = "churches"
    private val churches: CollectionReference = FirebaseFirestore.getInstance().collection(churchesKey)

    fun getAllChurches(): Observable<List<Church>> {
        return Observable.create<List<Church>> {
            churches.get().addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    it.onNext(parseChurchesList(task))
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
                val q: Query = churches.whereEqualTo(Church.Properties.parish, parishKey)
                q.get().addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        it.onNext(parseChurchesList(task))
                        it.onComplete()
                    } else {
                        it.onError(task.exception!!)
                    }
                }
            }
        }
    }

    private fun parseChurch(documentSnapshot: DocumentSnapshot): Church {
        val data = documentSnapshot.data

        val church = documentSnapshot.toObject(Church::class.java)

        church.mKey = documentSnapshot.id

        if (data.getValue(Church.Properties.geolocation) != null) {
            val geoPoint = data.getValue(Church.Properties.geolocation) as GeoPoint
            church.mLatitude = geoPoint.latitude
            church.mLongitude = geoPoint.longitude
        }
        return church
    }

    private fun parseChurchesList(task: Task<QuerySnapshot>): List<Church> {
        val list = ArrayList<Church>()
        task.result.documents.forEach({ documentSnapshot ->
            list.add(parseChurch(documentSnapshot))
        })
        return list
    }
}
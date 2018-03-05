package com.effeta.miparroquiaandroid.models

import com.google.firebase.firestore.GeoPoint
import com.google.firebase.firestore.PropertyName

/**
 * Created by jjimenez on 2/2/18.
 */
data class Church(
        var mKey: String = "",
        @get:PropertyName(FirebaseProperties.image)
        @set:PropertyName(FirebaseProperties.image)
        var mImage: String? = "",
        @get:PropertyName(FirebaseProperties.name)
        @set:PropertyName(FirebaseProperties.name)
        var mName: String = "",
        @get:PropertyName(FirebaseProperties.geolocation)
        @set:PropertyName(FirebaseProperties.geolocation)
        var mUbication: GeoPoint? = GeoPoint(0.0, 0.0)
) {
    object FirebaseProperties {
        const val name = "name"
        const val image = "image"
        const val geolocation = "geolocation"
    }

    override fun toString(): String {
        return mName
    }
}
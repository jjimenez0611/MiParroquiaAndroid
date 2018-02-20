package com.effeta.miparroquiaandroid.models

import com.google.firebase.firestore.GeoPoint
import com.google.firebase.firestore.PropertyName

/**
 * Created by jjimenez on 2/2/18.
 */
class Church(
        var mKey: String = "",
        @get:PropertyName(FirebasePropertiesChurch.image) @set:PropertyName(FirebasePropertiesChurch.image) var mImage: String = "",
        @get:PropertyName(FirebasePropertiesChurch.name) @set:PropertyName(FirebasePropertiesChurch.name) var mName: String = "",
        @get:PropertyName(FirebasePropertiesChurch.geolocation) @set:PropertyName(FirebasePropertiesChurch.geolocation) var mUbication: GeoPoint?
) {
    object FirebasePropertiesChurch {
        const val name = "name"
        const val image = "image"
        const val geolocation = "geolocation"
    }
}
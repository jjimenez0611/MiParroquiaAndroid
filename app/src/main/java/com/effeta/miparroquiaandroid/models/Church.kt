package com.effeta.miparroquiaandroid.models

import com.google.firebase.firestore.PropertyName
import java.io.Serializable


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
        @get:PropertyName(FirebaseProperties.parish)
        @set:PropertyName(FirebaseProperties.parish)
        var mParish: String = "",
        var mLatitude: Double? = 0.0,
        var mLongitude: Double? = 0.0

) : Serializable {
    object FirebaseProperties {
        const val name = "name"
        const val image = "image"
        const val geolocation = "geolocation"
        const val parish = "parish"
    }

    override fun toString(): String {
        return mName
    }


}
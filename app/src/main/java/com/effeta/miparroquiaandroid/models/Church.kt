package com.effeta.miparroquiaandroid.models

import com.google.firebase.firestore.GeoPoint
import com.google.firebase.firestore.PropertyName

/**
 * Created by jjimenez on 2/2/18.
 */
class Church(
        var mKey : String,
        @PropertyName("image") val mImage : String,
        @PropertyName("name") val mName : String,
        @PropertyName("geolocation") val mUbication: GeoPoint
)
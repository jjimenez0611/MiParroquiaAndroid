package com.effeta.miparroquiaandroid.models

import com.google.firebase.firestore.PropertyName
import com.google.firebase.firestore.ServerTimestamp
import java.util.*


/**
 * Created by jjimenez on 2/2/18.
 */
class Eucharist(
        var mKey: String,
        @get:PropertyName(Eucharist.FirebasePropertiesEucharist.church) @set:PropertyName(Eucharist.FirebasePropertiesEucharist.church) var mChurch: String = "",
        @get:PropertyName(Eucharist.FirebasePropertiesEucharist.hour) @set:PropertyName(Eucharist.FirebasePropertiesEucharist.hour) @ServerTimestamp var mHour: Date = Date(),
        @get:PropertyName(Eucharist.FirebasePropertiesEucharist.priest) @set:PropertyName(Eucharist.FirebasePropertiesEucharist.priest) var mPriestName: String = ""
) {
    object FirebasePropertiesEucharist {
        const val church = "church"
        const val hour = "hour"
        const val priest = "priest"
    }
}
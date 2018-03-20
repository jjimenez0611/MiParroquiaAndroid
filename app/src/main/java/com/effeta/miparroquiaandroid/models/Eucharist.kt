package com.effeta.miparroquiaandroid.models

import com.google.firebase.firestore.PropertyName
import com.google.firebase.firestore.ServerTimestamp
import java.util.*


/**
 * Created by jjimenez on 2/2/18.
 */
data class Eucharist(
        var mKey: String = "",
        @get:PropertyName(Eucharist.FirebaseProperties.church)
        @set:PropertyName(Eucharist.FirebaseProperties.church)
        var mChurch: String = "",
        @get:PropertyName(Eucharist.FirebaseProperties.hour)
        @set:PropertyName(Eucharist.FirebaseProperties.hour)
        @ServerTimestamp var mHour: Date = Date(),
        @get:PropertyName(Eucharist.FirebaseProperties.priest)
        @set:PropertyName(Eucharist.FirebaseProperties.priest)
        var mPriestName: String = "",
        @get:PropertyName(Eucharist.FirebaseProperties.parish)
        @set:PropertyName(Eucharist.FirebaseProperties.parish)
        var mParishKey: String = ""
) {
    object FirebaseProperties {
        const val church = "church"
        const val hour = "hour"
        const val priest = "priest"
        const val parish = "parish"
    }

    override fun toString(): String {
        return super.toString()
    }
}
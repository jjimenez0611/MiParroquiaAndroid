package com.effeta.miparroquiaandroid.models

import com.google.firebase.firestore.PropertyName
import com.google.firebase.firestore.ServerTimestamp
import java.sql.Timestamp
import java.util.Date


/**
 * Created by jjimenez on 2/2/18.
 */
class Eucharist(
        var mKey : String,
        @PropertyName("church") val mChurch: String,
        @PropertyName("hour") @ServerTimestamp val mHour: Date,
        @PropertyName("priest") val mPriestName: String
)
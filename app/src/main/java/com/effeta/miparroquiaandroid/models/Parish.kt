package com.effeta.miparroquiaandroid.models

import com.google.firebase.firestore.PropertyName

/**
 * Created by jjimenez on 2/2/18.
 */
class Parish(
        var mKey : String,
        @PropertyName("email") val mEmail: String,
        @PropertyName("schedule") val mSchedule: String,
        @PropertyName("name") val mName: String,
        @PropertyName("parish_priest") val mParishPriest: String,
        @PropertyName("phonenumber") val mPhone: String
)
package com.effeta.miparroquiaandroid.models

import com.google.firebase.firestore.PropertyName

/**
 * Created by jjimenez on 2/2/18.
 */
class Parish(
        var mKey : String,
        @get:PropertyName(Parish.FirebasePropertiesParish.email) @set:PropertyName(Parish.FirebasePropertiesParish.email) var mEmail: String = "",
        @get:PropertyName(Parish.FirebasePropertiesParish.schedule) @set:PropertyName(Parish.FirebasePropertiesParish.schedule) var mSchedule: String = "",
        @get:PropertyName(Parish.FirebasePropertiesParish.name) @set:PropertyName(Parish.FirebasePropertiesParish.name) var mName: String = "",
        @get:PropertyName(Parish.FirebasePropertiesParish.parish_priest) @set:PropertyName(Parish.FirebasePropertiesParish.parish_priest) var mParishPriest: String = "",
        @get:PropertyName(Parish.FirebasePropertiesParish.phonenumber) @set:PropertyName(Parish.FirebasePropertiesParish.phonenumber) var mPhone: String = ""
){
    object FirebasePropertiesParish {
        const val email = "email"
        const val schedule = "schedule"
        const val name = "name"
        const val parish_priest = "parish_priest"
        const val phonenumber = "phonenumber"
    }
}
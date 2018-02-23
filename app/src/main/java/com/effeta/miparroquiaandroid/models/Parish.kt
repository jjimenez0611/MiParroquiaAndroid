package com.effeta.miparroquiaandroid.models

import com.google.firebase.firestore.PropertyName

/**
 * Created by jjimenez on 2/2/18.
 */
class Parish(
        var mKey : String,
        @get:PropertyName(Parish.FirebaseProperties.email)
        @set:PropertyName(Parish.FirebaseProperties.email)
        var mEmail: String = "",
        @get:PropertyName(Parish.FirebaseProperties.schedule)
        @set:PropertyName(Parish.FirebaseProperties.schedule)
        var mSchedule: String = "",
        @get:PropertyName(Parish.FirebaseProperties.name)
        @set:PropertyName(Parish.FirebaseProperties.name)
        var mName: String = "",
        @get:PropertyName(Parish.FirebaseProperties.parish_priest)
        @set:PropertyName(Parish.FirebaseProperties.parish_priest)
        var mParishPriest: String = "",
        @get:PropertyName(Parish.FirebaseProperties.phonenumber)
        @set:PropertyName(Parish.FirebaseProperties.phonenumber)
        var mPhone: String = ""
){
    object FirebaseProperties {
        const val email = "email"
        const val schedule = "schedule"
        const val name = "name"
        const val parish_priest = "parish_priest"
        const val phonenumber = "phonenumber"
    }
}
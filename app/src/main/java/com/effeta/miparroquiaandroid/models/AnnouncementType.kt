package com.effeta.miparroquiaandroid.models

import com.google.firebase.firestore.PropertyName

/**
 * Created by jjimenez on 2/2/18.
 */
class AnnouncementType(
        mKey: String,
        @get:PropertyName(FirebaseProperties.id)
        @set:PropertyName(FirebaseProperties.id)
        var mId: String = "",
        @get:PropertyName(FirebaseProperties.name)
        @set:PropertyName(FirebaseProperties.name)
        var mName: String = ""
) {
    object FirebaseProperties {
        const val name = "name"
        const val id = "id"
    }
}
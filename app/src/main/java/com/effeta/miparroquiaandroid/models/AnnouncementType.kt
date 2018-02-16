package com.effeta.miparroquiaandroid.models

import com.google.firebase.firestore.PropertyName

/**
 * Created by jjimenez on 2/2/18.
 */
class AnnouncementType(
        mKey: String,
        @PropertyName("id") val mId: String,
        @PropertyName("name") val mName: String
)
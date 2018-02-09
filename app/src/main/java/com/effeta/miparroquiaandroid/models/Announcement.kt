package com.effeta.miparroquiaandroid.models

import com.google.firebase.firestore.PropertyName
import com.google.firebase.firestore.ServerTimestamp

/**
 * Created by jjimenez on 2/2/18.
 */
class Announcement(
        var mKey : String,
        @PropertyName("title") val mTitle: String,
        @PropertyName("description") val mDescription: String,
        @PropertyName("church") val mChurch: String,
        @PropertyName("image") val mImage: String,
        @PropertyName("type") var mType: String,
        @PropertyName("created_at") @ServerTimestamp val mCreationDate: Number,
        @PropertyName("published_at") @ServerTimestamp var mPublishDate: Number,
        @PropertyName("expires_at") @ServerTimestamp var mExpirationDate: Number
)
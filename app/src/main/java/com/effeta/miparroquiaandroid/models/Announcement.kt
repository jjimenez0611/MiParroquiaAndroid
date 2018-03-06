package com.effeta.miparroquiaandroid.models

import com.google.firebase.firestore.PropertyName
import com.google.firebase.firestore.ServerTimestamp
import java.util.*

/**
 * Created by jjimenez on 2/2/18.
 */
data class Announcement(
        var mKey: String = "",
        @get:PropertyName(FirebaseProperties.title)
        @set:PropertyName(FirebaseProperties.title)
        var mTitle: String = "",
        @get:PropertyName(FirebaseProperties.description)
        @set:PropertyName(FirebaseProperties.description)
        var mDescription: String = "",
        @get:PropertyName(FirebaseProperties.church)
        @set:PropertyName(FirebaseProperties.church)
        var mChurch: String = "",
        @get:PropertyName(FirebaseProperties.parish)
        @set:PropertyName(FirebaseProperties.parish)
        var mParish: String = "",
        @get:PropertyName(FirebaseProperties.image)
        @set:PropertyName(FirebaseProperties.image)
        var mImage: String? = "",
        @get:PropertyName(FirebaseProperties.type)
        @set:PropertyName(FirebaseProperties.type)
        var mType: String = "",
        @get:PropertyName(FirebaseProperties.created_at)
        @set:PropertyName(FirebaseProperties.created_at)
        @ServerTimestamp var mCreatedAt: Date = Date(),
        @get:PropertyName(FirebaseProperties.published_at)
        @set:PropertyName(FirebaseProperties.published_at)
        @ServerTimestamp var mPublishedAt: Date = Date(),
        @get:PropertyName(FirebaseProperties.expires_at)
        @set:PropertyName(FirebaseProperties.expires_at)
        @ServerTimestamp var mExpiresAt: Date = Date()
) {
    object FirebaseProperties {
        const val title = "title"
        const val description = "description"
        const val church = "church"
        const val parish = "parish"
        const val image = "image"
        const val type = "type"
        const val created_at = "created_at"
        const val published_at = "published_at"
        const val expires_at = "expires_at"
    }
}


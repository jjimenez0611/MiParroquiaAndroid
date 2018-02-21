package com.effeta.miparroquiaandroid.models

import com.google.firebase.firestore.PropertyName
import com.google.firebase.firestore.ServerTimestamp
import java.util.*

/**
 * Created by jjimenez on 2/2/18.
 */
data class Announcement(
        var mKey: String = "",
        @get:PropertyName(FirebasePropertiesAnnouncement.title) @set:PropertyName(FirebasePropertiesAnnouncement.title) var mTitle: String = "",
        @get:PropertyName(FirebasePropertiesAnnouncement.description) @set:PropertyName(FirebasePropertiesAnnouncement.description) var mDescription: String = "",
        @get:PropertyName(FirebasePropertiesAnnouncement.church) @set:PropertyName(FirebasePropertiesAnnouncement.church) var mChurch: String = "",
        @get:PropertyName(FirebasePropertiesAnnouncement.image) @set:PropertyName(FirebasePropertiesAnnouncement.image) var mImage: String? = "",
        @get:PropertyName(FirebasePropertiesAnnouncement.type) @set:PropertyName(FirebasePropertiesAnnouncement.type) var mType: String = "",
        @get:PropertyName(FirebasePropertiesAnnouncement.created_at) @set:PropertyName(FirebasePropertiesAnnouncement.created_at) @ServerTimestamp var mCreatedAt: Date = Date(),
        @get:PropertyName(FirebasePropertiesAnnouncement.published_at) @set:PropertyName(FirebasePropertiesAnnouncement.published_at) @ServerTimestamp var mPublishedAt: Date = Date(),
        @get:PropertyName(FirebasePropertiesAnnouncement.expires_at) @set:PropertyName(FirebasePropertiesAnnouncement.expires_at) @ServerTimestamp var mExpiresAt: Date = Date()
) {
    object FirebasePropertiesAnnouncement {
        const val title = "title"
        const val description = "description"
        const val church = "church"
        const val image = "image"
        const val type = "type"
        const val created_at = "created_at"
        const val published_at = "published_at"
        const val expires_at = "expires_at"
    }
}


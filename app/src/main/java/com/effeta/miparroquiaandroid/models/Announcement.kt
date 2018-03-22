package com.effeta.miparroquiaandroid.models

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.effeta.miparroquiaandroid.services.room.MiParroquiaDB
import com.google.firebase.firestore.PropertyName
import org.joda.time.DateTime

/**
 * Created by jjimenez on 2/2/18.
 */
@Entity(tableName = MiParroquiaDB.ANNOUNCEMENTS_TABLENAME)
data class Announcement(
        @PrimaryKey
        @ColumnInfo(name = Properties.key)
        var mKey: String = "",
        @ColumnInfo(name = Properties.title)
        @get:PropertyName(Properties.title)
        @set:PropertyName(Properties.title)
        var mTitle: String = "",
        @ColumnInfo(name = Properties.description)
        @get:PropertyName(Properties.description)
        @set:PropertyName(Properties.description)
        var mDescription: String = "",
        @ColumnInfo(name = Properties.church)
        @get:PropertyName(Properties.church)
        @set:PropertyName(Properties.church)
        var mChurch: String = "",
        @ColumnInfo(name = Properties.parish)
        @get:PropertyName(Properties.parish)
        @set:PropertyName(Properties.parish)
        var mParish: String = "",
        @ColumnInfo(name = Properties.image)
        @get:PropertyName(Properties.image)
        @set:PropertyName(Properties.image)
        var mImage: String? = "",
        @ColumnInfo(name = Properties.type)
        @get:PropertyName(Properties.type)
        @set:PropertyName(Properties.type)
        var mType: String = "",
        @ColumnInfo(name = Properties.created_at)
//        @get:PropertyName(Properties.created_at)
//        @set:PropertyName(Properties.created_at)
//        @ServerTimestamp
        var mCreatedAt: DateTime = DateTime(),
        @ColumnInfo(name = Properties.published_at)
//        @get:PropertyName(Properties.published_at)
//        @set:PropertyName(Properties.published_at)
//        @ServerTimestamp
        var mPublishedAt: DateTime = DateTime(),
        @ColumnInfo(name = Properties.expires_at)
//        @get:PropertyName(Properties.expires_at)
//        @set:PropertyName(Properties.expires_at)
//        @ServerTimestamp
        var mExpiresAt: DateTime = DateTime()
) {
    object Properties {
        const val key = "key"
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


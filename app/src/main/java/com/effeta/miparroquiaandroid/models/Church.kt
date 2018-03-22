package com.effeta.miparroquiaandroid.models

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.effeta.miparroquiaandroid.services.room.MiParroquiaDB
import com.google.firebase.firestore.PropertyName
import java.io.Serializable


/**
 * Created by jjimenez on 2/2/18.
 */
@Entity(tableName = MiParroquiaDB.CHURCHES_TABLENAME)
data class Church(
        @PrimaryKey
        @ColumnInfo(name = Properties.key)
        var mKey: String = "",
        @ColumnInfo(name = Properties.image)
        @get:PropertyName(Properties.image)
        @set:PropertyName(Properties.image)
        var mImage: String? = "",
        @ColumnInfo(name = Properties.name)
        @get:PropertyName(Properties.name)
        @set:PropertyName(Properties.name)
        var mName: String = "",
        @ColumnInfo(name = Properties.parish)
        @get:PropertyName(Properties.parish)
        @set:PropertyName(Properties.parish)
        var mParish: String = "",
        @ColumnInfo(name = Properties.latitude)
        var mLatitude: Double? = 0.0,
        @ColumnInfo(name = Properties.longitude)
        var mLongitude: Double? = 0.0

) : Serializable {
    object Properties {
        const val key = "key"
        const val name = "name"
        const val image = "image"
        const val geolocation = "geolocation"
        const val parish = "parish"
        const val longitude = "longitude"
        const val latitude = "latitude"
    }

    override fun toString(): String {
        return mName
    }


}
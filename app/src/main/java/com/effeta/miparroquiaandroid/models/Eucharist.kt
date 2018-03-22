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
@Entity(tableName = MiParroquiaDB.EUCHARISTS_TABLENAME)
data class Eucharist(
        @PrimaryKey
        @ColumnInfo(name = Properties.key)
        var mKey: String = "",
        @ColumnInfo(name = Properties.church)
        @get:PropertyName(Eucharist.Properties.church)
        @set:PropertyName(Eucharist.Properties.church)
        var mChurch: String = "",
        @ColumnInfo(name = Properties.hour)
//        @get:PropertyName(Eucharist.Properties.hour)
//        @set:PropertyName(Eucharist.Properties.hour)
//        @ServerTimestamp
        var mHour: DateTime = DateTime(),
        @ColumnInfo(name = Properties.priest)
        @get:PropertyName(Eucharist.Properties.priest)
        @set:PropertyName(Eucharist.Properties.priest)
        var mPriestName: String? = "",
        @ColumnInfo(name = Properties.parish)
        @get:PropertyName(Eucharist.Properties.parish)
        @set:PropertyName(Eucharist.Properties.parish)
        var mParishKey: String = ""
) {
    object Properties {
        const val key = "key"
        const val church = "church"
        const val hour = "hour"
        const val priest = "priest"
        const val parish = "parish"
    }

    override fun toString(): String {
        return super.toString()
    }
}
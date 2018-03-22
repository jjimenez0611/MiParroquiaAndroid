package com.effeta.miparroquiaandroid.models

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.effeta.miparroquiaandroid.services.room.MiParroquiaDB
import com.google.firebase.firestore.PropertyName

/**
 * Created by jjimenez on 2/2/18.
 */
@Entity(tableName = MiParroquiaDB.PARISHES_TABLENAME)
data class Parish(
        @PrimaryKey
        @ColumnInfo(name = Properties.key)
        var mKey: String = "",
        @ColumnInfo(name = Properties.email)
        @get:PropertyName(Parish.Properties.email)
        @set:PropertyName(Parish.Properties.email)
        var mEmail: String = "",
        @ColumnInfo(name = Properties.schedule)
        @get:PropertyName(Parish.Properties.schedule)
        @set:PropertyName(Parish.Properties.schedule)
        var mSchedule: String = "",
        @ColumnInfo(name = Properties.name)
        @get:PropertyName(Parish.Properties.name)
        @set:PropertyName(Parish.Properties.name)
        var mName: String = "",
        @ColumnInfo(name = Properties.parish_priest)
        @get:PropertyName(Parish.Properties.parish_priest)
        @set:PropertyName(Parish.Properties.parish_priest)
        var mParishPriest: String = "",
        @ColumnInfo(name = Properties.phonenumber)
        @get:PropertyName(Parish.Properties.phonenumber)
        @set:PropertyName(Parish.Properties.phonenumber)
        var mPhone: String = ""
){
    object Properties {
        const val key = "key"
        const val email = "email"
        const val schedule = "schedule"
        const val name = "name"
        const val parish_priest = "parish_priest"
        const val phonenumber = "phonenumber"
    }

    override fun toString(): String {
        return mName
    }
}
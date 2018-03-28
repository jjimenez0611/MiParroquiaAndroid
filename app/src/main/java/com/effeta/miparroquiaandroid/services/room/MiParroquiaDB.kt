package com.effeta.miparroquiaandroid.services.room

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import com.effeta.miparroquiaandroid.common.DateTimeConverters
import com.effeta.miparroquiaandroid.models.Announcement
import com.effeta.miparroquiaandroid.models.Church
import com.effeta.miparroquiaandroid.models.Eucharist
import com.effeta.miparroquiaandroid.models.Parish
import com.effeta.miparroquiaandroid.services.room.dao.ChurchDao
import com.effeta.miparroquiaandroid.services.room.dao.EucharistDao
import com.effeta.miparroquiaandroid.services.room.dao.ParishDao

/** -*- coding: utf-8 -*-
 * This file was created by
 * @Author: aulate
 * @Date:   19/3/18
 */
@Database(entities = [(Church::class), (Parish::class), (Announcement::class), (Eucharist::class)],
        version = 1,
        exportSchema = false)
@TypeConverters(value = [(DateTimeConverters::class)])
abstract class MiParroquiaDB : RoomDatabase() {
    abstract fun parishDao(): ParishDao
    abstract fun churchDao(): ChurchDao
    abstract fun eucharistDao(): EucharistDao

    companion object {
        const val PARISHES_TABLENAME = "parishes"
        const val CHURCHES_TABLENAME = "churches"
        const val ANNOUNCEMENTS_TABLENAME = "announcements"
        const val EUCHARISTS_TABLENAME = "eucharists"
        const val PRIESTS_TABLENAME = "priests"
        const val CATALOGUES_TABLENAME = "catalogues"
    }
}
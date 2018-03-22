package com.effeta.miparroquiaandroid.di

import android.arch.persistence.room.Room
import android.content.Context
import com.effeta.miparroquiaandroid.services.room.MiParroquiaDB
import com.effeta.miparroquiaandroid.services.room.dao.ChurchDao
import com.effeta.miparroquiaandroid.services.room.dao.ParishDao
import dagger.Module
import dagger.Provides

/**
 * Created by aulate on 19/3/18.
 */
@Module
class RoomModule {

    @Provides
    fun provideRoomDatabase(context: Context): MiParroquiaDB {
        return Room.databaseBuilder(context,
                MiParroquiaDB::class.java, "miparroquia-db").allowMainThreadQueries().build()
    }

    @Provides
    fun provideParishDao(db: MiParroquiaDB): ParishDao {
        return db.parishDao()
    }

    @Provides
    fun provideChurchDao(db: MiParroquiaDB): ChurchDao {
        return db.churchDao()
    }
}
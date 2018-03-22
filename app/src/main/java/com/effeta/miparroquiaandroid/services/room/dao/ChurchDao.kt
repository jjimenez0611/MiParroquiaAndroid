package com.effeta.miparroquiaandroid.services.room.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.effeta.miparroquiaandroid.models.Church
import com.effeta.miparroquiaandroid.services.room.MiParroquiaDB
import io.reactivex.Single

/**
 * Created by aulate on 19/3/18.
 */
@Dao
interface ChurchDao {

    @Query("SELECT * FROM ${MiParroquiaDB.CHURCHES_TABLENAME}")
    fun getAllChurches(): Single<List<Church>>

    @Query("SELECT * FROM ${MiParroquiaDB.CHURCHES_TABLENAME} WHERE ${Church.Properties.parish} = :parishKey")
    fun getAllChurchesByParish(parishKey: String): Single<List<Church>>

    @Query("SELECT * FROM ${MiParroquiaDB.CHURCHES_TABLENAME} WHERE ${Church.Properties.key} = :churchKey")
    fun getChurchByKey(churchKey: String): Single<Church>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: Church)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(users: List<Church>)
}
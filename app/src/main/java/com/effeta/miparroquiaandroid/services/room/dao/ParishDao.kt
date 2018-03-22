package com.effeta.miparroquiaandroid.services.room.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import android.arch.persistence.room.Query
import com.effeta.miparroquiaandroid.models.Parish
import com.effeta.miparroquiaandroid.services.room.MiParroquiaDB
import io.reactivex.Single

/**
 * Created by aulate on 19/3/18.
 */
@Dao
interface ParishDao {

    @Query("SELECT * FROM ${MiParroquiaDB.PARISHES_TABLENAME}")
    fun getAllParishes(): Single<List<Parish>>

    @Query("SELECT COUNT(*) FROM ${MiParroquiaDB.PARISHES_TABLENAME} LIMIT 1")
    fun countParishes(): Int

    @Query("SELECT * FROM ${MiParroquiaDB.PARISHES_TABLENAME} WHERE ${Parish.Properties.key} = :parishKey")
    fun getParishByKey(parishKey: String): Single<Parish>

    @Insert(onConflict = REPLACE)
    fun insert(user: Parish)

    @Insert(onConflict = REPLACE)
    fun insertAll(users: List<Parish>)
}
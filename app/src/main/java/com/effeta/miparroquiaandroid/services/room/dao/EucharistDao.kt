package com.effeta.miparroquiaandroid.services.room.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.effeta.miparroquiaandroid.models.Eucharist
import com.effeta.miparroquiaandroid.services.room.MiParroquiaDB
import io.reactivex.Single
import org.joda.time.DateTime
import java.util.*

/**
 * Created by aulate on 19/3/18.
 */
@Dao
interface EucharistDao {

    @Query("SELECT * FROM ${MiParroquiaDB.EUCHARISTS_TABLENAME}")
    fun getAllEucharists(): Single<List<Eucharist>>

    @Query("SELECT * FROM ${MiParroquiaDB.EUCHARISTS_TABLENAME} WHERE ${Eucharist.Properties.parish} = :parishKey")
    fun getAllEucharistsByParish(parishKey: String): Single<List<Eucharist>>

    @Query("SELECT * FROM ${MiParroquiaDB.EUCHARISTS_TABLENAME} WHERE ${Eucharist.Properties.parish} = :parishKey " +
            "AND date(${Eucharist.Properties.hour}) BETWEEN date(:startDay) AND date(:endDay)" +
            "ORDER BY ${Eucharist.Properties.hour}")
    fun getEucharistsByDay(parishKey: String, startDay: String,endDay: String): Single<List<Eucharist>>

    @Query("SELECT COUNT(*) FROM ${MiParroquiaDB.EUCHARISTS_TABLENAME} WHERE ${Eucharist.Properties.parish} = :parishKey " +
            "AND date(${Eucharist.Properties.hour}) = date(:day) " +
            "ORDER BY ${Eucharist.Properties.hour}")
    fun hasEucharistsByDay(parishKey: String, day: String): Int

    @Query("SELECT * FROM ${MiParroquiaDB.EUCHARISTS_TABLENAME} WHERE ${Eucharist.Properties.key} = :eucharistKey")
    fun getEucharistByKey(eucharistKey: String): Single<Eucharist>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(eucharist: Eucharist)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(eucharists: List<Eucharist>)
}
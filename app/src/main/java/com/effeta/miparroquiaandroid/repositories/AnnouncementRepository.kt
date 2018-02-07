package com.effeta.miparroquiaandroid.repositories

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.LiveDataReactiveStreams
import com.effeta.miparroquiaandroid.models.Announcement
import com.effeta.miparroquiaandroid.services.firebase.AnnouncementService
import io.reactivex.BackpressureStrategy

/**
 * Created by aulate on 6/2/18.
 */
object AnnouncementRepository {

    private val service = AnnouncementService

    fun getAnnouncementsByChurch(church : String) : LiveData<List<Announcement>> {
        return LiveDataReactiveStreams.fromPublisher(
                service.getAnnouncementsByChurch(church)
                        .toFlowable(BackpressureStrategy.BUFFER)
        )
    }
}
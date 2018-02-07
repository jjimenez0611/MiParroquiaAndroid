package com.effeta.miparroquiaandroid.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MediatorLiveData
import android.arch.lifecycle.MutableLiveData
import com.effeta.miparroquiaandroid.models.Announcement
import com.effeta.miparroquiaandroid.repositories.AnnouncementRepository

/**
 * Created by aulate on 6/2/18.
 */

class AnnouncementViewModel(application: Application) : AndroidViewModel(application) {
    private val announcementRepository = AnnouncementRepository

    val announcementsLiveData = MutableLiveData<List<Announcement>> ()

    val churchLiveData = MutableLiveData<String>()
    fun setChurch(church : String) {
        churchLiveData.postValue(church)
        isLoadingLiveData.postValue(true)
        announcementsLiveData.postValue(announcementRepository.getAnnouncementsByChurch(church).value)
    }

    val isLoadingLiveData = MediatorLiveData<Boolean>()
    init {
        isLoadingLiveData.addSource(announcementsLiveData) {
            isLoadingLiveData.postValue(false)
        }
    }

    val throwableLiveData = MediatorLiveData<Throwable> ()
    init {
        throwableLiveData.addSource(announcementsLiveData) {

        }
    }

}

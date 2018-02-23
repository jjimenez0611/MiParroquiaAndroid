package com.effeta.miparroquiaandroid.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.effeta.miparroquiaandroid.models.Announcement
import com.effeta.miparroquiaandroid.repositories.AnnouncementRepository
import javax.inject.Inject

/**
 * Created by aulate on 16/2/18.
 */
class AnnouncementViewModel @Inject constructor(private val announcementRepository : AnnouncementRepository): ViewModel() {

    var announcementList: MutableLiveData<List<Announcement>> = MutableLiveData()

    var isLoading: MutableLiveData<Boolean> = MutableLiveData()

    var isError: MutableLiveData<Boolean> = MutableLiveData()

    fun getAnnouncements() {
        announcementRepository.getAnnouncements()
                .subscribe { list ->
                    isLoading.postValue(false)
                    announcementList.postValue(list)
                }
    }
}
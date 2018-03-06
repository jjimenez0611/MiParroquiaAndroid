package com.effeta.miparroquiaandroid.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.effeta.miparroquiaandroid.models.Announcement
import com.effeta.miparroquiaandroid.repositories.AnnouncementRepository
import javax.inject.Inject

/**
 * Created by aulate on 16/2/18.
 */
class AnnouncementViewModel @Inject constructor(private val mAnnouncementRepository: AnnouncementRepository) : ViewModel() {

    private var mAnnouncementList: MutableLiveData<List<Announcement>> = MutableLiveData()

    var isError: MutableLiveData<Boolean> = MutableLiveData()

    init {
        mAnnouncementRepository.getAnnouncements()
                .subscribe { list ->
                    mAnnouncementList.postValue(list)
                }
    }

    fun getAnnouncements() = mAnnouncementList
}
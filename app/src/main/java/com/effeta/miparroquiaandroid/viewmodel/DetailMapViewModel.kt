package com.effeta.miparroquiaandroid.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.effeta.miparroquiaandroid.models.Church
import com.effeta.miparroquiaandroid.repositories.ChurchRepository
import com.effeta.miparroquiaandroid.services.firebase.FirebaseStorageImages
import com.google.firebase.storage.StorageReference
import javax.inject.Inject

/**
 * Created by jjimenez on 3/12/18.
 */
class DetailMapViewModel @Inject constructor(private val churchRepository: ChurchRepository) : ViewModel() {

    var mChurch: MutableLiveData<Church> = MutableLiveData()

    private var mImageChurch: MutableLiveData<StorageReference> = MutableLiveData()

    fun getImageChurch(churchKey: String): MutableLiveData<StorageReference> {
        mImageChurch.value = churchRepository.getChurchImage(churchKey)
        return mImageChurch

    }

}
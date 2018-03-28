package com.effeta.miparroquiaandroid.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.effeta.miparroquiaandroid.models.Church
import com.effeta.miparroquiaandroid.repositories.ChurchRepository
import com.google.firebase.storage.StorageReference
import javax.inject.Inject

/**
 * Created by jjimenez on 3/12/18.
 */
class ChurchViewModel @Inject constructor(private val churchRepository: ChurchRepository) : ViewModel() {

    var mChurchKey: String? = null
        set(value) {
            field = value
            value?.let {
                churchRepository.getChurch(it).subscribe { church ->
                    mChurch.postValue(church)
                }
                getImageChurch(it)
            }
        }

    private var mChurch: MutableLiveData<Church> = MutableLiveData()

    private var mImageChurch: MutableLiveData<StorageReference> = MutableLiveData()

    fun getChurch() = mChurch

    fun getImageChurch(churchKey: String): MutableLiveData<StorageReference> {
        mImageChurch.value = churchRepository.getChurchImage(churchKey)
        return mImageChurch

    }

}
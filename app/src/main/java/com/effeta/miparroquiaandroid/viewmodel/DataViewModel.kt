package com.effeta.miparroquiaandroid.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.effeta.miparroquiaandroid.repositories.ChurchRepository
import com.effeta.miparroquiaandroid.repositories.EucharistRepository
import javax.inject.Inject

class DataViewModel @Inject constructor(private val mChurchRepository: ChurchRepository,
                                        private val mEucharistRepository: EucharistRepository) : ViewModel() {

    private var isDataSaved: MutableLiveData<Boolean> = MutableLiveData()

    fun getData(): MutableLiveData<Boolean> {
        //first try to get from firebase
        mChurchRepository.getChurchesFromFirebase().subscribe {
            //them get the churches from database
            getChurchesFromRoom()
        }
        return isDataSaved
    }

    private fun getChurchesFromRoom() {
        mChurchRepository.getChurchesFromRoom().subscribe { list ->
            // And finally get the Eucharists
            getEucharistsFromFirebaseAndSave()
        }
    }

    private fun getEucharistsFromFirebaseAndSave() {
        mEucharistRepository.getEucharistsFromFirebaseAndSave().subscribe { eucharists ->
            isDataSaved.postValue(true)
        }
    }

}


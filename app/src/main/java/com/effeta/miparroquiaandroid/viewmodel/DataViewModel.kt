package com.effeta.miparroquiaandroid.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.effeta.miparroquiaandroid.models.Church
import com.effeta.miparroquiaandroid.models.Eucharist
import com.effeta.miparroquiaandroid.repositories.ChurchRepository
import com.effeta.miparroquiaandroid.repositories.EucharistRepository
import javax.inject.Inject

class DataViewModel @Inject constructor(private val mChurchRepository: ChurchRepository,
                                              private val mEucharistRepository: EucharistRepository) : ViewModel() {

    private var mChurchList: MutableLiveData<List<Church>> = MutableLiveData()

    private var mEucharistList: MutableLiveData<List<Eucharist>> = MutableLiveData()

    var isError: MutableLiveData<Boolean> = MutableLiveData()

    var isDataSaved : MutableLiveData<Boolean> = MutableLiveData()

    fun getData(): MutableLiveData<Boolean>{
        //first try to get from firebase
        mChurchRepository.getChurchesFromFirebase().subscribe {
            //them get the churches from database
            getChurchesFromRoom()
        }

        return isDataSaved
    }

    fun getChurches() =  mChurchList

    fun getEucharists()= mEucharistList

    private fun getChurchesFromRoom(){
        mChurchRepository.getChurchesFromRoom().subscribe { list ->
            mChurchList.value = list
            // And finally get the Eucharists
            getEucharistsFromFirebaseAndSave()
        }
    }
    private fun getEucharistsFromFirebaseAndSave(){

        mEucharistRepository.getEucharistsFromFirebaseAndSave().subscribe { eucharists ->
            mEucharistList.value = eucharists
            isDataSaved.postValue(true)
        }
    }

}


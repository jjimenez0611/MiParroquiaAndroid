package com.effeta.miparroquiaandroid.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.effeta.miparroquiaandroid.models.Eucharist
import com.effeta.miparroquiaandroid.repositories.EucharistRepository
import java.util.*

/**
 * Created by aulate on 14/3/18.
 */
class EucharistViewModel @javax.inject.Inject constructor(private val mEucharistRepository: EucharistRepository) : ViewModel() {

    private var mDays: MutableLiveData<List<Calendar>> = MutableLiveData()

    private var mEucharists: MutableLiveData<List<Eucharist>> = MutableLiveData()

    init {
        calculateDays()
    }

    fun getWeekDays() = mDays

    fun getEucharists(): MutableLiveData<List<Eucharist>> {
        mEucharistRepository.getEucharistsByParish().subscribe {
            mEucharists.postValue(it)
        }
        return mEucharists
    }

    fun getEucharistsByDay(day: String) {

    }

    private fun calculateDays() {
        val days = ArrayList<Calendar>()

        addDay(days, Calendar.MONDAY)
        addDay(days, Calendar.TUESDAY)
        addDay(days, Calendar.WEDNESDAY)
        addDay(days, Calendar.THURSDAY)
        addDay(days, Calendar.FRIDAY)
        addDay(days, Calendar.SATURDAY)
        addDay(days, Calendar.SUNDAY)

        mDays.postValue(days)
    }

    private fun addDay(list: ArrayList<Calendar>, dayOfWeek: Int) {
        val today = Calendar.getInstance()
        today.set(Calendar.DAY_OF_WEEK, dayOfWeek)
        list.add(today)
    }
}
package com.effeta.miparroquiaandroid.utils

import java.util.*

/**
 * Created by aulate on 15/3/18.
 */
object DayUtils {

    fun getDaysOfWeek(): List<Calendar> {
        val days = ArrayList<Calendar>()

        addDay(days, Calendar.MONDAY)
        addDay(days, Calendar.TUESDAY)
        addDay(days, Calendar.WEDNESDAY)
        addDay(days, Calendar.THURSDAY)
        addDay(days, Calendar.FRIDAY)
        addDay(days, Calendar.SATURDAY)
        addDay(days, Calendar.SUNDAY)

        return days
    }

    private fun addDay(list: ArrayList<Calendar>, dayOfWeek: Int) {
        val today = Calendar.getInstance()
        today.set(Calendar.DAY_OF_WEEK, dayOfWeek)
        list.add(today)
    }
}
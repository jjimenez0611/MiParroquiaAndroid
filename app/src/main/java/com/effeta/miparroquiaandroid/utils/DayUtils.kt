package com.effeta.miparroquiaandroid.utils

import org.joda.time.DateTime
import org.joda.time.DateTimeConstants
import java.util.*

/** -*- coding: utf-8 -*-
 * This file was created by
 * @Author: aulate
 * @Date:   15/3/18
 */
object DayUtils {

    private const val DAYS_FORWARD = 7
    private const val DAYS_BEFORE = -1


    fun getDaysOfWeek(): List<DateTime> {
        val days = ArrayList<DateTime>()
        for (day in DAYS_BEFORE..DAYS_FORWARD) {
            addPreviousDay(days, day)
        }
        return days
    }

    fun getWeek(): List<DateTime> {
        val days = ArrayList<DateTime>()

        addDay(days, DateTimeConstants.MONDAY)
        addDay(days, DateTimeConstants.TUESDAY)
        addDay(days, DateTimeConstants.WEDNESDAY)
        addDay(days, DateTimeConstants.THURSDAY)
        addDay(days, DateTimeConstants.FRIDAY)
        addDay(days, DateTimeConstants.SATURDAY)
        addDay(days, DateTimeConstants.SUNDAY)

        return days
    }

    private fun addDay(list: ArrayList<DateTime>, dayOfWeek: Int) {
        val today = DateTime()
        list.add(today.withDayOfWeek(dayOfWeek))
    }

    private fun addPreviousDay(list: ArrayList<DateTime>, days: Int) {
        val today = DateTime()
        if (days < 0) {
            list.add(today.minusDays(Math.abs(days)))
        }
        if (days == 0) {
            list.add(today)
        }
        if (days > 0) {
            list.add(today.plusDays(days))
        }

    }
}
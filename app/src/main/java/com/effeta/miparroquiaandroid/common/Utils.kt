package com.effeta.miparroquiaandroid.common

import android.annotation.SuppressLint
import android.view.View
import org.joda.time.DateTime
import java.text.SimpleDateFormat
import java.util.*


@SuppressLint("SimpleDateFormat")
        /** -*- coding: utf-8 -*-
         * This file was created by
         * @Author: aulate
         * @Date:   6/2/18
         */

fun Date.toString(pattern: String): String {
    val simpleDateFormat = SimpleDateFormat(pattern)
    return simpleDateFormat.format(this)
}

@SuppressLint("SimpleDateFormat")
fun Calendar.toString(pattern: String): String {
    val simpleDateFormat = SimpleDateFormat(pattern)
    return simpleDateFormat.format(time)
}

fun DateTime.getStartDay(): Date {
    return withHourOfDay(0).withMinuteOfHour(0).withSecondOfMinute(0).toDate()
}

fun DateTime.getEndDay(): Date {
    return withHourOfDay(23).withMinuteOfHour(59).withSecondOfMinute(59).toDate()
}

fun View.changeBackground(resId: Int) {
    val pL = paddingLeft
    val pT = paddingTop
    val pR = paddingRight
    val pB = paddingBottom

    setBackgroundResource(resId)
    setPadding(pL, pT, pR, pB)
}

fun Any.logName(): String {
    val str = this.javaClass.simpleName
    if (str.length > 35 - "sl_".length) {
        return "sl_" + str.substring(0, 35 - "sl_".length - 1)
    }

    return "sl_$str"
}
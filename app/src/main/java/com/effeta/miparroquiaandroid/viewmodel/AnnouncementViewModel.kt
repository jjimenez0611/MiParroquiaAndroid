package com.effeta.miparroquiaandroid.viewmodel

import com.effeta.miparroquiaandroid.R
import com.effeta.miparroquiaandroid.common.DATE_FORMAT
import com.effeta.miparroquiaandroid.models.Announcement

/**
 * Created by aulate on 19/3/18.
 */
class AnnouncementViewModel(private val announcement: Announcement) {

    val title = announcement.mTitle

    val description = announcement.mDescription

    val publishedAt = announcement.mPublishedAt.toString(DATE_FORMAT)!!

    fun getBackground(): Int {
        return when (announcement.mType) {
            "0" ->
                R.color.colorSecondaryAccent

            else ->
                R.color.colorAccent

        }
    }

    fun getAnnouncementType(types: Array<String>) = types[announcement.mType.toInt()]


}
package com.effeta.miparroquiaandroid.views.adapters

import android.content.res.Resources
import android.support.v4.content.ContextCompat
import android.view.View
import com.effeta.miparroquiaandroid.R
import com.effeta.miparroquiaandroid.common.*
import com.effeta.miparroquiaandroid.utils.RemoteConfig
import kotlinx.android.synthetic.main.item_day.view.*
import org.joda.time.DateTime
import java.util.*
import javax.inject.Inject

/** -*- coding: utf-8 -*-
 * This file was created by
 * @Author: aulate
 * @Date:   14/3/18
 */
class DayAdapter @Inject constructor() : BaseAdapter<DateTime, DayAdapter.DayViewHolder>() {

    override var mItemLayout = R.layout.item_day

    var mSelectedPosition: Int? = null

    override fun instantiateViewHolder(view: View): DayViewHolder {
        return DayViewHolder(view)
    }

    override fun getEmptyString(resources: Resources): String {
        return resources.getString(R.string.not_found, resources.getString(R.string.title_days).decapitalize())
    }

    inner class DayViewHolder(itemView: View?) : BaseAdapter.BaseViewHolder<DateTime>(itemView) {
        override fun showItem(item: DateTime) {
            itemView.item_day_name.text = item.toString(DAY_OF_WEEK).capitalize()
            itemView.item_day_date.text = item.toString(DATE_AND_MONTH).capitalize()

            checkSelectedPosition(item)
        }

        private fun checkSelectedPosition(item: DateTime) {
            if (mSelectedPosition == null) {
                val today = Calendar.getInstance()
                today.time = Date()
                if (item.toString(DATE_FORMAT) == today.toString(DATE_FORMAT)) {
                    mSelectedPosition = adapterPosition
                }
            }

            changeLayoutState()
        }

        private fun changeLayoutState() {
            if (mSelectedPosition == adapterPosition) {
                itemView.item_day_background.changeBackground(RemoteConfig.getPrimaryColor())
                itemView.item_day_name.setTextColor(ContextCompat.getColor(itemView.context, android.R.color.white))
                itemView.item_day_date.setTextColor(ContextCompat.getColor(itemView.context, android.R.color.white))
            } else {
                itemView.item_day_background.changeBackground(RemoteConfig.getPrimaryLightColor())
                itemView.item_day_name.setTextColor(ContextCompat.getColor(itemView.context, R.color.colorPrimaryText))
                itemView.item_day_date.setTextColor(ContextCompat.getColor(itemView.context, R.color.colorPrimaryText))
            }
        }
    }

}
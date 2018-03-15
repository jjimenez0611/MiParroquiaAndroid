package com.effeta.miparroquiaandroid.views.adapters

import android.support.v4.content.ContextCompat
import android.view.View
import com.effeta.miparroquiaandroid.R
import com.effeta.miparroquiaandroid.common.*
import kotlinx.android.synthetic.main.item_day.view.*
import java.util.*
import javax.inject.Inject

/**
 * Created by aulate on 14/3/18.
 */
class DayAdapter @Inject constructor() : BaseAdapter<Calendar, DayAdapter.DayViewHolder>() {

    override var mItemLayout = R.layout.item_day

    var mSelectedPosition: Int? = null

    override fun instantiateViewHolder(view: View): DayViewHolder {
        return DayViewHolder(view)
    }

    inner class DayViewHolder(itemView: View?) : BaseAdapter.BaseViewHolder<Calendar>(itemView) {
        override fun showItem(item: Calendar) {
            itemView.item_day.text = item.toString(DAY_OF_WEEK).capitalize()
            itemView.item_date.text = item.toString(DATE_AND_MONTH).capitalize()

            checkSelectedPosition(item)
        }

        private fun checkSelectedPosition(item: Calendar) {
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
                itemView.item_background.changeBackground(R.color.colorPrimaryDark)
                itemView.item_day.setTextColor(ContextCompat.getColor(itemView.context, android.R.color.white))
                itemView.item_date.setTextColor(ContextCompat.getColor(itemView.context, android.R.color.white))
            } else {
                itemView.item_background.changeBackground(R.color.colorPrimaryLight)
                itemView.item_day.setTextColor(ContextCompat.getColor(itemView.context, R.color.colorPrimaryText))
                itemView.item_date.setTextColor(ContextCompat.getColor(itemView.context, R.color.colorPrimaryText))
            }
        }
    }

}
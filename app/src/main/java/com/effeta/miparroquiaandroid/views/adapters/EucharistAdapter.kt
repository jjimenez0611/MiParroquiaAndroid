package com.effeta.miparroquiaandroid.views.adapters

import android.view.View
import com.effeta.miparroquiaandroid.R
import com.effeta.miparroquiaandroid.common.HOUR
import com.effeta.miparroquiaandroid.common.toString
import com.effeta.miparroquiaandroid.models.Eucharist
import kotlinx.android.synthetic.main.item_eucharist.view.*
import javax.inject.Inject

/**
 * Created by aulate on 15/3/18.
 */
class EucharistAdapter @Inject constructor() : BaseAdapter<Eucharist, EucharistAdapter.EucharistViewHolder>() {

    override var mItemLayout = R.layout.item_eucharist

    override fun instantiateViewHolder(view: View): EucharistViewHolder {
        return EucharistViewHolder(view)
    }

    inner class EucharistViewHolder(itemView: View?) : BaseAdapter.BaseViewHolder<Eucharist>(itemView) {
        override fun showItem(item: Eucharist) {
            itemView.textview_hour.text = item.mHour.toString(HOUR)
            itemView.textview_church.text = item.mChurch
        }
    }
}
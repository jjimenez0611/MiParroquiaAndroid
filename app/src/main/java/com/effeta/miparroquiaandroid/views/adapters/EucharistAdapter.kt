package com.effeta.miparroquiaandroid.views.adapters

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.Observer
import android.content.res.Resources
import android.view.View
import com.effeta.miparroquiaandroid.R
import com.effeta.miparroquiaandroid.models.Eucharist
import com.effeta.miparroquiaandroid.viewmodel.EucharistViewModel
import kotlinx.android.synthetic.main.item_eucharist.view.*
import javax.inject.Inject

/** -*- coding: utf-8 -*-
 * This file was created by
 * @Author: aulate
 * @Date:   15/3/18
 */
class EucharistAdapter @Inject constructor(private val viewmodel: EucharistViewModel) : BaseAdapter<Eucharist, EucharistAdapter.EucharistViewHolder>() {

    override var mItemLayout = R.layout.item_eucharist

    var mLifeCycleOwner: LifecycleOwner? = null

    override fun instantiateViewHolder(view: View): EucharistViewHolder {
        return EucharistViewHolder(view)
    }

    override fun getEmptyString(resources: Resources): String {
        return resources.getString(R.string.not_found_filtered,
                resources.getString(R.string.title_eucharist).decapitalize(),
                resources.getString(R.string.filter_day))
    }

    inner class EucharistViewHolder(itemView: View?) : BaseAdapter.BaseViewHolder<Eucharist>(itemView) {
        override fun showItem(item: Eucharist) {
            viewmodel.eucharist = item
            mLifeCycleOwner?.let {
                viewmodel.hour.observe(it, Observer {
                    itemView.textview_hour.text = it
                })
                viewmodel.church.observe(it, Observer {
                    itemView.textview_church.text = it
                })
                viewmodel.priest.observe(it, Observer {
                    itemView.textview_priest.visibility = View.VISIBLE
                    itemView.textview_priest.text = it
                })
            }
        }
    }
}
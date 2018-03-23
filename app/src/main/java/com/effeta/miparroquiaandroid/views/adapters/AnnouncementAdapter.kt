package com.effeta.miparroquiaandroid.views.adapters

import android.content.res.Resources
import android.view.View
import com.effeta.miparroquiaandroid.R
import com.effeta.miparroquiaandroid.common.changeBackground
import com.effeta.miparroquiaandroid.models.Announcement
import com.effeta.miparroquiaandroid.viewmodel.AnnouncementViewModel
import kotlinx.android.synthetic.main.item_announcement.view.*
import javax.inject.Inject

/** -*- coding: utf-8 -*-
 * This file was created by
 * @Author: aulate
 * @Date:   20/2/18
 */
class AnnouncementAdapter @Inject constructor() : BaseAdapter<Announcement, AnnouncementAdapter.AnnouncementViewHolder>() {

    override var mItemLayout = R.layout.item_announcement

    override fun instantiateViewHolder(view: View): AnnouncementViewHolder {
        return AnnouncementViewHolder(view)
    }

    override fun getEmptyString(resources: Resources): String {
        return resources.getString(
                R.string.not_found_filtered,
                resources.getString(R.string.title_announcements).decapitalize(),
                resources.getString(R.string.filter_days).decapitalize())
    }

    inner class AnnouncementViewHolder(itemView: View?) : BaseViewHolder<Announcement>(itemView) {
        override fun showItem(item: Announcement) {
            val viewModel = AnnouncementViewModel(item)

            itemView.highlight_item_type.changeBackground(viewModel.getBackground())
            itemView.item_title.text = viewModel.title
            itemView.item_type.text = viewModel.getAnnouncementType(itemView.resources.getStringArray(R.array.announcement_types))
            itemView.item_description.text = viewModel.description
            itemView.item_published_at.text = viewModel.publishedAt
        }
    }
}
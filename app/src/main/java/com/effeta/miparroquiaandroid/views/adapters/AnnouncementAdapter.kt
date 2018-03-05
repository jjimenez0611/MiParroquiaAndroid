package com.effeta.miparroquiaandroid.views.adapters

import android.view.View
import com.effeta.miparroquiaandroid.R
import com.effeta.miparroquiaandroid.common.DATE_FORMAT
import com.effeta.miparroquiaandroid.common.changeBackground
import com.effeta.miparroquiaandroid.common.toString
import com.effeta.miparroquiaandroid.models.Announcement
import kotlinx.android.synthetic.main.item_announcement.view.*
import javax.inject.Inject

/**
 * Created by aulate on 20/2/18.
 */
class AnnouncementAdapter @Inject constructor() : BaseAdapter<Announcement, AnnouncementAdapter.AnnouncementViewHolder>() {

    override var mItemLayout = R.layout.item_announcement

    var mAnnouncementTypes: Array<String>? = null

    override fun instantiateViewHolder(view: View): AnnouncementViewHolder {
        return AnnouncementViewHolder(view)
    }

    inner class AnnouncementViewHolder(itemView: View?) : BaseViewHolder<Announcement>(itemView) {
        override fun showItem(item: Announcement) {

            if (item.mType.toInt() == 0)
                itemView.changeBackground(R.drawable.bg_parish_announcement)

            itemView.item_title.text = item.mTitle
            itemView.item_description.text = item.mDescription
            itemView.item_published_at.text = item.mPublishedAt.toString(DATE_FORMAT)
        }
    }
}
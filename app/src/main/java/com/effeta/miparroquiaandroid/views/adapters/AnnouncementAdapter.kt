package com.effeta.miparroquiaandroid.views.adapters

import android.view.View
import com.effeta.miparroquiaandroid.R
import com.effeta.miparroquiaandroid.common.toString
import com.effeta.miparroquiaandroid.models.Announcement
import kotlinx.android.synthetic.main.item_announcement.view.*

/**
 * Created by aulate on 20/2/18.
 */
object AnnouncementAdapter : BaseAdapter<Announcement, AnnouncementAdapter.AnnouncementViewHolder>() {

    override var mItemLayout = R.layout.item_announcement

    override fun instantiateViewHolder(view: View): AnnouncementViewHolder {
        return AnnouncementViewHolder(view)
    }

    class AnnouncementViewHolder(itemView: View?) : BaseViewHolder<Announcement>(itemView) {
        override fun showItem(item: Announcement) {
            itemView.item_title.text = item.mTitle
            itemView.item_description.text = item.mDescription
            itemView.item_published_at.text = item.mPublishedAt.toString("dd-MM-yyyy")
            itemView.item_type.text = item.mType
        }
    }
}
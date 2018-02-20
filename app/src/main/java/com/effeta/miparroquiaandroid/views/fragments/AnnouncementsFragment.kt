package com.effeta.miparroquiaandroid.views.fragments

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.Toast
import com.effeta.miparroquiaandroid.R
import com.effeta.miparroquiaandroid.common.BaseFragment
import com.effeta.miparroquiaandroid.models.Announcement
import com.effeta.miparroquiaandroid.viewmodel.AnnouncementViewModel
import com.effeta.miparroquiaandroid.views.adapters.AnnouncementAdapter
import kotlinx.android.synthetic.main.fragment_announcements.*

class AnnouncementsFragment : BaseFragment() {
    override val mLayout: Int = R.layout.fragment_announcements

    private lateinit var mAnnouncementViewModel: AnnouncementViewModel

    private var mAnnouncementAdapter = AnnouncementAdapter

    override fun initializeViewModels() {
        mAnnouncementViewModel = ViewModelProviders.of(this).get(AnnouncementViewModel::class.java)
    }

    override fun initializeUI() {
    }

    override fun observeLiveData(isNewActivity: Boolean) {
        mAnnouncementViewModel.isLoading.observe(this, Observer { isLoading ->
            if (isLoading!!) {
                progress.visibility = View.VISIBLE
            } else {
                progress.visibility = View.GONE
            }
        })
        mAnnouncementViewModel.isError.observe(this, Observer {
            Toast.makeText(this@AnnouncementsFragment.context, "Error al cargar los anuncios.", Toast.LENGTH_SHORT).show()
        })


        mAnnouncementViewModel.announcementList.observe(this, Observer {
            Toast.makeText(this@AnnouncementsFragment.context, "Anuncios cargados.", Toast.LENGTH_SHORT).show()
            showAnnouncements(it)
        })


        mAnnouncementViewModel.getAnnouncements()
    }

    private fun showAnnouncements(list: List<Announcement>?) {
        mAnnouncementAdapter.list = list!!

        announcement_list.adapter = mAnnouncementAdapter
        announcement_list.layoutManager = LinearLayoutManager(this@AnnouncementsFragment.context)

        mAnnouncementAdapter.notifyDataSetChanged()
    }
}

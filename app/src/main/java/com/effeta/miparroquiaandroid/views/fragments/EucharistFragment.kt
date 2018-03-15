package com.effeta.miparroquiaandroid.views.fragments

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.widget.LinearSnapHelper
import android.view.View
import com.effeta.miparroquiaandroid.R
import com.effeta.miparroquiaandroid.common.BaseFragment
import com.effeta.miparroquiaandroid.common.ItemClickListener
import com.effeta.miparroquiaandroid.models.Eucharist
import com.effeta.miparroquiaandroid.viewmodel.EucharistViewModel
import com.effeta.miparroquiaandroid.views.adapters.DayAdapter
import com.effeta.miparroquiaandroid.views.adapters.EucharistAdapter
import kotlinx.android.synthetic.main.fragment_eucharist.*
import java.util.*
import javax.inject.Inject


class EucharistFragment : BaseFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var mEucharistViewModel: EucharistViewModel

    @Inject
    lateinit var mDayAdapter: DayAdapter

    @Inject
    lateinit var mEucharistAdapter: EucharistAdapter

    override val mLayout: Int = R.layout.fragment_eucharist

    override fun initializeViewModels() {
        mEucharistViewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(EucharistViewModel::class.java)
    }

    override fun initializeUI() {
        attachClickListener()
    }

    override fun observeLiveData(isNewActivity: Boolean) {
        getViewLifecycleOwner()?.let { lifecycleOwner ->
            mEucharistViewModel.getWeekDays().observe(lifecycleOwner, Observer { days ->
                showDays(days)
                mEucharistViewModel.getEucharists().observe(lifecycleOwner, Observer { eucharists ->
                    content.visibility = View.VISIBLE
                    progress.visibility = View.GONE

                    showEucharists(eucharists!!)
                })
            })
        }
    }

    private fun showDays(list: List<Calendar>?) {
        mDayAdapter.mList = list!!

        recyclerview_day_list.adapter = mDayAdapter
        mDayAdapter.notifyDataSetChanged()
        mDayAdapter.mSelectedPosition?.let {
            recyclerview_day_list.smoothScrollToPosition(it)
        }
    }

    private fun showEucharists(list: List<Eucharist>) {
        mEucharistAdapter.mList = list

        recyclerview_eucharist_list.adapter = mEucharistAdapter
        mEucharistAdapter.notifyDataSetChanged()
    }

    private fun attachClickListener() {
        val snapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(recyclerview_day_list)

        val clickListener = ItemClickListener(recyclerview_day_list, object : ItemClickListener.ClickListener {
            override fun onItemClick(view: View, position: Int) {
                mDayAdapter.mSelectedPosition = position
                mDayAdapter.notifyDataSetChanged()
                recyclerview_day_list.smoothScrollToPosition(position)
//                mEucharistViewModel.getEucharists(mDayAdapter.mList[position].toString(DATE_FORMAT))
            }

            override fun onLongItemClick(view: View?, position: Int) {}
        })

        recyclerview_day_list.addOnItemTouchListener(clickListener)
    }
}

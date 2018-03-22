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
import com.effeta.miparroquiaandroid.viewmodel.EucharistListViewModel
import com.effeta.miparroquiaandroid.views.adapters.DayAdapter
import com.effeta.miparroquiaandroid.views.adapters.EucharistAdapter
import kotlinx.android.synthetic.main.fragment_eucharist.*
import java.util.*
import javax.inject.Inject


class EucharistFragment : BaseFragment(), ItemClickListener.ClickListener {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var mEucharistListViewModel: EucharistListViewModel

    @Inject
    lateinit var mDayAdapter: DayAdapter

    @Inject
    lateinit var mEucharistAdapter: EucharistAdapter

    override val mLayout: Int = R.layout.fragment_eucharist

    override fun initializeViewModels() {
        mEucharistListViewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(EucharistListViewModel::class.java)
    }

    override fun initializeUI() {
        mEucharistAdapter.mLifeCycleOwner = getViewLifecycleOwner()
        attachClickListeners()
    }

    override fun observeLiveData(isNewActivity: Boolean) {
        getViewLifecycleOwner()?.let { lifecycleOwner ->
            showDays(mEucharistListViewModel.getWeekDays())
            mEucharistListViewModel.getEucharists().observe(lifecycleOwner, Observer { eucharists ->
                showEucharists(eucharists!!)
                showContent()
            })
        }
    }

    override fun onItemClick(view: View, position: Int) {
        when (view.id) {
            R.id.item_day -> {
                if (mDayAdapter.mSelectedPosition != position) {
                    mDayAdapter.mSelectedPosition = position
                    mDayAdapter.notifyDataSetChanged()
                    recyclerview_day_list.smoothScrollToPosition(position)
//                mEucharistListViewModel.getEucharists(mDayAdapter.mList[position].toString(DATE_FORMAT))
                }
            }
            R.id.item_eucharist -> {
            }
        }
    }

    override fun onLongItemClick(view: View?, position: Int) {}

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

    private fun attachClickListeners() {
        val snapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(recyclerview_day_list)
        val dayClickListener = ItemClickListener(recyclerview_day_list, this)
        recyclerview_day_list.addOnItemTouchListener(dayClickListener)

        val eucharistClickListener = ItemClickListener(recyclerview_eucharist_list, this)
        recyclerview_eucharist_list.addOnItemTouchListener(eucharistClickListener)
    }

    private fun showContent() {
        content.visibility = View.VISIBLE
        progress.visibility = View.GONE
    }

}

package com.effeta.miparroquiaandroid.views.fragments

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.widget.LinearSnapHelper
import android.view.View
import com.effeta.miparroquiaandroid.R
import com.effeta.miparroquiaandroid.common.BaseFragment
import com.effeta.miparroquiaandroid.common.EXTRA_CHURCH
import com.effeta.miparroquiaandroid.common.ItemClickListener
import com.effeta.miparroquiaandroid.models.Eucharist
import com.effeta.miparroquiaandroid.viewmodel.EucharistListViewModel
import com.effeta.miparroquiaandroid.views.activities.ChurchDetailActivity
import com.effeta.miparroquiaandroid.views.adapters.DayAdapter
import com.effeta.miparroquiaandroid.views.adapters.EucharistAdapter
import kotlinx.android.synthetic.main.fragment_eucharist.*
import org.jetbrains.anko.intentFor
import org.joda.time.DateTime
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
            mEucharistListViewModel.getEucharistsByDay(DateTime()).observe(lifecycleOwner, Observer { eucharists ->
                showEucharists(eucharists!!)
                showContent()
            })
        }
    }

    override fun onItemClick(view: View, position: Int) {
        when (view.id) {
            R.id.item_day -> {
                if (mDayAdapter.mSelectedPosition != position) {
                    showProgress()
                    mDayAdapter.mSelectedPosition = position
                    mDayAdapter.notifyDataSetChanged()
                    recyclerview_day_list.smoothScrollToPosition(position)
                    mEucharistListViewModel.getEucharistsByDay(mDayAdapter[position])
                }
            }
            R.id.item_eucharist -> {
                startActivity(context.intentFor<ChurchDetailActivity>(Pair(EXTRA_CHURCH, mEucharistAdapter[position].mChurch)))
            }
        }
    }

    override fun onLongItemClick(view: View?, position: Int) {}

    private fun showDays(list: List<DateTime>?) {
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
        recyclerview_eucharist_list.visibility = View.VISIBLE
        progress_eucharists.visibility = View.GONE
    }

    private fun showProgress() {
        recyclerview_eucharist_list.visibility = View.GONE
        progress_eucharists.visibility = View.VISIBLE
    }
}

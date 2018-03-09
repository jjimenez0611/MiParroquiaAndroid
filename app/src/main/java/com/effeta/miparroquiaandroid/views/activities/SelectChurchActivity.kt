package com.effeta.miparroquiaandroid.views.activities

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import com.effeta.miparroquiaandroid.R
import com.effeta.miparroquiaandroid.common.BaseActivity
import com.effeta.miparroquiaandroid.models.Parish
import com.effeta.miparroquiaandroid.viewmodel.ParishViewModel
import kotlinx.android.synthetic.main.activity_select_church.*
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.toast
import javax.inject.Inject

/**
 * Created by aulate on 26/2/18.
 */
class SelectChurchActivity : BaseActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var mParishViewModel: ParishViewModel

    override val mLayout: Int = R.layout.activity_select_church

    private lateinit var mAdapter: ArrayAdapter<Parish>

    private var mSelectedParishPos: Int = -1

    override fun initializeViewModels() {
        mParishViewModel = ViewModelProviders.of(this, viewModelFactory).get(ParishViewModel::class.java)
    }

    override fun initializeUI() {
        mAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item)

        spinner_choose_parish.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                mSelectedParishPos = position
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                toast(R.string.error_nothing_selected)
            }
        }

        btn_parish_selected.setOnClickListener {
            if (mSelectedParishPos != -1) {
                val p = mAdapter.getItem(mSelectedParishPos)
                mParishViewModel.storeSelectedParish(p.mKey)
                startActivity(intentFor<MainActivity>())
            }
        }
    }

    override fun observeLiveData() {
        mParishViewModel.getParishes().observe(this, Observer {
            showContent()
            setupSpinner(parishes = it!!)
        })
    }

    private fun setupSpinner(parishes: List<Parish>) {
        mAdapter.clear()
        mAdapter.addAll(parishes)
        spinner_choose_parish.adapter = mAdapter
    }

    private fun showContent() {
        progress.visibility = View.GONE
        content.visibility = View.VISIBLE
    }

}
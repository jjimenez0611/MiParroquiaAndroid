package com.effeta.miparroquiaandroid.views.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Created by aulate on 20/2/18.
 */
abstract class BaseAdapter<T, VH : BaseAdapter.BaseViewHolder<T>?> : RecyclerView.Adapter<VH>() {
    private val EMPTY = 1

    abstract var mItemLayout: Int

    lateinit var mList: List<T>

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): VH {
        val view = LayoutInflater.from(parent!!.context).inflate(mItemLayout, parent, false)
        return instantiateViewHolder(view)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder?.showItem(mList[position])
    }

    override fun getItemCount(): Int {
//        if (mList.isEmpty())
//            return 1
        return mList.size
    }

    abstract fun instantiateViewHolder(view: View): VH

    abstract class BaseViewHolder<T>(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        abstract fun showItem(item: T)
    }
}
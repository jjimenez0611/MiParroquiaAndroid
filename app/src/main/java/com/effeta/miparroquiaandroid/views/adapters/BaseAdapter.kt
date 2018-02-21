package com.effeta.miparroquiaandroid.views.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.effeta.miparroquiaandroid.R

/**
 * Created by aulate on 20/2/18.
 */
abstract class BaseAdapter<T, VH : BaseAdapter.BaseViewHolder<T>?> : RecyclerView.Adapter<VH>() {
    private val EMPTY = 1

    abstract var mItemLayout: Int

    lateinit var list: List<T>

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): VH {
        val view = LayoutInflater.from(parent!!.context).inflate(R.layout.item_announcement, parent, false)
        return instantiateViewHolder(view)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder?.showItem(list[position])
    }

    override fun getItemCount(): Int {
//        if (list.isEmpty())
//            return 1
        return list.size
    }

    abstract fun instantiateViewHolder(view: View): VH

    abstract class BaseViewHolder<T>(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        abstract fun showItem(item: T)
    }
}
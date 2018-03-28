package com.effeta.miparroquiaandroid.views.adapters

import android.content.res.Resources
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.effeta.miparroquiaandroid.R
import kotlinx.android.synthetic.main.item_empty.view.*

/** -*- coding: utf-8 -*-
 * This file was created by
 * @Author: aulate
 * @Date:   20/2/18
 */
@Suppress("UNCHECKED_CAST")
abstract class BaseAdapter<T, VH : BaseAdapter.BaseViewHolder<T>?> : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val EMPTY = 1

    abstract var mItemLayout: Int

    lateinit var mList: List<T>

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == EMPTY) {
            return EmptyViewHolder(LayoutInflater.from(parent!!.context).inflate(R.layout.item_empty, parent, false))
        }
        val view = LayoutInflater.from(parent!!.context).inflate(mItemLayout, parent, false)
        return instantiateViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        if (holder is BaseViewHolder<*>) {
            holder as VH
            holder.showItem(mList[position])
        }
        if (holder is BaseAdapter<*, *>.EmptyViewHolder) {
            holder.showEmpty()
        }
    }

    override fun getItemCount(): Int {
        if (mList.isEmpty())
            return 1
        return mList.size
    }

    override fun getItemViewType(position: Int): Int {
        if (mList.isEmpty() && position == 0) {
            return EMPTY
        }
        return super.getItemViewType(position)
    }

    abstract fun instantiateViewHolder(view: View): RecyclerView.ViewHolder

    abstract class BaseViewHolder<T>(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        abstract fun showItem(item: T)
    }

    protected abstract fun getEmptyString(resources: Resources): String

    operator fun get(position: Int): T {
        return mList[position]
    }

    inner class EmptyViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        fun showEmpty() {
            itemView.empty_label.text = getEmptyString(itemView.resources)
        }
    }
}
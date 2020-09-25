package com.gp.tawk.core.utils

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseRecyclerViewAdapter<T> (
    open var items: MutableList<T>
) : RecyclerView.Adapter<BaseRecyclerViewAdapter<T>.ViewHolder>() {



    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view)
    abstract fun onBindViewHolder(view: View, item: T, position: Int)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        onBindViewHolder(holder.view, items[position], position)
    }

    override fun getItemCount(): Int = items.size


//    override fun getlastId(pos:Int): T = items[pos]


}

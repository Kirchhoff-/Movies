package com.kirchhoff.movies.ui.utils.recyclerView

import android.view.View
import androidx.annotation.CallSuper
import androidx.recyclerview.widget.RecyclerView

abstract class BaseRecyclerAdapter<VH : BaseVH<T>, T>(
    private val items: MutableList<T> = mutableListOf(),
    private val itemClickListener: OnItemClickListener<T>? = null
) : RecyclerView.Adapter<VH>() {

    private val internalListener = View.OnClickListener { view: View -> Unit
        itemClickListener?.let {
            val position = view.tag as Int
            val item = items[position]
            itemClickListener.onItemClick(item)
        }
    }

    @CallSuper
    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.itemView.tag = position
        holder.itemView.setOnClickListener(internalListener)
        holder.bind(items[position])
    }

    override fun getItemCount() = items.size

    interface OnItemClickListener<T> {
        fun onItemClick(item: T)
    }
}

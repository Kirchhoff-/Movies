package com.kirchhoff.movies.core.ui.recyclerview

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseVH<T>(itemView: View) : RecyclerView.ViewHolder(itemView) {
    abstract fun bind(item: T)
}

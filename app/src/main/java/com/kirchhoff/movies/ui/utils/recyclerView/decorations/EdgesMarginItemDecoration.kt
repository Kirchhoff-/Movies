package com.kirchhoff.movies.ui.utils.recyclerView.decorations

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class EdgesMarginItemDecoration(private val edgesMargin: Int) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        with(outRect) {
            val position = parent.getChildAdapterPosition(view)
            when (position) {
                0 -> {
                    left = edgesMargin
                    right = edgesMargin / 2
                }
                parent.adapter!!.itemCount - 1 -> {
                    right = edgesMargin
                    left = edgesMargin / 2
                }
                else -> {
                    left = edgesMargin / 2
                    right = edgesMargin / 2
                }
            }
        }
    }
}

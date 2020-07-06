package com.kirchhoff.movies.ui.utils.recyclerView.decorations

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class GridMarginItemDecoration(
    private val spanCount: Int = 0,
    private val topMargin: Int = 0,
    private val bottomMargin: Int = 0,
    private val edgesMargin: Int = 0
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect, view: View,
        parent: RecyclerView, state: RecyclerView.State
    ) {
        with(outRect) {
            top = if (spanCount > 0) {
                if (parent.getChildAdapterPosition(view) < spanCount) {
                    topMargin
                } else {
                    topMargin / 2
                }
            } else {
                topMargin
            }

            bottom = bottomMargin
            left = edgesMargin
            right = edgesMargin
        }
    }
}
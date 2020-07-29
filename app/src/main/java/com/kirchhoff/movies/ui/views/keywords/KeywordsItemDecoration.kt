package com.kirchhoff.movies.ui.views.keywords

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class KeywordsItemDecoration(
    private val topMargin: Int = 0,
    private val bottomMargin: Int = 0,
    private val edgesMargin: Int = 0,
    private val firstItemMarginEnabled: Boolean
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        with(outRect) {
            top = topMargin
            bottom = bottomMargin
            right = edgesMargin

            left = if (firstItemMarginEnabled && parent.getChildAdapterPosition(view) == 0) {
                edgesMargin / 2
            } else {
                0
            }
        }
    }
}

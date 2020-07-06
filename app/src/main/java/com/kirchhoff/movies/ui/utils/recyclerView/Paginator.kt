package com.kirchhoff.movies.ui.utils.recyclerView

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class Paginator(
    var isLoading: Boolean = false,
    private val loadMore: (Int) -> Unit,
    private val threshold: Int = DEFAULT_THRESHOLD,
    var totalPages: Int = 0
) : RecyclerView.OnScrollListener() {

    private var currentPage = 1

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        val layoutManager = recyclerView.layoutManager
        layoutManager?.let {
            if (isLoading || currentPage >= totalPages) return

            val totalItemCount = it.itemCount
            val visibleItemCount = when (layoutManager) {
                is LinearLayoutManager -> layoutManager.findLastVisibleItemPosition()
                is GridLayoutManager -> layoutManager.findLastVisibleItemPosition()
                else -> throw IllegalStateException("Not supported LayoutManager type =${layoutManager::class.java}")
            }

            if ((visibleItemCount + threshold) >= totalItemCount) {
                loadMore(++currentPage)
                isLoading = true
            }
        }
    }

    companion object {
        private const val DEFAULT_THRESHOLD = 10
    }
}
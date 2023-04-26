package com.kirchhoff.movies.ui.screens.main.tvs

import com.kirchhoff.movies.R
import com.kirchhoff.movies.core.data.UITv
import com.kirchhoff.movies.core.ui.paginated.PaginatedScreenFragment
import com.kirchhoff.movies.core.ui.paginated.UIPaginated
import com.kirchhoff.movies.core.ui.recyclerview.adapter.BaseRecyclerViewAdapter
import com.kirchhoff.movies.ui.screens.core.tvs.adapter.TvsListAdapter
import org.koin.android.viewmodel.ext.android.viewModel

class TvsFragment : PaginatedScreenFragment<UITv, UIPaginated<UITv>>(),
    BaseRecyclerViewAdapter.OnItemClickListener<UITv> {

    override val vm by viewModel<TvsVM>()

    override val listAdapter = TvsListAdapter(this)

    override val configuration: Configuration = Configuration(
        threshold = THRESHOLD,
        spanCount = SPAN_COUNT,
        emptyResultText = R.string.empty_tw_shows,
        isToolbarVisible = false,
        toolbarTitle = ""
    )

    companion object {
        private const val SPAN_COUNT = 1
        private const val THRESHOLD = 3
    }

    override fun onItemClick(item: UITv) {
        router.openTvDetailsScreen(item)
    }
}

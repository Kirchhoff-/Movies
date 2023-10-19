package com.kirchhoff.movies.screen.tvshow.ui.screen.list

import android.content.Context
import com.kirchhoff.movies.core.data.UITv
import com.kirchhoff.movies.core.ui.paginated.PaginatedScreenFragment
import com.kirchhoff.movies.core.ui.paginated.UIPaginated
import com.kirchhoff.movies.core.ui.recyclerview.adapter.BaseRecyclerViewAdapter
import com.kirchhoff.movies.screen.tvshow.R
import com.kirchhoff.movies.screen.tvshow.tvShowModule
import com.kirchhoff.movies.screen.tvshow.ui.view.adapter.TvShowAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules

class TvShowListFragment : PaginatedScreenFragment<UITv, UIPaginated<UITv>>(),
    BaseRecyclerViewAdapter.OnItemClickListener<UITv> {

    override val vm by viewModel<TvShowListViewModel>()

    override val listAdapter = TvShowAdapter(this)

    override val configuration: Configuration = Configuration(
        threshold = THRESHOLD,
        spanCount = SPAN_COUNT,
        emptyResultText = R.string.empty_tw_shows,
        isToolbarVisible = false,
        toolbarTitle = ""
    )

    override fun onAttach(context: Context) {
        loadKoinModules(tvShowModule)
        super.onAttach(context)
    }

    override fun onDestroy() {
        unloadKoinModules(tvShowModule)
        super.onDestroy()
    }

    companion object {
        private const val SPAN_COUNT = 1
        private const val THRESHOLD = 3
    }

    override fun onItemClick(item: UITv) {
        router.openTvDetailsScreen(item)
    }
}

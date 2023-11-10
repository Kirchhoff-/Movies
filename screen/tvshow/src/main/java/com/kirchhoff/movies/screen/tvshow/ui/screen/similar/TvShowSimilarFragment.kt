package com.kirchhoff.movies.screen.tvshow.ui.screen.similar

import android.content.Context
import android.os.Bundle
import android.view.View
import com.kirchhoff.movies.core.data.UITv
import com.kirchhoff.movies.core.extensions.getParcelableExtra
import com.kirchhoff.movies.core.ui.paginated.PaginatedScreenFragment
import com.kirchhoff.movies.core.ui.paginated.UIPaginated
import com.kirchhoff.movies.core.ui.recyclerview.adapter.BaseRecyclerViewAdapter
import com.kirchhoff.movies.screen.tvshow.R
import com.kirchhoff.movies.screen.tvshow.ui.view.adapter.TvShowAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules
import org.koin.core.parameter.parametersOf

internal class TvShowSimilarFragment :
    PaginatedScreenFragment<UITv, UIPaginated<UITv>>(),
    BaseRecyclerViewAdapter.OnItemClickListener<UITv> {

    private val tvShow: UITv by lazy {
        requireArguments().getParcelableExtra(TV_SHOW_ARG)
            ?: error("Should provide UITv argument for fragment")
    }

    override val vm: TvShowSimilarViewModel by viewModel { parametersOf(tvShow.id) }

    override val listAdapter = TvShowAdapter(this)

    override val configuration: Configuration = Configuration(
        threshold = THRESHOLD,
        spanCount = SPAN_COUNT,
        emptyResultText = R.string.tv_show_empty_similar,
        isToolbarVisible = true,
        toolbarTitle = ""
    )

    override fun onAttach(context: Context) {
        loadKoinModules(tvShowSimilarModule)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        displayTitle(getString(R.string.tv_show_similar_to_format, tvShow.name))
    }

    override fun onDestroyView() {
        unloadKoinModules(tvShowSimilarModule)
        super.onDestroyView()
    }

    override fun onItemClick(item: UITv) {
        router.openTvDetailsScreen(item)
    }

    companion object {
        fun newInstance(tvShow: UITv): TvShowSimilarFragment =
            TvShowSimilarFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(TV_SHOW_ARG, tvShow)
                }
            }

        private const val TV_SHOW_ARG = "TV_SHOW_ARG"
        private const val SPAN_COUNT = 1
        private const val THRESHOLD = 3
    }
}

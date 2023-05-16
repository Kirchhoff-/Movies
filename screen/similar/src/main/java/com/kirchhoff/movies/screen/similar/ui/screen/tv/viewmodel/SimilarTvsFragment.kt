package com.kirchhoff.movies.screen.similar.ui.screen.tv.viewmodel

import android.content.Context
import android.os.Bundle
import android.view.View
import com.kirchhoff.movies.core.data.UITv
import com.kirchhoff.movies.core.ui.paginated.PaginatedScreenFragment
import com.kirchhoff.movies.core.ui.paginated.UIPaginated
import com.kirchhoff.movies.core.ui.recyclerview.adapter.BaseRecyclerViewAdapter
import com.kirchhoff.movies.screen.similar.R
import com.kirchhoff.movies.screen.similar.similarModule
import com.kirchhoff.movies.screen.similar.ui.screen.tv.viewmodel.adapter.SimilarTvsAdapter
import com.kirchhoff.movies.screen.similar.ui.screen.tv.viewmodel.viewmodel.SimilarTvsViewModel
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules
import org.koin.core.parameter.parametersOf

class SimilarTvsFragment : PaginatedScreenFragment<UITv, UIPaginated<UITv>>(),
    BaseRecyclerViewAdapter.OnItemClickListener<UITv> {

    override val vm: SimilarTvsViewModel by viewModel { parametersOf(requireArguments().getInt(TV_ID_ARG)) }

    override val listAdapter = SimilarTvsAdapter(this)

    override val configuration: Configuration = Configuration(
        threshold = THRESHOLD,
        spanCount = SPAN_COUNT,
        emptyResultText = R.string.empty_similar_tv_shows,
        isToolbarVisible = true,
        toolbarTitle = ""
    )

    override fun onAttach(context: Context) {
        loadKoinModules(similarModule)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        displayTitle(getString(R.string.similar_to_format, requireArguments().getString(TV_NAME_ARG)))
    }

    override fun onDestroy() {
        unloadKoinModules(similarModule)
        super.onDestroy()
    }

    override fun onItemClick(item: UITv) {
        router.openTvDetailsScreen(item)
    }

    companion object {
        fun newInstance(tvId: Int, tvName: String?): SimilarTvsFragment =
            SimilarTvsFragment().apply {
                arguments = Bundle().apply {
                    putInt(TV_ID_ARG, tvId)
                    putString(TV_NAME_ARG, tvName)
                }
            }

        private const val TV_ID_ARG = "TV_ID_ARG"
        private const val TV_NAME_ARG = "TV_NAME_ARG"
        private const val SPAN_COUNT = 1
        private const val THRESHOLD = 3
    }
}

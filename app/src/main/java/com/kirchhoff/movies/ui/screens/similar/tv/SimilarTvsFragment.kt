package com.kirchhoff.movies.ui.screens.similar.tv

import android.os.Bundle
import android.view.View
import com.kirchhoff.movies.R
import com.kirchhoff.movies.core.ui.paginated.PaginatedScreenFragment
import com.kirchhoff.movies.core.ui.paginated.UIPaginated
import com.kirchhoff.movies.core.ui.recyclerview.adapter.BaseRecyclerViewAdapter
import com.kirchhoff.movies.data.ui.main.UITv
import com.kirchhoff.movies.ui.screens.core.tvs.adapter.TvsListAdapter
import com.kirchhoff.movies.ui.screens.details.tv.TvDetailsFragment
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class SimilarTvsFragment : PaginatedScreenFragment<UITv, UIPaginated<UITv>>(),
    BaseRecyclerViewAdapter.OnItemClickListener<UITv> {

    override val vm: SimilarTvsVM by viewModel { parametersOf(requireArguments().getInt(TV_ID_ARG)) }

    override val listAdapter = TvsListAdapter(this)

    override val configuration: Configuration = Configuration(
        threshold = THRESHOLD,
        spanCount = SPAN_COUNT,
        emptyResultText = R.string.empty_similar_tv_shows,
        isToolbarVisible = true,
        toolbarTitle = ""
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        displayTitle(getString(R.string.similar_to_format, requireArguments().getString(TV_NAME_ARG)))
    }

    override fun onItemClick(item: UITv) {
        requireActivity().supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragmentContainer, TvDetailsFragment.newInstance(item))
            .addToBackStack(null)
            .commit()
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

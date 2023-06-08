package com.kirchhoff.movies.screen.movie.ui.screen.country

import android.content.Context
import android.os.Bundle
import android.view.View
import com.kirchhoff.movies.core.data.UIMovie
import com.kirchhoff.movies.core.ui.paginated.PaginatedScreenFragment
import com.kirchhoff.movies.core.ui.paginated.UIPaginated
import com.kirchhoff.movies.core.ui.recyclerview.adapter.BaseRecyclerViewAdapter
import com.kirchhoff.movies.core.ui.recyclerview.adapter.viewholder.BaseVH
import com.kirchhoff.movies.screen.movie.R
import com.kirchhoff.movies.screen.movie.ui.view.adapter.MovieAdapter
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules
import org.koin.core.parameter.parametersOf

class MovieCountryFragment : PaginatedScreenFragment<UIMovie, UIPaginated<UIMovie>>(),
    BaseRecyclerViewAdapter.OnItemClickListener<UIMovie> {

    override val vm: MovieCountryViewModel by viewModel {
        parametersOf(requireArguments().getString(COUNTRY_ID_ARG))
    }

    override val listAdapter: BaseRecyclerViewAdapter<BaseVH<UIMovie>, UIMovie> =
        MovieAdapter(this)

    override val configuration: Configuration = Configuration(
        threshold = THRESHOLD,
        spanCount = SPAN_COUNT,
        emptyResultText = R.string.movie_cant_get_movies_by_country,
        isToolbarVisible = true,
        toolbarTitle = ""
    )

    override fun onAttach(context: Context) {
        loadKoinModules(movieCountryModule)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        displayTitle(
            getString(
                R.string.movie_movies_from_country_format, requireArguments().getString(
                    COUNTRY_NAME_ARG
                )
            )
        )
    }

    override fun onDestroy() {
        unloadKoinModules(movieCountryModule)
        super.onDestroy()
    }

    override fun onItemClick(item: UIMovie) {
        router.openMovieDetailsScreen(item)
    }

    companion object {
        fun newInstance(countryId: String, countryName: String): MovieCountryFragment =
            MovieCountryFragment().apply {
                arguments = Bundle().apply {
                    putString(COUNTRY_ID_ARG, countryId)
                    putString(COUNTRY_NAME_ARG, countryName)
                }
            }

        private const val COUNTRY_ID_ARG = "COUNTRY_ID_ARG"
        private const val COUNTRY_NAME_ARG = "COUNTRY_NAME_ARG"
        private const val SPAN_COUNT = 1
        private const val THRESHOLD = SPAN_COUNT * 3
    }
}

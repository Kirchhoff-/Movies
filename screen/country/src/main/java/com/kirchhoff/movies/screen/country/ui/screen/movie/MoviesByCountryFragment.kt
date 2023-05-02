package com.kirchhoff.movies.screen.country.ui.screen.movie

import android.content.Context
import android.os.Bundle
import android.view.View
import com.kirchhoff.movies.core.data.UIMovie
import com.kirchhoff.movies.core.ui.paginated.PaginatedScreenFragment
import com.kirchhoff.movies.core.ui.paginated.UIPaginated
import com.kirchhoff.movies.core.ui.recyclerview.adapter.BaseRecyclerViewAdapter
import com.kirchhoff.movies.core.ui.recyclerview.adapter.viewholder.BaseVH
import com.kirchhoff.movies.screen.country.R
import com.kirchhoff.movies.screen.country.moviesByCountryModule
import com.kirchhoff.movies.screen.country.ui.screen.movie.adapter.MoviesByCountryAdapter
import com.kirchhoff.movies.screen.country.ui.screen.movie.viewmodel.MoviesByCountryViewModel
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules
import org.koin.core.parameter.parametersOf

class MoviesByCountryFragment : PaginatedScreenFragment<UIMovie, UIPaginated<UIMovie>>(),
    BaseRecyclerViewAdapter.OnItemClickListener<UIMovie> {

    override val vm: MoviesByCountryViewModel by viewModel {
        parametersOf(requireArguments().getString(COUNTRY_ID_ARG))
    }

    override val listAdapter: BaseRecyclerViewAdapter<BaseVH<UIMovie>, UIMovie> =
        MoviesByCountryAdapter(this)

    override val configuration: Configuration = Configuration(
        threshold = THRESHOLD,
        spanCount = SPAN_COUNT,
        emptyResultText = R.string.cant_get_movies_by_country,
        isToolbarVisible = true,
        toolbarTitle = ""
    )

    override fun onAttach(context: Context) {
        loadKoinModules(moviesByCountryModule)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        displayTitle(
            getString(
                R.string.movies_from_country_format, requireArguments().getString(
                    COUNTRY_NAME_ARG
                )
            )
        )
    }

    override fun onDestroy() {
        unloadKoinModules(moviesByCountryModule)
        super.onDestroy()
    }

    override fun onItemClick(item: UIMovie) {
        router.openMovieDetailsScreen(item)
    }

    companion object {
        fun newInstance(countryId: String, countryName: String): MoviesByCountryFragment =
            MoviesByCountryFragment().apply {
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

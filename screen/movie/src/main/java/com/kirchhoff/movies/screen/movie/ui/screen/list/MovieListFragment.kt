package com.kirchhoff.movies.screen.movie.ui.screen.list

import android.content.Context
import com.kirchhoff.movies.core.data.UIMovie
import com.kirchhoff.movies.core.ui.paginated.PaginatedScreenFragment
import com.kirchhoff.movies.core.ui.paginated.UIPaginated
import com.kirchhoff.movies.core.ui.recyclerview.adapter.BaseRecyclerViewAdapter
import com.kirchhoff.movies.screen.movie.R
import com.kirchhoff.movies.screen.movie.movieModule
import com.kirchhoff.movies.screen.movie.ui.view.adapter.MovieAdapter
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules

class MovieListFragment : PaginatedScreenFragment<UIMovie, UIPaginated<UIMovie>>(),
    BaseRecyclerViewAdapter.OnItemClickListener<UIMovie> {

    override val vm by viewModel<MovieListVM>()

    override val listAdapter = MovieAdapter(this)

    override val configuration: Configuration = Configuration(
        threshold = THRESHOLD,
        spanCount = SPAN_COUNT,
        emptyResultText = R.string.movie_empty_movies,
        isToolbarVisible = false,
        toolbarTitle = ""
    )

    override fun onAttach(context: Context) {
        loadKoinModules(movieModule)
        super.onAttach(context)
    }

    override fun onDestroy() {
        unloadKoinModules(movieModule)
        super.onDestroy()
    }

    companion object {
        private const val SPAN_COUNT = 2
        private const val THRESHOLD = SPAN_COUNT * 3
    }

    override fun onItemClick(item: UIMovie) {
        router.openMovieDetailsScreen(item)
    }
}

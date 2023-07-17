package com.kirchhoff.movies.screen.movie.ui.screen.genre

import android.content.Context
import android.os.Bundle
import android.view.View
import com.kirchhoff.movies.core.data.UIGenre
import com.kirchhoff.movies.core.data.UIMovie
import com.kirchhoff.movies.core.extensions.getParcelableExtra
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

class MovieListByGenreFragment : PaginatedScreenFragment<UIMovie, UIPaginated<UIMovie>>(),
    BaseRecyclerViewAdapter.OnItemClickListener<UIMovie> {

    private val genre: UIGenre by lazy {
        requireArguments().getParcelableExtra(GENRE_ARG)
            ?: error("Should provide genre argument for fragment")
    }

    override val vm: MovieListByGenreViewModel by viewModel {
        parametersOf(genre.id)
    }

    override val listAdapter: BaseRecyclerViewAdapter<BaseVH<UIMovie>, UIMovie> =
        MovieAdapter(this)

    override val configuration: Configuration = Configuration(
        threshold = THRESHOLD,
        spanCount = SPAN_COUNT,
        emptyResultText = R.string.movie_no_results,
        isToolbarVisible = true,
        toolbarTitle = ""
    )

    override fun onAttach(context: Context) {
        loadKoinModules(movieListByGenreModule)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        displayTitle(
            getString(R.string.movie_movies_with_genre_format, genre.name)
        )
    }

    override fun onDestroyView() {
        unloadKoinModules(movieListByGenreModule)
        super.onDestroyView()
    }

    override fun onItemClick(item: UIMovie) {
        router.openMovieDetailsScreen(item)
    }

    companion object {
        fun newInstance(genre: UIGenre): MovieListByGenreFragment =
            MovieListByGenreFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(GENRE_ARG, genre)
                }
            }

        private const val GENRE_ARG = "GENRE_ARG"
        private const val SPAN_COUNT = 2
        private const val THRESHOLD = SPAN_COUNT * 3
    }
}

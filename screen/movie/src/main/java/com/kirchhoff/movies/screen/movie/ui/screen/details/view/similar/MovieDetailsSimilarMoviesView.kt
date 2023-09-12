package com.kirchhoff.movies.screen.movie.ui.screen.details.view.similar

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kirchhoff.movies.core.data.UIMovie
import com.kirchhoff.movies.core.ui.recyclerview.adapter.BaseRecyclerViewAdapter
import com.kirchhoff.movies.core.ui.recyclerview.decorations.EdgesMarginItemDecoration
import com.kirchhoff.movies.core.ui.recyclerview.decorations.TopBottomMarginItemDecoration
import com.kirchhoff.movies.screen.movie.R
import com.kirchhoff.movies.screen.movie.databinding.ViewMovieDetailsSimilarMoviesBinding
import com.kirchhoff.movies.screen.movie.ui.screen.details.view.similar.adapter.MovieDetailsSimilarMovieAdapter

class MovieDetailsSimilarMoviesView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val adapter = MovieDetailsSimilarMovieAdapter(MovieItemClickListener())

    private val binding =
        ViewMovieDetailsSimilarMoviesBinding.inflate(LayoutInflater.from(context), this, true)

    private var itemClickListener: ItemClickListener? = null

    init {
        with(binding) {
            rvSimilarMovies.apply {
                adapter = this@MovieDetailsSimilarMoviesView.adapter
                layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
                addItemDecoration(EdgesMarginItemDecoration(resources.getDimensionPixelSize(R.dimen.movie_details_similar_movie_edges_margin)))
                addItemDecoration(
                    TopBottomMarginItemDecoration(
                        resources.getDimensionPixelSize(R.dimen.movie_details_similar_movie_edges_margin),
                        resources.getDimensionPixelSize(R.dimen.movie_details_similar_movie_edges_margin)
                    )
                )
                isNestedScrollingEnabled = false
            }
            tvSeeAll.setOnClickListener { itemClickListener?.onSeeAllClick() }
            tvSimilarMovies.setOnClickListener { itemClickListener?.onSeeAllClick() }
        }
    }

    fun displayMovies(list: List<UIMovie>) {
        binding.notEmptyViewGroup.isVisible = list.isNotEmpty()
        binding.emptyViewGroup.isVisible = list.isEmpty()
        adapter.addItems(list)
    }

    fun itemClickListener(listener: ItemClickListener) {
        itemClickListener = listener
    }

    private inner class MovieItemClickListener : BaseRecyclerViewAdapter.OnItemClickListener<UIMovie> {
        override fun onItemClick(item: UIMovie) {
            itemClickListener?.onMovieClick(item)
        }
    }

    interface ItemClickListener {
        fun onSeeAllClick()
        fun onMovieClick(movie: UIMovie)
    }
}

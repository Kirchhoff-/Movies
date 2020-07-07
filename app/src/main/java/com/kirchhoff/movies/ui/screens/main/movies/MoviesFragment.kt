package com.kirchhoff.movies.ui.screens.main.movies

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.kirchhoff.movies.R
import com.kirchhoff.movies.data.DiscoverMoviesResponse
import com.kirchhoff.movies.databinding.FragmentMoviesBinding
import com.kirchhoff.movies.extensions.getSizeFromRes
import com.kirchhoff.movies.ui.screens.main.movies.adapter.MoviesListAdapter
import com.kirchhoff.movies.ui.utils.recyclerView.Paginator
import com.kirchhoff.movies.ui.utils.recyclerView.decorations.GridMarginItemDecoration
import com.kirchhoff.movies.utils.binding.viewBinding
import org.koin.android.viewmodel.ext.android.viewModel

class MoviesFragment : Fragment(R.layout.fragment_movies) {

    private val vm by viewModel<MoviesVM>()

    private val viewBinding: FragmentMoviesBinding by viewBinding()
    private val moviesAdapter = MoviesListAdapter()
    private val paginator = Paginator(loadMore = { loadMovies(it) }, threshold = MOVIES_THRESHOLD)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadMovies(1)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding.rvMovies.apply {
            layoutManager = GridLayoutManager(requireContext(), SPAN_COUNT)
            adapter = moviesAdapter
            addItemDecoration(
                GridMarginItemDecoration(
                    SPAN_COUNT,
                    getSizeFromRes(R.dimen.movie_item_top_margin),
                    getSizeFromRes(R.dimen.movie_item_bottom_margin),
                    getSizeFromRes(R.dimen.movie_item_edges_margin)
                )
            )
            addOnScrollListener(paginator)
        }

        with(vm) {
            loading.observe(viewLifecycleOwner, Observer { viewBinding.pbMovies.isVisible = it })
            paginating.observe(viewLifecycleOwner, Observer { viewBinding.pbPaginate.isVisible = it })
            moviesResponse.observe(viewLifecycleOwner, Observer { obtainMoviesResponse(it) })
            error.observe(
                viewLifecycleOwner,
                Observer { Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show() })
        }
    }

    private fun loadMovies(page: Int) {
        vm.discoverMovies(page)
        paginator.isLoading = true
    }

    private fun obtainMoviesResponse(response: DiscoverMoviesResponse) {
        moviesAdapter.addItems(response.results)
        paginator.totalPages = response.total_pages
        paginator.isLoading = false
    }

    companion object {
        private const val SPAN_COUNT = 2
        private const val MOVIES_THRESHOLD = SPAN_COUNT * 3
    }
}

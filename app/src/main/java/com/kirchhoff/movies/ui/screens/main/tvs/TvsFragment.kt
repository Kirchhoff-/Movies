package com.kirchhoff.movies.ui.screens.main.tvs

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.kirchhoff.movies.R
import com.kirchhoff.movies.data.responses.DiscoverTvsResponse
import com.kirchhoff.movies.databinding.FragmentTvsBinding
import com.kirchhoff.movies.ui.screens.BaseFragment
import com.kirchhoff.movies.ui.screens.main.tvs.adapter.TvsListAdapter
import com.kirchhoff.movies.ui.utils.recyclerView.Paginator
import com.kirchhoff.movies.ui.utils.recyclerView.decorations.GridMarginItemDecoration
import com.kirchhoff.movies.utils.binding.viewBinding
import org.koin.android.viewmodel.ext.android.viewModel

class TvsFragment : BaseFragment(R.layout.fragment_tvs) {

    private val vm by viewModel<TvsVM>()

    private val viewBinding: FragmentTvsBinding by viewBinding()
    private val tvsAdapter = TvsListAdapter()
    private val paginator = Paginator(loadMore = { loadTvs(it) }, threshold = TVS_THRESHOLD)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadTvs(1)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding.rvTvs.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = tvsAdapter
            addItemDecoration(GridMarginItemDecoration(
                1,
                resources.getDimensionPixelSize(R.dimen.tv_item_top_margin),
                resources.getDimensionPixelSize(R.dimen.tv_item_bottom_margin),
                resources.getDimensionPixelSize(R.dimen.tv_item_edges_margin)
            ))
            addOnScrollListener(paginator)
        }

        with(vm) {
            loading.subscribe { viewBinding.pbTvs.isVisible = it }
            paginating.subscribe { viewBinding.pbPaginate.isVisible = it }
            data.subscribe(::obtainTvsResponse)
            error.subscribe { Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show() }
        }
    }

    private fun loadTvs(page: Int) {
        vm.fetchData(page)
        paginator.isLoading = true
    }

    private fun obtainTvsResponse(response: DiscoverTvsResponse) {
        tvsAdapter.addItems(response.results)
        paginator.totalPages = response.total_pages
        paginator.isLoading = false
    }

    companion object {
        private const val TVS_THRESHOLD = 3
    }
}

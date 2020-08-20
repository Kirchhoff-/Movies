package com.kirchhoff.movies.ui.screens.core

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.recyclerview.widget.GridLayoutManager
import com.kirchhoff.movies.R
import com.kirchhoff.movies.data.ui.core.PaginatedData
import com.kirchhoff.movies.databinding.FragmentPaginatedBinding
import com.kirchhoff.movies.extensions.getSizeFromRes
import com.kirchhoff.movies.ui.screens.BaseFragment
import com.kirchhoff.movies.ui.utils.recyclerView.BaseRecyclerViewAdapter
import com.kirchhoff.movies.ui.utils.recyclerView.BaseVH
import com.kirchhoff.movies.ui.utils.recyclerView.Paginator
import com.kirchhoff.movies.ui.utils.recyclerView.decorations.GridMarginItemDecoration
import com.kirchhoff.movies.utils.binding.viewBinding

abstract class PaginatedScreenFragment<Data, T : PaginatedData<Data>> : BaseFragment(R.layout.fragment_paginated) {

    abstract val threshold: Int
    abstract val spanCount: Int
    abstract val listAdapter: BaseRecyclerViewAdapter<BaseVH<Data>, Data>
    abstract val vm: PaginatedScreenVM<T>

    private val viewBinding: FragmentPaginatedBinding by viewBinding()
    private lateinit var paginator: Paginator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        paginator = Paginator(loadMore = { loadData(it) }, threshold = threshold)
        loadData(1)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding.rv.apply {
            layoutManager = GridLayoutManager(requireContext(), spanCount)
            adapter = listAdapter
            addItemDecoration(
                GridMarginItemDecoration(
                    spanCount,
                    getSizeFromRes(R.dimen.main_screen_item_top_margin),
                    getSizeFromRes(R.dimen.main_screen_item_bottom_margin),
                    getSizeFromRes(R.dimen.main_screen_item_edges_margin)
                )
            )
            addOnScrollListener(paginator)
        }

        with(vm) {
            loading.subscribe { viewBinding.pbLoading.isVisible = it }
            paginating.subscribe { viewBinding.pbPaginate.isVisible = it }
            data.subscribe(::obtainDataResponse)
            error.subscribe { Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show() }
        }
    }

    private fun loadData(page: Int) {
        vm.fetchData(page)
        paginator.isLoading = true
    }

    private fun obtainDataResponse(response: T) {
        listAdapter.addItems(response.results)
        paginator.totalPages = response.totalPages
        paginator.isLoading = false
    }
}

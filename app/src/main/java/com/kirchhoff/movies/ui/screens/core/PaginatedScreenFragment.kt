package com.kirchhoff.movies.ui.screens.core

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.core.view.isVisible
import androidx.recyclerview.widget.GridLayoutManager
import com.kirchhoff.movies.R
import com.kirchhoff.movies.core.ui.BaseFragment
import com.kirchhoff.movies.core.ui.paginated.UIPaginated
import com.kirchhoff.movies.core.ui.recyclerview.BaseRecyclerViewAdapter
import com.kirchhoff.movies.core.ui.recyclerview.BaseVH
import com.kirchhoff.movies.core.ui.recyclerview.Paginator
import com.kirchhoff.movies.core.ui.recyclerview.decorations.GridMarginItemDecoration
import com.kirchhoff.movies.core.ui.utils.viewBinding
import com.kirchhoff.movies.databinding.FragmentPaginatedBinding

abstract class PaginatedScreenFragment<Data, T : UIPaginated<Data>> : BaseFragment(R.layout.fragment_paginated) {

    abstract val configuration: Configuration
    abstract val listAdapter: BaseRecyclerViewAdapter<BaseVH<Data>, Data>
    abstract val vm: PaginatedScreenVM<T>

    private val viewBinding by viewBinding(FragmentPaginatedBinding::bind)
    private lateinit var paginator: Paginator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        paginator = Paginator(loadMore = { loadData(it) }, threshold = configuration.threshold)
        loadData(1)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(viewBinding) {
            tvEmptyResult.setText(configuration.emptyResultText)
            rv.apply {
                layoutManager = GridLayoutManager(requireContext(), configuration.spanCount)
                adapter = listAdapter
                addItemDecoration(
                    GridMarginItemDecoration(
                        configuration.spanCount,
                        resources.getDimensionPixelSize(R.dimen.main_screen_item_top_margin),
                        resources.getDimensionPixelSize(R.dimen.main_screen_item_bottom_margin),
                        resources.getDimensionPixelSize(R.dimen.main_screen_item_edges_margin)
                    )
                )
                addOnScrollListener(paginator)
            }
            toolbar.apply {
                isVisible = configuration.isToolbarVisible
                title = configuration.toolbarTitle
                setNavigationOnClickListener { requireActivity().onBackPressed() }
            }
        }

        with(vm) {
            loading.subscribe { viewBinding.pbLoading.isVisible = it }
            paginating.subscribe { viewBinding.pbPaginate.isVisible = it }
            data.subscribe(::obtainDataResponse)
            error.subscribe { Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show() }
            showEmptyResult.subscribe(::obtainViewsVisibility)
        }
    }

    protected fun displayTitle(title: String) {
        viewBinding.toolbar.title = title
    }

    private fun loadData(page: Int) {
        vm.fetchData(page)
        paginator.isLoading = true
    }

    private fun obtainDataResponse(response: T) {
        if (paginator.currentPage < response.page) listAdapter.addItems(response.results)
        paginator.apply {
            currentPage = response.page
            totalPages = response.totalPages
            isLoading = false
        }
    }

    private fun obtainViewsVisibility(isEmptyResult: Boolean) {
        viewBinding.rv.isVisible = !isEmptyResult
        viewBinding.tvEmptyResult.isVisible = isEmptyResult
    }

    data class Configuration(
        val threshold: Int,
        val spanCount: Int,
        @StringRes val emptyResultText: Int,
        val isToolbarVisible: Boolean,
        val toolbarTitle: String
    )
}

package com.kirchhoff.movies.core.ui.paginated

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.core.view.isVisible
import androidx.recyclerview.widget.GridLayoutManager
import com.kirchhoff.movies.core.R
import com.kirchhoff.movies.core.databinding.FragmentPaginatedBinding
import com.kirchhoff.movies.core.ui.BaseFragment
import com.kirchhoff.movies.core.ui.recyclerview.Paginator
import com.kirchhoff.movies.core.ui.recyclerview.adapter.BaseRecyclerViewAdapter
import com.kirchhoff.movies.core.ui.recyclerview.adapter.viewholder.BaseVH
import com.kirchhoff.movies.core.ui.recyclerview.decorations.GridMarginItemDecoration

abstract class PaginatedScreenFragment<Data, T : UIPaginated<Data>> : BaseFragment() {

    abstract val configuration: Configuration
    abstract val listAdapter: BaseRecyclerViewAdapter<BaseVH<Data>, Data>
    abstract val vm: PaginatedScreenViewModel<T>

    private var _viewBinding: FragmentPaginatedBinding? = null
    private val viewBinding get() = _viewBinding!!

    private lateinit var paginator: Paginator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        paginator = Paginator(loadMore = { loadData(it) }, threshold = configuration.threshold)
        loadData(1)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _viewBinding = FragmentPaginatedBinding.inflate(inflater, container, false)
        return viewBinding.root
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
                        resources.getDimensionPixelSize(R.dimen.paginated_screen_item_top_margin),
                        resources.getDimensionPixelSize(R.dimen.paginated_screen_item_bottom_margin),
                        resources.getDimensionPixelSize(R.dimen.paginated_screen_item_edges_margin)
                    )
                )
                addOnScrollListener(paginator)
            }
            toolbar.apply {
                isVisible = configuration.isToolbarVisible
                title = configuration.toolbarTitle
                setNavigationOnClickListener { requireActivity().onBackPressedDispatcher.onBackPressed() }
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

    override fun onDestroyView() {
        viewBinding.rv.adapter = null
        super.onDestroyView()
        _viewBinding = null
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

package com.kirchhoff.movies.ui.screens.main.persons

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.recyclerview.widget.GridLayoutManager
import com.kirchhoff.movies.R
import com.kirchhoff.movies.data.responses.PersonsResponse
import com.kirchhoff.movies.databinding.FragmentPersonsBinding
import com.kirchhoff.movies.extensions.getSizeFromRes
import com.kirchhoff.movies.ui.screens.BaseFragment
import com.kirchhoff.movies.ui.screens.main.persons.adapter.PersonsListAdapter
import com.kirchhoff.movies.ui.utils.recyclerView.Paginator
import com.kirchhoff.movies.ui.utils.recyclerView.decorations.GridMarginItemDecoration
import com.kirchhoff.movies.utils.binding.viewBinding
import org.koin.android.viewmodel.ext.android.viewModel

class PersonsFragment : BaseFragment(R.layout.fragment_persons) {

    private val vm by viewModel<PersonsVM>()

    private val viewBinding: FragmentPersonsBinding by viewBinding()
    private val personsAdapter = PersonsListAdapter()
    private val paginator = Paginator(loadMore = { loadPersons(it) }, threshold = PERSONS_THRESHOLD)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadPersons(1)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding.rvPersons.apply {
            layoutManager = GridLayoutManager(requireContext(), SPAN_COUNT)
            adapter = personsAdapter
            addItemDecoration(
                GridMarginItemDecoration(
                    SPAN_COUNT,
                    getSizeFromRes(R.dimen.person_item_top_margin),
                    getSizeFromRes(R.dimen.person_item_bottom_margin),
                    getSizeFromRes(R.dimen.person_item_edges_margin)
                )
            )
            addOnScrollListener(paginator)
        }

        with(vm) {
            loading.subscribe { viewBinding.pbPersons.isVisible = it }
            paginating.subscribe { viewBinding.pbPaginate.isVisible = it }
            data.subscribe(::obtainPersonsResponse)
            error.subscribe { Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show() }
        }
    }

    private fun loadPersons(page: Int) {
        vm.fetchData(page)
        paginator.isLoading = true
    }

    private fun obtainPersonsResponse(response: PersonsResponse) {
        personsAdapter.addItems(response.results)
        paginator.totalPages = response.total_pages
        paginator.isLoading = false
    }

    companion object {
        private const val SPAN_COUNT = 3
        private const val PERSONS_THRESHOLD = SPAN_COUNT * 2
    }
}

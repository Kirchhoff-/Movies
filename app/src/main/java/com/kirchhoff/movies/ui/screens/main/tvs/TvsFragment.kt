package com.kirchhoff.movies.ui.screens.main.tvs

import com.kirchhoff.movies.data.ui.core.PaginatedData
import com.kirchhoff.movies.data.ui.main.UITv
import com.kirchhoff.movies.ui.screens.core.PaginatedScreenFragment
import com.kirchhoff.movies.ui.screens.core.tvs.adapter.TvsListAdapter
import com.kirchhoff.movies.ui.screens.details.DetailsActivity
import com.kirchhoff.movies.ui.utils.recyclerView.BaseRecyclerViewAdapter
import org.koin.android.viewmodel.ext.android.viewModel

class TvsFragment : PaginatedScreenFragment<UITv, PaginatedData<UITv>>(),
    BaseRecyclerViewAdapter.OnItemClickListener<UITv> {

    override val vm by viewModel<TvsVM>()

    override val listAdapter = TvsListAdapter(this)

    override val threshold = TVS_THRESHOLD

    override val spanCount = SPAN_COUNT

    companion object {
        private const val SPAN_COUNT = 1
        private const val TVS_THRESHOLD = 3
    }

    override fun onItemClick(item: UITv) {
        startActivity(DetailsActivity.createTvDetailsIntent(requireContext(), item))
    }
}

package com.kirchhoff.movies.ui.screens.similar.tv

import android.os.Bundle
import com.kirchhoff.movies.data.Tv
import com.kirchhoff.movies.data.responses.DiscoverTvsResponse
import com.kirchhoff.movies.ui.screens.core.PaginatedScreenFragment
import com.kirchhoff.movies.ui.screens.core.tvs.adapter.TvsListAdapter
import com.kirchhoff.movies.ui.screens.details.DetailsActivity
import com.kirchhoff.movies.ui.utils.recyclerView.BaseRecyclerViewAdapter
import org.koin.android.viewmodel.ext.android.viewModel

class SimilarTvsFragment : PaginatedScreenFragment<Tv, DiscoverTvsResponse>(),
    BaseRecyclerViewAdapter.OnItemClickListener<Tv> {

    override val vm by viewModel<SimilarTvsVM>()

    override val listAdapter = TvsListAdapter(this)

    override val threshold = TVS_THRESHOLD

    override val spanCount = SPAN_COUNT

    override val dataId: Int by lazy { arguments!!.getInt(TV_ID_ARG) }

    override fun onItemClick(item: Tv) {
        startActivity(DetailsActivity.createTvDetailsIntent(requireContext(), item))
    }

    companion object {
        fun newInstance(tvId: Int): SimilarTvsFragment {
            val fragment = SimilarTvsFragment()
            val arg = Bundle()
            arg.putInt(TV_ID_ARG, tvId)
            fragment.arguments = arg
            return fragment
        }

        private const val TV_ID_ARG = "TV_ID_ARG"
        private const val SPAN_COUNT = 1
        private const val TVS_THRESHOLD = 3
    }
}

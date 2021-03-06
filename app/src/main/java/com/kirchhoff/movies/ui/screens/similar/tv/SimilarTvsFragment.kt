package com.kirchhoff.movies.ui.screens.similar.tv

import android.os.Bundle
import com.kirchhoff.movies.data.ui.core.UIPaginated
import com.kirchhoff.movies.data.ui.main.UITv
import com.kirchhoff.movies.ui.screens.core.PaginatedScreenFragment
import com.kirchhoff.movies.ui.screens.core.tvs.adapter.TvsListAdapter
import com.kirchhoff.movies.ui.screens.details.DetailsActivity
import com.kirchhoff.movies.ui.utils.recyclerView.BaseRecyclerViewAdapter
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class SimilarTvsFragment : PaginatedScreenFragment<UITv, UIPaginated<UITv>>(),
    BaseRecyclerViewAdapter.OnItemClickListener<UITv> {

    override val vm: SimilarTvsVM by viewModel { parametersOf(arguments!!.getInt(TV_ID_ARG)) }

    override val listAdapter = TvsListAdapter(this)

    override val threshold = TVS_THRESHOLD

    override val spanCount = SPAN_COUNT

    override fun onItemClick(item: UITv) {
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

package com.kirchhoff.movies.ui.screens.main.tvs

import com.kirchhoff.movies.data.Tv
import com.kirchhoff.movies.data.responses.DiscoverTvsResponse
import com.kirchhoff.movies.ui.screens.main.MainScreenFragment
import com.kirchhoff.movies.ui.screens.main.tvs.adapter.TvsListAdapter
import org.koin.android.viewmodel.ext.android.viewModel

class TvsFragment : MainScreenFragment<Tv, DiscoverTvsResponse>() {

    override val vm by viewModel<TvsVM>()

    override val listAdapter = TvsListAdapter()

    override val threshold = TVS_THRESHOLD

    override val spanCount = SPAN_COUNT

    companion object {
        private const val SPAN_COUNT = 1
        private const val TVS_THRESHOLD = 3
    }
}

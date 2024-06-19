package com.kirchhoff.movies.screen.tvshow

import androidx.fragment.app.Fragment
import com.kirchhoff.movies.core.data.UITv
import com.kirchhoff.movies.screen.tvshow.ui.screen.details.TvShowDetailsFragment
import com.kirchhoff.movies.screen.tvshow.ui.screen.list.TvShowListFragment
import com.kirchhoff.movies.screen.tvshow.ui.screen.similar.TvShowSimilarFragment

interface ITvShowFacade {
    fun tvShowList(): Fragment
    fun tvShowDetails(tv: UITv): Fragment
}

class TvShowFacade : ITvShowFacade {

    override fun tvShowList(): Fragment = TvShowListFragment()

    override fun tvShowDetails(tv: UITv): Fragment = TvShowDetailsFragment.newInstance(tv)
}

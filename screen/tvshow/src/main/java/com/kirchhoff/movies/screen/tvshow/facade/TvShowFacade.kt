package com.kirchhoff.movies.screen.tvshow.facade

import androidx.fragment.app.Fragment
import com.kirchhoff.movies.core.data.UITv
import com.kirchhoff.movies.screen.tvshow.ITvShowFacade
import com.kirchhoff.movies.screen.tvshow.ui.screen.details.TvShowDetailsFragment
import com.kirchhoff.movies.screen.tvshow.ui.screen.list.TvShowListFragment
import com.kirchhoff.movies.screen.tvshow.ui.screen.similar.TvShowSimilarFragment

class TvShowFacade : ITvShowFacade {

    override fun tvShowList(): Fragment = TvShowListFragment()

    override fun tvShowDetails(tv: UITv): Fragment = TvShowDetailsFragment.newInstance(tv)

    override fun similarTvShow(tv: UITv): Fragment = TvShowSimilarFragment.newInstance(tv)
}

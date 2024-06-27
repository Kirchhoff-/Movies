package com.kirchhoff.movies.screen.tvshow

import androidx.fragment.app.Fragment
import com.kirchhoff.movies.core.data.UITv
import com.kirchhoff.movies.screen.tvshow.ui.screen.details.TvShowDetailsFragment
import com.kirchhoff.movies.screen.tvshow.ui.screen.discover.TvShowDiscoverFragment

interface ITvShowFacade {
    fun tvShowList(): Fragment
    fun tvShowDetails(tv: UITv): Fragment
}

class TvShowFacade : ITvShowFacade {

    override fun tvShowList(): Fragment = TvShowDiscoverFragment.newInstance()

    override fun tvShowDetails(tv: UITv): Fragment = TvShowDetailsFragment.newInstance(tv)
}

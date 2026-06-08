package com.kirchhoff.movies.screen.tvshow

import androidx.fragment.app.Fragment
import com.kirchhoff.movies.core.data.ui.UITv
import com.kirchhoff.movies.screen.tvshow.ui.screen.details.TvShowDetailsFragment
import com.kirchhoff.movies.screen.tvshow.ui.screen.discover.TvShowDiscoverFragment

class TvShowFacade {

    fun tvShowList(): Fragment = TvShowDiscoverFragment.newInstance()

    fun tvShowDetails(tv: UITv): Fragment = TvShowDetailsFragment.newInstance(tv)
}

package com.kirchhoff.movies.screen.tvshow

import androidx.fragment.app.Fragment
import com.kirchhoff.movies.core.data.UITv

interface ITvShowFacade {
    fun tvShowList(): Fragment
    fun tvShowDetails(tv: UITv): Fragment
    fun similarTvShow(tvId: Int, tvName: String?): Fragment
}

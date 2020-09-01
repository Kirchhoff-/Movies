package com.kirchhoff.movies.mapper.entertainment

import com.kirchhoff.movies.data.network.core.NetworkEntertainmentCredits
import com.kirchhoff.movies.data.ui.core.UIEntertainmentCredits

interface IEntertainmentMapper {
    fun createUIEntertainmentCredits(credits: NetworkEntertainmentCredits): UIEntertainmentCredits
}

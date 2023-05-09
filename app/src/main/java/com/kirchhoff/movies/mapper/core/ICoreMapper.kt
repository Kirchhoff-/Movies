package com.kirchhoff.movies.mapper.core

import com.kirchhoff.movies.core.data.UIEntertainmentCredits
import com.kirchhoff.movies.core.data.UIGenre
import com.kirchhoff.movies.networkdata.core.NetworkEntertainmentCredits
import com.kirchhoff.movies.networkdata.core.NetworkObjectWithName

interface ICoreMapper {
    fun createUIEntertainmentCredits(credits: NetworkEntertainmentCredits): UIEntertainmentCredits
    fun createUIGenre(item: NetworkObjectWithName): UIGenre
}

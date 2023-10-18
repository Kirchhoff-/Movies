package com.kirchhoff.movies.core.mapper.core

import com.kirchhoff.movies.core.data.UIEntertainmentCredits
import com.kirchhoff.movies.core.data.UIGenre
import com.kirchhoff.movies.core.data.UIImage
import com.kirchhoff.movies.networkdata.core.NetworkEntertainmentCredits
import com.kirchhoff.movies.networkdata.core.NetworkObjectWithName
import com.kirchhoff.movies.networkdata.main.NetworkImage

interface ICoreMapper {
    fun createUIEntertainmentCredits(credits: NetworkEntertainmentCredits): UIEntertainmentCredits
    fun createUIGenre(item: NetworkObjectWithName): UIGenre
    fun createUIImage(item: NetworkImage): UIImage
}

package com.kirchhoff.movies.screen.person.ui.screen.list.mapper

import com.kirchhoff.movies.core.data.UIPerson
import com.kirchhoff.movies.core.mapper.BaseMapper
import com.kirchhoff.movies.core.ui.paginated.UIPaginated
import com.kirchhoff.movies.networkdata.core.NetworkPaginated
import com.kirchhoff.movies.networkdata.main.NetworkPerson

internal interface IPersonListMapper {
    fun createUIPersons(personList: NetworkPaginated<NetworkPerson>): UIPaginated<UIPerson>
}

internal class PersonListMapper : BaseMapper(), IPersonListMapper {

    override fun createUIPersons(personList: NetworkPaginated<NetworkPerson>): UIPaginated<UIPerson> = personList.toUIPaginated()

    private fun NetworkPaginated<NetworkPerson>.toUIPaginated(): UIPaginated<UIPerson> = UIPaginated(
        page = page,
        results = results.map { it.toUIPerson() },
        totalPages = totalPages
    )

    private fun NetworkPerson.toUIPerson(): UIPerson = UIPerson(
        id = id,
        name = name,
        profilePath = profilePath
    )
}

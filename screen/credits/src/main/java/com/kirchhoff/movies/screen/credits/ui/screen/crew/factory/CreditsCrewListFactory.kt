package com.kirchhoff.movies.screen.credits.ui.screen.crew.factory

import com.kirchhoff.movies.core.data.UIEntertainmentPerson
import com.kirchhoff.movies.screen.credits.ui.screen.crew.model.CreditsCrewListPersonItem
import com.kirchhoff.movies.screen.credits.ui.screen.crew.model.CreditsCrewListItem

internal class CreditsCrewListFactory : ICreditsCrewListFactory {
    override fun createCrewList(
        creators: List<UIEntertainmentPerson.Creator>,
        expandedItems: Set<String>
    ): List<CreditsCrewListItem> {
        val resultList = mutableListOf<CreditsCrewListItem>()
        creators.groupBy { it.job }
            .onEach { group ->
                group.key?.let { job ->
                    resultList.add(
                        CreditsCrewListItem(
                            job = job,
                            persons = group.value.map { creator ->
                                CreditsCrewListPersonItem(
                                    name = creator.name,
                                    profilePath = creator.profilePath
                                )
                            },
                            isExpanded = expandedItems.contains(job)
                        )
                    )
                }
            }

        return resultList
    }
}

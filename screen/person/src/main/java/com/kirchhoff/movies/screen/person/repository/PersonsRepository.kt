package com.kirchhoff.movies.screen.person.repository

import com.kirchhoff.movies.core.data.UIPerson
import com.kirchhoff.movies.core.repository.BaseRepository
import com.kirchhoff.movies.core.repository.Result
import com.kirchhoff.movies.core.ui.paginated.UIPaginated
import com.kirchhoff.movies.screen.person.data.UIPersonCredits
import com.kirchhoff.movies.screen.person.data.UIPersonDetails
import com.kirchhoff.movies.screen.person.data.UIPersonImage
import com.kirchhoff.movies.screen.person.mapper.IPersonDetailsMapper
import com.kirchhoff.movies.screen.person.mapper.IPersonsMapper
import com.kirchhoff.movies.screen.person.network.PersonService
import com.kirchhoff.movies.screen.person.storage.IPersonImagesStorage

internal interface IPersonsRepository {
    suspend fun fetchPopularPersons(page: Int): Result<UIPaginated<UIPerson>>
    suspend fun fetchPersonDetail(personId: Int): Result<UIPersonDetails>
    suspend fun fetchPersonCredits(personId: Int): Result<UIPersonCredits>
    suspend fun fetchPersonImages(personId: Int): Result<List<UIPersonImage>>
}

internal class PersonsRepository(
    private val personService: PersonService,
    private val personImagesStorage: IPersonImagesStorage,
    private val personMapper: IPersonsMapper,
    private val personDetailsMapper: IPersonDetailsMapper
) : BaseRepository(), IPersonsRepository {

    override suspend fun fetchPopularPersons(page: Int): Result<UIPaginated<UIPerson>> =
        personMapper.createUIPersons(
            apiCall {
                personService.fetchPopularPerson(page)
            }
        )

    override suspend fun fetchPersonDetail(personId: Int): Result<UIPersonDetails> =
        personDetailsMapper.createUIPersonDetails(
            apiCall {
                personService.fetchPersonDetail(
                    personId
                )
            }
        )

    override suspend fun fetchPersonCredits(personId: Int): Result<UIPersonCredits> =
        personDetailsMapper.createUIPersonCredits(
            apiCall {
                personService.fetchPersonCredits(
                    personId
                )
            }
        )

    override suspend fun fetchPersonImages(personId: Int): Result<List<UIPersonImage>> {
        val localImages = personImagesStorage.fetchImages(personId)

        return if (localImages != null) {
            Result.Success(localImages)
        } else {
            val result = personDetailsMapper.createUIPersonImages(
                apiCall {
                    personService.fetchPersonImages(personId)
                }
            )

            if (result is Result.Success) {
                personImagesStorage.updateImages(personId, result.data)
            }

            result
        }
    }
}

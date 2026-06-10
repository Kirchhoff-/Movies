package com.kirchhoff.movies.screen.person.ui.screen.details.usecase

import com.kirchhoff.movies.core.repository.RepositoryResult
import com.kirchhoff.movies.screen.person.data.PersonUIImage
import com.kirchhoff.movies.screen.person.storage.PersonImagesStorage
import com.kirchhoff.movies.screen.person.ui.screen.details.mapper.PersonDetailsMapper
import com.kirchhoff.movies.screen.person.ui.screen.details.model.PersonUICredits
import com.kirchhoff.movies.screen.person.ui.screen.details.model.PersonUIDetails
import com.kirchhoff.movies.screen.person.ui.screen.details.repository.PersonDetailsRepository

internal class PersonDetailsUseCase(
    private val personDetailsRepository: PersonDetailsRepository,
    private val personImageStorage: PersonImagesStorage,
    private val personDetailsMapper: PersonDetailsMapper
) {

    suspend fun fetchDetails(personId: Int): Result<PersonUIDetails> =
        when (val response = personDetailsRepository.details(personId)) {
            is RepositoryResult.Success -> Result.success(personDetailsMapper.createPersonDetails(response.data))
            else -> Result.failure(Exception("Can't fetch the details"))
        }

    suspend fun fetchCredits(personId: Int): Result<PersonUICredits> =
        when (val response = personDetailsRepository.credits(personId)) {
            is RepositoryResult.Success -> Result.success(personDetailsMapper.createCredits(response.data))
            else -> Result.failure(Exception("Can't fetch the credits"))
        }

    suspend fun fetchImages(personId: Int): Result<List<PersonUIImage>> {
        val localImages = personImageStorage.fetchImages(personId)

        return if (localImages != null) {
            Result.success(localImages)
        } else {
            val result = personDetailsRepository.images(personId)

            if (result is RepositoryResult.Success) {
                val uiImages = result.data.images.map { personDetailsMapper.createPersonImage(it) }
                personImageStorage.updateImages(personId, uiImages)
                Result.success(uiImages)
            } else {
                Result.failure(Exception("Can't fetch the images"))
            }
        }
    }
}

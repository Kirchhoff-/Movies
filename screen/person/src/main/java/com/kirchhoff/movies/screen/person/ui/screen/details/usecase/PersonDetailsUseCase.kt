package com.kirchhoff.movies.screen.person.ui.screen.details.usecase

import com.kirchhoff.movies.core.repository.RepositoryResult
import com.kirchhoff.movies.screen.person.data.UIPersonImage
import com.kirchhoff.movies.screen.person.storage.IPersonImagesStorage
import com.kirchhoff.movies.screen.person.ui.screen.details.mapper.IPersonDetailsMapper
import com.kirchhoff.movies.screen.person.ui.screen.details.model.UIPersonCredits
import com.kirchhoff.movies.screen.person.ui.screen.details.model.UIPersonDetails
import com.kirchhoff.movies.screen.person.ui.screen.details.repository.IPersonDetailsRepository

internal interface IPersonDetailsUseCase {
    suspend fun fetchDetails(personId: Int): Result<UIPersonDetails>
    suspend fun fetchCredits(personId: Int): Result<UIPersonCredits>
    suspend fun fetchImages(personId: Int): Result<List<UIPersonImage>>
}

internal class PersonDetailsUseCase(
    private val personDetailsRepository: IPersonDetailsRepository,
    private val personImageStorage: IPersonImagesStorage,
    private val personDetailsMapper: IPersonDetailsMapper
) : IPersonDetailsUseCase {

    override suspend fun fetchDetails(personId: Int): Result<UIPersonDetails> =
        when (val response = personDetailsRepository.details(personId)) {
            is RepositoryResult.Success -> Result.success(personDetailsMapper.createPersonDetails(response.data))
            else -> Result.failure(Exception("Can't fetch the details"))
        }

    override suspend fun fetchCredits(personId: Int): Result<UIPersonCredits> =
        when (val response = personDetailsRepository.credits(personId)) {
            is RepositoryResult.Success -> Result.success(personDetailsMapper.createCredits(response.data))
            else -> Result.failure(Exception("Can't fetch the credits"))
        }

    override suspend fun fetchImages(personId: Int): Result<List<UIPersonImage>> {
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

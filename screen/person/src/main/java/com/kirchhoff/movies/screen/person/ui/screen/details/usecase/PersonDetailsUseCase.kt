package com.kirchhoff.movies.screen.person.ui.screen.details.usecase

import com.kirchhoff.movies.core.repository.Result
import com.kirchhoff.movies.screen.person.data.UIPersonImage
import com.kirchhoff.movies.screen.person.storage.IPersonImagesStorage
import com.kirchhoff.movies.screen.person.ui.screen.details.mapper.IPersonDetailsMapper
import com.kirchhoff.movies.screen.person.ui.screen.details.model.UIPersonCredits
import com.kirchhoff.movies.screen.person.ui.screen.details.model.UIPersonDetails
import com.kirchhoff.movies.screen.person.ui.screen.details.repository.IPersonDetailsRepository

internal interface IPersonDetailsUseCase {
    suspend fun fetchDetails(personId: Int): kotlin.Result<UIPersonDetails>
    suspend fun fetchCredits(personId: Int): kotlin.Result<UIPersonCredits>
    suspend fun fetchImages(personId: Int): kotlin.Result<List<UIPersonImage>>
}

internal class PersonDetailsUseCase(
    private val personDetailsRepository: IPersonDetailsRepository,
    private val personImageStorage: IPersonImagesStorage,
    private val personDetailsMapper: IPersonDetailsMapper
) : IPersonDetailsUseCase {

    override suspend fun fetchDetails(personId: Int): kotlin.Result<UIPersonDetails> =
        when (val response = personDetailsRepository.details(personId)) {
            is Result.Success -> kotlin.Result.success(personDetailsMapper.createPersonDetails(response.data))
            else -> kotlin.Result.failure(Exception("Can't fetch the details"))
        }

    override suspend fun fetchCredits(personId: Int): kotlin.Result<UIPersonCredits> =
        when (val response = personDetailsRepository.credits(personId)) {
            is Result.Success -> kotlin.Result.success(personDetailsMapper.createCredits(response.data))
            else -> kotlin.Result.failure(Exception("Can't fetch the credits"))
        }

    override suspend fun fetchImages(personId: Int): kotlin.Result<List<UIPersonImage>> {
        val localImages = personImageStorage.fetchImages(personId)

        return if (localImages != null) {
            kotlin.Result.success(localImages)
        } else {
            val result = personDetailsRepository.images(personId)

            if (result is Result.Success) {
                val uiImages = result.data.images.map { personDetailsMapper.createPersonImage(it) }
                personImageStorage.updateImages(personId, uiImages)
                kotlin.Result.success(uiImages)
            } else {
                kotlin.Result.failure(Exception("Can't fetch the images"))
            }
        }
    }
}

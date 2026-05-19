package com.kirchhoff.movies.core.repository

import retrofit2.Response

@Suppress("TooGenericExceptionCaught", "UnnecessaryAbstractClass")
abstract class BaseRepository {
    suspend fun <T : Any> apiCall(call: suspend () -> Response<T>): RepositoryResult<T> {
        return try {
            val response = call.invoke()
            if (response.isSuccessful) {
                RepositoryResult.Success(response)
            } else {
                RepositoryResult.Error(response)
            }
        } catch (ex: Exception) {
            RepositoryResult.Exception(ex)
        }
    }
}

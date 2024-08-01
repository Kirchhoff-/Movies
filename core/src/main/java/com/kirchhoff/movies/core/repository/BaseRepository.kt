package com.kirchhoff.movies.core.repository

import retrofit2.Response

@Suppress("TooGenericExceptionCaught")
abstract class BaseRepository {
    suspend fun <T : Any> apiCall(call: suspend () -> Response<T>): Result<T> {
        return try {
            val response = call.invoke()
            if (response.isSuccessful) {
                Result.Success(response)
            } else {
                Result.Error(response)
            }
        } catch (ex: Exception) {
            Result.Exception(ex)
        }
    }

    protected fun <T, R> mapErrorOrException(result: Result<T>): Result<R> =
        when (result) {
            is Result.Error -> Result.Error(result.responseBody, result.code)
            is Result.Exception -> Result.Exception(result.message)
            else -> error("Can't map result = $result")
        }
}

package com.kirchhoff.movies.repository

import retrofit2.Response

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
}
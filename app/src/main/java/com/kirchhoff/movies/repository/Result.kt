package com.kirchhoff.movies.repository

import okhttp3.ResponseBody
import retrofit2.Response

sealed class Result<out T> {
    class Success<T>(response: Response<T>) : Result<T>() {
        val data: T = response.body()!!
    }
    class Error<out T>(response: Response<out T>) : Result<T>() {
        val responseBody: ResponseBody? = response.errorBody()
        val code: Int = response.code()
        override fun toString(): String = "[ApiResponse.Failure $code]: $responseBody"
    }
    class Exception<out T>(exception: Throwable) : Result<T>() {
        val message: String? = exception.localizedMessage
        override fun toString(): String = "[ApiResponse.Failure]: $message"
    }
}
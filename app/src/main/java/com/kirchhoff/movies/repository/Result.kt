package com.kirchhoff.movies.repository

import okhttp3.ResponseBody
import retrofit2.Response

sealed class Result<out T> {
    class Success<T> : Result<T> {
        val data: T

        constructor(response: Response<T>) {
            data = response.body()!!
        }

        constructor(data: T) {
            this.data = data
        }
    }

    class Error<out T> : Result<T> {
        val responseBody: ResponseBody?
        val code: Int

        constructor(response: Response<out T>) {
            this.responseBody = response.errorBody()
            this.code = response.code()
        }

        constructor(responseBody: ResponseBody?, code: Int) {
            this.responseBody = responseBody
            this.code = code
        }

        override fun toString(): String = "[ApiResponse.Failure $code]: $responseBody"
    }

    class Exception<out T> : Result<T> {
        val message: String?

        constructor(exception: Throwable) {
            this.message = exception.localizedMessage
        }

        constructor(message: String?) {
            this.message = message
        }

        override fun toString(): String = "[ApiResponse.Failure]: $message"
    }
}

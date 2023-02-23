package com.kirchhoff.movies.mapper

import com.kirchhoff.movies.core.repository.Result

abstract class BaseMapper {
    protected fun <T, R> mapErrorOrException(result: Result<T>): Result<R> =
        when (result) {
            is Result.Error -> Result.Error(result.responseBody, result.code)
            is Result.Exception -> Result.Exception(result.message)
            else -> error("Can't map result = $result")
        }
}

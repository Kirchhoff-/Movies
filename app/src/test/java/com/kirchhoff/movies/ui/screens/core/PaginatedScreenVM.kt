package com.kirchhoff.movies.ui.screens.core

import com.kirchhoff.movies.repository.Result

fun PaginatedScreenVM<*>.verifyFirstPageErrorState(errorResult: Result.Error<*>) {
    assert(loading.value == false)
    assert(paginating.value == null)
    assert(error.value == errorResult.toString())
    assert(data.value == null)
}

fun PaginatedScreenVM<*>.verifyFirstPageExceptionState(exceptionResult: Result.Exception<*>) {
    assert(loading.value == false)
    assert(paginating.value == null)
    assert(error.value == exceptionResult.toString())
    assert(data.value == null)
}

fun PaginatedScreenVM<*>.verifyFirstPageSuccessState(successResult: Result.Success<*>) {
    assert(loading.value == false)
    assert(paginating.value == null)
    assert(error.value == null)
    assert(data.value == successResult.data)
}

fun PaginatedScreenVM<*>.verifyRandomPageErrorState(errorResult: Result.Error<*>) {
    assert(loading.value == null)
    assert(paginating.value == false)
    assert(error.value == errorResult.toString())
    assert(data.value == null)
}

fun PaginatedScreenVM<*>.verifyRandomPageExceptionState(exceptionResult: Result.Exception<*>) {
    assert(loading.value == null)
    assert(paginating.value == false)
    assert(error.value == exceptionResult.toString())
    assert(data.value == null)
}

fun PaginatedScreenVM<*>.verifyRandomPageSuccessState(successResult: Result.Success<*>) {
    assert(loading.value == null)
    assert(paginating.value == false)
    assert(error.value == null)
    assert(data.value == successResult.data)
}

fun PaginatedScreenVM<*>.verifyPaginatedErrorState(lastData: Result.Success<*>, errorResult: Result.Error<*>) {
    assert(loading.value == false)
    assert(paginating.value == false)
    assert(error.value == errorResult.toString())
    assert(data.value == lastData.data)
}

fun PaginatedScreenVM<*>.verifyPaginatedExceptionState(lastData: Result.Success<*>, exceptionResult: Result.Exception<*>) {
    assert(loading.value == false)
    assert(paginating.value == false)
    assert(error.value == exceptionResult.toString())
    assert(data.value == lastData.data)
}

fun PaginatedScreenVM<*>.verifyPaginatedSuccessState(successResult: Result.Success<*>) {
    assert(loading.value == false)
    assert(paginating.value == false)
    assert(error.value == null)
    assert(data.value == successResult.data)
}

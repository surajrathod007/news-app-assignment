package com.surajrathod.newsapp.api

sealed class NetworkResult<T>(
    val data: T? = null, val errorResponse: ErrorResponse? = null
) {
    class Success<T>(data: T) : NetworkResult<T>(data)
    class Error<T>(data: T?,errorResponse: ErrorResponse) : NetworkResult<T>(data,errorResponse)
}
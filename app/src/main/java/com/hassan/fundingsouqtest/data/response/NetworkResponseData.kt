package com.hassan.fundingsouqtest.data.response

import com.hassan.fundingsouqtest.utilities.enums.RequestStatus

sealed class NetworkResponseData<out T>{
    data class Success<out T>(val status: RequestStatus, val data: T?): NetworkResponseData<T>()
    data class Failure(val status: RequestStatus, val message: String? = null): NetworkResponseData<Nothing>()
    data class Loading(val message: String? = "Loading, please wait for the response"): NetworkResponseData<Nothing>()
    data class Exception(val status: RequestStatus, val statusCode: Int? = 0, val message: String? = null): NetworkResponseData<Nothing>()
}
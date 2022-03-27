package com.hassan.fundingsouqtest.utilities.extensions

import com.hassan.fundingsouqtest.data.response.NetworkResponseData
import com.hassan.fundingsouqtest.utilities.enums.RequestStatus
import org.json.JSONException
import retrofit2.HttpException

fun Exception.handleExceptionForRequests(): NetworkResponseData.Exception{
    return when(this){
        is HttpException -> {
            NetworkResponseData.Exception(RequestStatus.EXCEPTION,this.code(),this.message)
        }
        is JSONException -> {
            NetworkResponseData.Exception(RequestStatus.EXCEPTION,0,this.toString())
        }
        else -> {
            NetworkResponseData.Exception(RequestStatus.EXCEPTION,0,this.toString())
        }
    }
}
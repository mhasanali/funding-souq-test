package com.hassan.fundingsouqtest.utilities.constants

import com.hassan.fundingsouqtest.data.response.NetworkResponseData
import com.hassan.fundingsouqtest.utilities.enums.RequestStatus
import com.hassan.fundingsouqtest.utilities.extensions.handleExceptionForRequests
import okhttp3.Credentials
import java.lang.Exception

object NetworkConstants{

                const val authUsername = ""
                const val authPassword = ""
                const val baseURL = ""
                const val requestTimeoutInSeconds: Long = 120
                val basicAuth = Credentials.basic(authUsername, authPassword)

                   fun <T> performRequest(apiMethodToCall: () -> T): NetworkResponseData<T> {
                        var result: NetworkResponseData<T>
                        try {
                                val res = apiMethodToCall()
                                if(res == null){
                                     result = NetworkResponseData.Failure(RequestStatus.ERROR,"error")
                                }else{
                                    result = NetworkResponseData.Success(RequestStatus.SUCCESS,res)
                                }
                        }
                        catch (ex: Exception) {
                                result = ex.handleExceptionForRequests()
                        }
                        return result
                }

}

package com.hassan.fundingsouqtest.networking

import com.hassan.fundingsouqtest.data.response.DummyApiResponse
import com.hassan.fundingsouqtest.data.response.NetworkResponseData
import com.hassan.fundingsouqtest.utilities.constants.NetworkConstants
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class AuthRepository @Inject constructor(private val apiService: ApiService) {
    suspend fun getClients() = NetworkConstants.performRequest { runBlocking { apiService.getClients() } }

}
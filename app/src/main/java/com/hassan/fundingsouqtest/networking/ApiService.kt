package com.hassan.fundingsouqtest.networking

import com.hassan.fundingsouqtest.data.response.DummyApiResponse
import com.hassan.fundingsouqtest.utilities.constants.MiscEndpoints
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    @GET(MiscEndpoints.dummyEndpoint)
    suspend fun getClients(): DummyApiResponse
}
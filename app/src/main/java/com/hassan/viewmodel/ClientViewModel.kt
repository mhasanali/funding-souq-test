package com.hassan.viewmodel

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hassan.fundingsouqtest.adapter.ClientAdapter
import com.hassan.fundingsouqtest.data.response.DummyApiResponse
import com.hassan.fundingsouqtest.data.response.DummyApiResponseItem
import com.hassan.fundingsouqtest.data.response.NetworkResponseData
import com.hassan.fundingsouqtest.networking.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ClientViewModel @Inject constructor(private val authRepository: AuthRepository) : ViewModel() {
    private val clientList = MutableLiveData<NetworkResponseData<DummyApiResponse>>()
    private val clientAdapter: ClientAdapter = ClientAdapter()

    init {
        performApiRequest()
    }

    fun getClients() = clientList

    private fun performApiRequest() {
        viewModelScope.launch {
            clientList.postValue(authRepository.getClients())
        }
    }

    fun getAdapter() = clientAdapter

    fun setAdapter(clients: List<DummyApiResponseItem>) {
        clientAdapter.submitList(clients)
    }
}
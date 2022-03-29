package com.hassan.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hassan.fundingsouqtest.networking.AuthRepository
import com.hassan.fundingsouqtest.utilities.SingleLiveEvent
import com.hassan.fundingsouqtest.utilities.enums.UserAuthStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

class AuthViewModel : ViewModel() {
    private val userStatus = SingleLiveEvent<UserAuthStatus>()

    fun getUserStatus() = userStatus

    fun signUp() {
        userStatus.postValue(UserAuthStatus.SigningUp)
    }

    fun logIn() {
        userStatus.postValue(UserAuthStatus.LoggingIn)
    }

    fun loggedIn() {
        userStatus.postValue(UserAuthStatus.LoggedIn)
    }

}
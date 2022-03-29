package com.hassan.fundingsouqtest.view

import android.os.Bundle
import android.telephony.PhoneNumberUtils
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.hassan.fundingsouqtest.R
import com.hassan.fundingsouqtest.data.DummyData
import com.hassan.fundingsouqtest.data.response.Account
import com.hassan.fundingsouqtest.data.response.Address
import com.hassan.fundingsouqtest.data.response.User
import com.hassan.fundingsouqtest.databinding.FragmentRegisterBinding
import com.hassan.fundingsouqtest.utilities.enums.Sex
import com.hassan.fundingsouqtest.utilities.enums.UserAuthStatus
import com.hassan.fundingsouqtest.utilities.extensions.observeNTimes
import com.hassan.fundingsouqtest.utilities.extensions.observeOnce
import com.hassan.fundingsouqtest.utilities.extensions.showToast
import com.hassan.viewmodel.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment : Fragment() {
    private val authViewModel by viewModels<AuthViewModel>()
    private lateinit var registerFragmentBinding: FragmentRegisterBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        setupDependencies(container)
        observeAuthenticationStatus()
        return registerFragmentBinding.root
    }

    private fun observeAuthenticationStatus() {
        authViewModel.getUserStatus().observe(viewLifecycleOwner) {
            when (it) {
                UserAuthStatus.SigningUp -> {
                    validateInput()
                }
                UserAuthStatus.LoggingIn -> navigateToLoginScreen()
                else -> {
                    //do nothing for now
                }
            }
        };
    }

    private fun navigateToLoginScreen() {
        findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
    }

    private fun validateInput() {
        //leaving other validations as test is too long
        if(registerFragmentBinding.emailEditText.text.isNullOrBlank() || registerFragmentBinding
                .firstName.text.isNullOrBlank() || registerFragmentBinding.lastName.text
                .isNullOrBlank() || registerFragmentBinding.mobileNumber.text.isNullOrBlank() ||
            registerFragmentBinding.personalId.text.isNullOrBlank()) {
            requireActivity().showToast("All the fields are mandatory and cannot be left blank")
            return
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(registerFragmentBinding.emailEditText.text!!).matches
                ()) {
            requireActivity().showToast("Enter a valid email address")
            return
        }

        if (registerFragmentBinding.firstName.text!!.length >= 60) {
            registerFragmentBinding.firstName.error = "First name should not exceed 60 characters"
            return
        }

        if(registerFragmentBinding
                .lastName.text!!.length >= 60){
            registerFragmentBinding.lastName.error = "Last name should not exceed 60 characters"
            return
        }

        if (!PhoneNumberUtils.isGlobalPhoneNumber(registerFragmentBinding.mobileNumber.text
                .toString())) {
            registerFragmentBinding.mobileNumber.error = "Enter a valid mobile number"
            return
        }

        if (registerFragmentBinding.personalId.text!!.length != 11) {
            registerFragmentBinding.personalId.error = "Personal ID should be of 11 characters"
            return
        }

        val accounts = mutableListOf<Account>()
        accounts.add(Account("Abc",1234))
        val user = User(registerFragmentBinding.emailEditText.text.toString(),
            registerFragmentBinding.firstName.text.toString(),registerFragmentBinding.lastName
                .text.toString(),registerFragmentBinding.personalId.text.toString(),"",
            registerFragmentBinding.mobileNumber.text.toString(),Sex.Male, Address("PK","KHI",
                "St 1","12341"), accounts)
        proceedToSignUp(user)

    }

    private fun proceedToSignUp(userData: User) {
        DummyData.listOfUsers.add(userData)
        //TODO proceed ahead
    }

    private fun setupDependencies(container: ViewGroup?) {
        registerFragmentBinding = DataBindingUtil.inflate(layoutInflater,R.layout
            .fragment_register,container,false)
        registerFragmentBinding.lifecycleOwner = this
        registerFragmentBinding.authViewModel = authViewModel
    }

}
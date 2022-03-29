package com.hassan.fundingsouqtest.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.hassan.fundingsouqtest.R
import com.hassan.viewmodel.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.hassan.fundingsouqtest.databinding.FragmentLoginBinding
import com.hassan.fundingsouqtest.utilities.enums.UserAuthStatus

@AndroidEntryPoint
class LoginFragment : Fragment() {
    private val authViewModel by viewModels<AuthViewModel>()
    private lateinit var bindingLoginFragment: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        setupDependencies(inflater, container)
        observeUserStatus()
        return bindingLoginFragment.root
    }

    private fun observeUserStatus() {
        authViewModel.getUserStatus().observe(viewLifecycleOwner) {
            if (it == UserAuthStatus.LoggedIn) {
                navigateToClientScreen()
        }
        }
    }

    private fun navigateToClientScreen() {
        findNavController().navigate(R.id.action_loginFragment_to_clientFragment)
    }

    private fun setupDependencies(inflater: LayoutInflater, container: ViewGroup?) {
        bindingLoginFragment = DataBindingUtil.inflate(inflater,R.layout.fragment_login,
            container,false)
        bindingLoginFragment.authViewModel = authViewModel
        bindingLoginFragment.lifecycleOwner = this
    }



}
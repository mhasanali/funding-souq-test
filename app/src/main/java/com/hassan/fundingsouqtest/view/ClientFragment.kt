package com.hassan.fundingsouqtest.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.hassan.fundingsouqtest.R
import com.hassan.fundingsouqtest.adapter.DiffCallback
import com.hassan.fundingsouqtest.data.response.NetworkResponseData
import com.hassan.fundingsouqtest.databinding.FragmentClientBinding
import com.hassan.fundingsouqtest.databinding.ItemClientBinding
import com.hassan.viewmodel.ClientViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ClientFragment : Fragment() {
    private val clientViewModel by viewModels<ClientViewModel>()
    private lateinit var fragmentClientBinding: FragmentClientBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentClientBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_client,container,false)
        fragmentClientBinding.recyclerClients.let {
            it.layoutManager = LinearLayoutManager(requireContext())
            it.adapter = clientViewModel.getAdapter()
        }
        getClients()
        return fragmentClientBinding.root
    }
    private fun getClients() {
        clientViewModel.getClients().observe(viewLifecycleOwner) {
            when(it){
                is NetworkResponseData.Success -> {
                    it.data?.let { clients ->
                        clientViewModel.setAdapter(clients)
                    }
                }
                else -> {
                    //do nothing
                }
            }
        }
    }

}
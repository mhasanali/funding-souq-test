package com.hassan.fundingsouqtest.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hassan.fundingsouqtest.data.response.DummyApiResponseItem
import com.hassan.fundingsouqtest.databinding.ItemClientBinding


class ClientAdapter : ListAdapter<DummyApiResponseItem, ClientAdapter.ItemViewHolder>(DiffCallback
    ()){
    private var clientList = emptyList<DummyApiResponseItem>()

    fun setClients(clientList: List<DummyApiResponseItem>){
        this.clientList = clientList
    }

    inner class ItemViewHolder(itemClientBinding: ItemClientBinding) : RecyclerView.ViewHolder
        (itemClientBinding.root) {
        private val itemClientBinding: ItemClientBinding = itemClientBinding
        fun bind(item: DummyApiResponseItem) {
            itemClientBinding.textId.text = item.id.toString()
            itemClientBinding.textTitle.text = item.title
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(ItemClientBinding.inflate(LayoutInflater.from(parent.context),
            parent, false))
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}
package com.hassan.fundingsouqtest.adapter

import androidx.recyclerview.widget.DiffUtil
import com.hassan.fundingsouqtest.data.response.DummyApiResponseItem

class DiffCallback : DiffUtil.ItemCallback<DummyApiResponseItem>() {
    override fun areItemsTheSame(
        oldItem: DummyApiResponseItem,
        newItem: DummyApiResponseItem
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: DummyApiResponseItem,
        newItem: DummyApiResponseItem
    ): Boolean {
        return oldItem == newItem
    }

}
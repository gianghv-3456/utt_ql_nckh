package com.gianghv.android.base

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

abstract class BaseViewHolder<out B : ViewBinding>(val binding: B) :
    RecyclerView.ViewHolder(binding.root) {
    abstract fun bind(item: Any, position: Int)
}

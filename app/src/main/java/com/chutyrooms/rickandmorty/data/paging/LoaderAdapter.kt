package com.chutyrooms.rickandmorty.data.paging

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.chutyrooms.rickandmorty.databinding.LoaderItemBinding

class LoaderAdapter: LoadStateAdapter<LoaderAdapter.LoaderViewHolder>() {

    class LoaderViewHolder(private val binding: LoaderItemBinding) :
        RecyclerView.ViewHolder(binding.root){

        fun bind(loadState: LoadState)
        {

            binding.apply {
                loader.isVisible= loadState is LoadState.Loading
                loader.isVisible= loadState is LoadState.Error
                tvError.isVisible= loadState is LoadState.Error
            }
        }
    }

    override fun onBindViewHolder(holder: LoaderViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoaderViewHolder {
        val binding: LoaderItemBinding = LoaderItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LoaderViewHolder(binding)
    }
}
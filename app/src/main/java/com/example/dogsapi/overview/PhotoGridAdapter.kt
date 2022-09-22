package com.example.dogsapi.overview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.dogsapi.data.ApiDataClass
import com.example.dogsapi.databinding.RecyclerViewItemBinding

class PhotoGridAdapter :
    ListAdapter<ApiDataClass, PhotoGridAdapter.ApiViewHolder>(DiffCallback) {
    class ApiViewHolder(
        private  var binding: RecyclerViewItemBinding
        ):RecyclerView.ViewHolder(binding.root){

        fun bind(apiDataClass: ApiDataClass){
            binding.info = apiDataClass
            binding.executePendingBindings()
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<ApiDataClass>() {
        override fun areItemsTheSame(oldItem: ApiDataClass, newItem: ApiDataClass): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ApiDataClass, newItem: ApiDataClass): Boolean {
            return oldItem.image.url == newItem.image.url
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ApiViewHolder {
        return ApiViewHolder(RecyclerViewItemBinding.inflate(
            LayoutInflater.from((parent.context))
        ))
    }

    override fun onBindViewHolder(holder: ApiViewHolder, position: Int) {
        val apiData = getItem(position)
        holder.bind(
            apiData
        )
    }

}
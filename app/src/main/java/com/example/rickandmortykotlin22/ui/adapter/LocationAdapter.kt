package com.example.rickandmortykotlin22.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmortykotlin22.data.network.dto.location.LocationDto
import com.example.rickandmortykotlin22.databinding.LocationItemBinding
import com.example.rickandmortykotlin22.keeper.base.BaseComparator

class LocationAdapter(
    private val onItemClick: (id: Int) -> Unit,
) : PagingDataAdapter<LocationDto, LocationAdapter.LocationViewHolder>(
    BaseComparator()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationViewHolder {
        return LocationViewHolder(
            LocationItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false)
        )
    }

    override fun onBindViewHolder(holder: LocationViewHolder, position: Int) {
        getItem(position)?.let {
            holder.onBind(it)
        }
    }

    inner class LocationViewHolder(
        private val binding: LocationItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            itemView.setOnClickListener {
                getItem(absoluteAdapterPosition)?.let {
                    onItemClick(it.id)
                }
            }
        }

        fun onBind(item: LocationDto) = with(binding) {
            name.text = item.name
            txtNameLocation.text = item.type
            txtDimensionLocation.text = item.dimension
        }
    }
}
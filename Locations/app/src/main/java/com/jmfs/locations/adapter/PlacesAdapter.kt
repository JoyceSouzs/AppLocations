package com.jmfs.locations.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jmfs.locations.databinding.LayoutPlacesBinding
import com.jmfs.locations.entity.Place

class PlacesAdapter(private var listPlaces: List<Place>)
: RecyclerView.Adapter<PlacesAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val binding =  LayoutPlacesBinding.inflate(
            LayoutInflater.from(parent.context)
            , parent, false
        )
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return listPlaces.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(listPlaces[position])
    }
    class MyViewHolder(private val binding: LayoutPlacesBinding)
        : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Place){
            binding.apply {
                binding.place = item
                executePendingBindings()
            }
        }

    }


}
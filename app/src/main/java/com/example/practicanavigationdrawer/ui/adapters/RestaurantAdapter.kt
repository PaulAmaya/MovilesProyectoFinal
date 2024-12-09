package com.example.practicanavigationdrawer.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.practicanavigationdrawer.databinding.ItemRestaurantBinding
import com.example.practicanavigationdrawer.models.Restaurant

class RestaurantAdapter(
    private val restaurants: List<Restaurant>,
    private val onRestaurantClick: (Restaurant) -> Unit
) : RecyclerView.Adapter<RestaurantAdapter.RestaurantViewHolder>() {

    inner class RestaurantViewHolder(private val binding: ItemRestaurantBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(restaurant: Restaurant) {
            binding.textViewName.text = restaurant.name
            binding.textViewAddress.text = restaurant.address
            Glide.with(binding.imageViewLogo.context)
                .load(restaurant.logo)
                .into(binding.imageViewLogo)

            binding.root.setOnClickListener {
                onRestaurantClick(restaurant)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantViewHolder {
        val binding =
            ItemRestaurantBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RestaurantViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RestaurantViewHolder, position: Int) {
        holder.bind(restaurants[position])
    }

    override fun getItemCount(): Int = restaurants.size
}

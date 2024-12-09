package com.example.practicanavigationdrawer.models

import com.google.gson.annotations.SerializedName

data class Product(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("description") val description: String,
    @SerializedName("price") val price: String,
    @SerializedName("restaurant_id") val restaurantId: Int,
    @SerializedName("image") val image: String
)

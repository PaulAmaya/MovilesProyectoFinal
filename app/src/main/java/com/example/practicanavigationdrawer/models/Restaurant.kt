package com.example.practicanavigationdrawer.models

import com.google.gson.annotations.SerializedName

data class Restaurant(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("address") val address: String,
    @SerializedName("latitude") val latitude: String,
    @SerializedName("longitude") val longitude: String,
    @SerializedName("logo") val logo: String,
    @SerializedName("products") val products: List<Product>
)

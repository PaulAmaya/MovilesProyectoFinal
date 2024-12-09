package com.example.practicanavigationdrawer.models

import com.google.gson.annotations.SerializedName


data class Order(
    @SerializedName("id") val id: Int,
    @SerializedName("user_id") val userId: Int,
    @SerializedName("restaurant_id") val restaurantId: Int,
    @SerializedName("total") val total: String,
    @SerializedName("latitude") val latitude: String,
    @SerializedName("longitude") val longitude: String,
    @SerializedName("address") val address: String,
    @SerializedName("driver_id") val driverId: Int?,
    @SerializedName("status") val status: String,
    @SerializedName("created_at") val createdAt: String,
    @SerializedName("delivery_proof") val deliveryProof: String,
    @SerializedName("order_details") val orderDetails: List<OrderDetail>
)




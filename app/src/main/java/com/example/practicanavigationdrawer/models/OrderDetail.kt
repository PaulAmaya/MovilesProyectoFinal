package com.example.practicanavigationdrawer.models

import com.google.gson.annotations.SerializedName

data class OrderDetail(
    val product_id: Int,
    val qty: Int,
    val price: String
)

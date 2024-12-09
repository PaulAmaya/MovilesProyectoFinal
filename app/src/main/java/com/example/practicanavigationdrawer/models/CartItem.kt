package com.example.practicanavigationdrawer.models

data class CartItem(
    val productId: Int,
    val productName: String,
    val quantity: Int,
    val price: Double,
    val imageUrl: String
)

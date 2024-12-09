package com.example.practicanavigationdrawer.managers

import com.example.practicanavigationdrawer.models.CartItem

object CartManager {
    private val cartItems = mutableListOf<CartItem>()

    fun addItem(item: CartItem) {
        val existingItem = cartItems.find { it.productId == item.productId }
        if (existingItem != null) {
            val updatedItem = existingItem.copy(quantity = existingItem.quantity + item.quantity)
            cartItems.remove(existingItem)
            cartItems.add(updatedItem)
        } else {
            cartItems.add(item)
        }
    }

    fun getItems(): List<CartItem> = cartItems

    fun removeItem(productId: Int) {
        cartItems.removeIf { it.productId == productId }
    }

    fun clearCart() {
        cartItems.clear()
    }
}

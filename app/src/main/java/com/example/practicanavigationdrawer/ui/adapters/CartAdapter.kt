package com.example.practicanavigationdrawer.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.practicanavigationdrawer.databinding.ItemCartBinding
import com.example.practicanavigationdrawer.models.CartItem

class CartAdapter(
    private val cartItems: List<CartItem>,
    private val onRemoveClick: (CartItem) -> Unit
) : RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    inner class CartViewHolder(private val binding: ItemCartBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(cartItem: CartItem) {
            binding.textViewName.text = cartItem.productName
            binding.textViewQuantity.text = "Cantidad: ${cartItem.quantity}"
            binding.textViewPrice.text = "Bs. ${cartItem.price * cartItem.quantity}"
            Glide.with(binding.imageViewProduct.context).load(cartItem.imageUrl).into(binding.imageViewProduct)

            binding.buttonRemove.setOnClickListener {
                onRemoveClick(cartItem)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val binding =
            ItemCartBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CartViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        holder.bind(cartItems[position])
    }

    override fun getItemCount(): Int = cartItems.size
}

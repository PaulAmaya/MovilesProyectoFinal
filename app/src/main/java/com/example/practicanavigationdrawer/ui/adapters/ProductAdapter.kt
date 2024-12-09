package com.example.practicanavigationdrawer.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.practicanavigationdrawer.databinding.ItemProductBinding
import com.example.practicanavigationdrawer.models.Product

class ProductAdapter(
    private val products: List<Product>,
    private val onProductClick: (Product) -> Unit,
    private val onAddToCartClick: (Product) -> Unit
) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    inner class ProductViewHolder(private val binding: ItemProductBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(product: Product) {
            binding.textViewName.text = product.name
            binding.textViewDescription.text = product.description
            binding.textViewPrice.text = "Bs. ${product.price}"
            Glide.with(binding.imageViewProduct.context).load(product.image).into(binding.imageViewProduct)

            binding.root.setOnClickListener {
                onProductClick(product)
            }

            binding.buttonAddToCart.setOnClickListener {
                onAddToCartClick(product)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding =
            ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(products[position])
    }

    override fun getItemCount(): Int = products.size
}

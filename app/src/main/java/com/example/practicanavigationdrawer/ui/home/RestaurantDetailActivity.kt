package com.example.practicanavigationdrawer.ui.home

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.practicanavigationdrawer.databinding.ActivityRestaurantDetailBinding
import com.example.practicanavigationdrawer.managers.CartManager
import com.example.practicanavigationdrawer.models.CartItem
import com.example.practicanavigationdrawer.repositories.RestaurantRepository
import com.example.practicanavigationdrawer.ui.adapters.ProductAdapter

class RestaurantDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRestaurantDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRestaurantDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val restaurantId = intent.getIntExtra("restaurantId", -1)
        if (restaurantId == -1) {
            Toast.makeText(this, "ID del restaurante inválido", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        fetchRestaurantDetails(restaurantId)
    }

    private fun fetchRestaurantDetails(restaurantId: Int) {
        RestaurantRepository.getRestaurantDetails(
            restaurantId = restaurantId,
            onSuccess = { restaurant ->
                // Configurar datos del restaurante
                binding.textViewRestaurantName.text = restaurant.name
                Glide.with(this).load(restaurant.logo).into(binding.imageViewLogo)

                // Configurar RecyclerView con los productos
                binding.recyclerViewProducts.apply {
                    layoutManager = LinearLayoutManager(this@RestaurantDetailActivity)
                    adapter = ProductAdapter(
                        products = restaurant.products,
                        onProductClick = { product ->
                            // Mostrar mensaje cuando se haga clic en un producto
                            Toast.makeText(
                                this@RestaurantDetailActivity,
                                "Clicked: ${product.name}",
                                Toast.LENGTH_SHORT
                            ).show()
                        },
                        onAddToCartClick = { product ->
                            // Agregar producto al carrito
                            CartManager.addItem(
                                CartItem(
                                    productId = product.id,
                                    productName = product.name,
                                    quantity = 1,
                                    price = product.price.toDouble(),
                                    imageUrl = product.image
                                )
                            )
                            Toast.makeText(
                                this@RestaurantDetailActivity,
                                "${product.name} añadido al carrito",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    )
                }
            },
            onError = { throwable ->
                Toast.makeText(this, "Error: ${throwable.message}", Toast.LENGTH_SHORT).show()
            }
        )
    }
}

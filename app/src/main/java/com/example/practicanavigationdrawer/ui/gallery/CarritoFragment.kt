package com.example.practicanavigationdrawer.ui.gallery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.practicanavigationdrawer.databinding.FragmentGalleryBinding
import com.example.practicanavigationdrawer.managers.CartManager
import com.example.practicanavigationdrawer.models.CartItem
import com.example.practicanavigationdrawer.models.OrderDetail
import com.example.practicanavigationdrawer.models.OrderRequest
import com.example.practicanavigationdrawer.repositories.OrderRepository
import com.example.practicanavigationdrawer.ui.adapters.CartAdapter

class CarritoFragment : Fragment() {

    private var _binding: FragmentGalleryBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGalleryBinding.inflate(inflater, container, false)
        setupRecyclerView()
        setupListeners()
        return binding.root
    }

    private fun setupRecyclerView() {
        binding.recyclerViewCart.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerViewCart.adapter = CartAdapter(CartManager.getItems()) { cartItem ->
            CartManager.removeItem(cartItem.productId)
            setupRecyclerView() // Actualiza la lista después de eliminar
        }
    }

    private fun setupListeners() {
        binding.buttonPlaceOrder.setOnClickListener {
            val address = binding.editTextAddress.text.toString()
            if (address.isEmpty()) {
                Toast.makeText(requireContext(), "Ingrese una dirección", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val cartItems = CartManager.getItems()
            if (cartItems.isEmpty()) {
                Toast.makeText(requireContext(), "El carrito está vacío", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val total = cartItems.sumOf { it.price * it.quantity }
            val restaurantId = cartItems.first().productId // Suponiendo que todos los productos son del mismo restaurante
            val details = cartItems.map {
                OrderDetail(
                    product_id = it.productId,
                    qty = it.quantity,
                    price = it.price.toString()
                )
            }

            val orderRequest = OrderRequest(
                restaurant_id = restaurantId,
                total = total,
                address = address,
                latitude = "0.0", // Reemplaza con la lógica para obtener la latitud real
                longitude = "0.0", // Reemplaza con la lógica para obtener la longitud real
                details = details
            )

            placeOrder(orderRequest)
        }
    }

    private fun placeOrder(orderRequest: OrderRequest) {
        OrderRepository.placeOrder(
            orderRequest = orderRequest,
            onSuccess = {
                Toast.makeText(requireContext(), "Pedido realizado con éxito", Toast.LENGTH_SHORT).show()
                CartManager.clearCart()
                setupRecyclerView() // Limpia la lista del carrito
            },
            onError = { throwable ->
                Toast.makeText(requireContext(), "Error: ${throwable.message}", Toast.LENGTH_SHORT).show()
            }
        )
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

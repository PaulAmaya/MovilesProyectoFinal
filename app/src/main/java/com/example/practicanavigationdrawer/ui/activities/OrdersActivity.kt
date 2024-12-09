package com.example.practicanavigationdrawer.ui.activities

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.practicanavigationdrawer.R
import com.example.practicanavigationdrawer.databinding.ActivityOrdersBinding
import com.example.practicanavigationdrawer.ui.adapters.OrderAdapter
import com.example.practicanavigationdrawer.ui.viewModels.OrdersViewModel

class OrdersActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOrdersBinding
    private val ordersViewModel: OrdersViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOrdersBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Configurar RecyclerView
        binding.recyclerViewOrders.layoutManager = LinearLayoutManager(this)

        // Observar los pedidos
        ordersViewModel.orders.observe(this) { orders ->
            binding.recyclerViewOrders.adapter = OrderAdapter(orders)
        }

        // Observar errores
        ordersViewModel.error.observe(this) { errorMessage ->
            Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
        }

        // Cargar pedidos
        ordersViewModel.fetchOrders()
    }
}

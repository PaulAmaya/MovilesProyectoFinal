package com.example.practicanavigationdrawer.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.practicanavigationdrawer.databinding.ItemOrderBinding
import com.example.practicanavigationdrawer.models.Order


class OrderAdapter(private val orders: List<Order>) : RecyclerView.Adapter<OrderAdapter.OrderViewHolder>() {

    inner class OrderViewHolder(private val binding: ItemOrderBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(order: Order) {
            binding.textViewOrderId.text = "Pedido ID: ${order.id}"
            binding.textViewAddress.text = "Dirección: ${order.address}"
            binding.textViewTotal.text = "Total: $${order.total}"
            binding.textViewStatus.text = "Estado: ${getStatusText(order.status)}"
        }

        private fun getStatusText(status: String): String {
            return when (status) {
                "0" -> "Solicitado"
                "1" -> "Aceptado"
                "2" -> "En camino"
                "3" -> "Finalizado"
                else -> "Desconocido"
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        val binding = ItemOrderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return OrderViewHolder(binding)
    }

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        holder.bind(orders[position])
    }

    override fun getItemCount(): Int = orders.size
}

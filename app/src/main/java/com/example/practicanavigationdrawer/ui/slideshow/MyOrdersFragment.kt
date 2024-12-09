package com.example.practicanavigationdrawer.ui.slideshow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.practicanavigationdrawer.databinding.FragmentSlideshowBinding
import com.example.practicanavigationdrawer.models.Order
import com.example.practicanavigationdrawer.repositories.OrderRepository
import com.example.practicanavigationdrawer.ui.adapters.OrderAdapter

class MyOrdersFragment : Fragment() {

    private var _binding: FragmentSlideshowBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSlideshowBinding.inflate(inflater, container, false)
        setupRecyclerView()
        fetchOrders()
        return binding.root
    }

    private fun setupRecyclerView() {
        binding.recyclerViewOrders.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun fetchOrders() {
        OrderRepository.getUserOrders(
            onSuccess = { orders ->
                binding.recyclerViewOrders.adapter = OrderAdapter(orders)
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
